import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * University of Mary Washington 
 * CPSC 240 Section 2
 * 
 */

/**
 * @author Jacques Troussard
 *
 */
public class GameState {

	@SuppressWarnings("serial")
	public static class IllegalSaveFormatException extends Exception {
		public IllegalSaveFormatException(String e) {
			super(e);
		}
	}

	/**
	 * Header String for default file name when writing to a .sav file.
	 */
	static String DEFAULT_SAVE_FILE = "bork_save";
	/**
	 * Header String for default save file extension when writing to a .sav
	 * file.
	 */
	static String SAVE_FILE_EXTENSION = ".sav";
	/**
	 * Header String for default file version when reading/writing from/to a
	 * .bork/.sav file.
	 */
	static String SAVE_FILE_VERSION = "Bork v3.0 save data";
	/**
	 * Header String for player status section when writing to a .sav file.
	 */
	static String ADVENTURER_MARKER = "Adventurer:";
	/**
	 * Header String for player position status when writing to a .sav file.
	 */
	static String CURRENT_ROOM_LEADER = "Current room: ";
	/**
	 * Header String for player inventory status when writing to a .sav file.
	 */
	static String INVENTORY_LEADER = "Inventory: ";
	/**
	 * Header String for player health status when writing to a .sav file.
	 */
	static String HEALTH_LEADER = "Health: ";
	/**
	 * Header String for player hunger status when writing to a .sav file.
	 */
	static String HUNGER_LEADER = "Hunger: ";
	/**
	 * Header String for player companion status when writing to a .sav file.
	 */
	static String COMPANION_LEADER = "Companion: ";
	/**
	 * Header String for player bank status when writing to a .sav file.
	 */
	static String BANK_LEADER = "Bank: ";
	/**
	 * Header String for player score status when writing to a .sav file.
	 */
	static String SCORE_LEADER = "Score: ";
	/**
	 * Instance variable for GameState
	 */
	private static GameState theInstance;
	/**
	 * Current dungeon
	 */
	private Dungeon dungeon;
	/**
	 * Current player inventory
	 */
	private ArrayList<Item> inventory;
	/**
	 * Current location of player
	 */
	private Room adventurersCurrentRoom;
	/**
	 * Current status of player health
	 */
	private int playerHealth;
	/**
	 * Current status of player hunger
	 */
    private int playerScore;
    /**
     * Current score of the player
     */
	private int playerHunger;
	/**
	 * Current amount in player bank
	 */
	private int playerBank;
	/**
	 * Value of monetary items in the game.
	 */
	private LinkedList<Coin> coinValues;
	/**
	 * Michelle: sorry didn't know how you wanted to do this so i'll leave it to
	 * you
	 */
	private ArrayList<Exit> unlockedExits;
	// Variables related to game logging
	/**
	 * Database structure for the game log. Player commands, events triggered
	 * and results will be stored here.
	 */
	private ArrayList<String> log;
	/**
	 * Actual log entry to be put into log database.
	 */
	static String logEntry;
	/**
	 * Current GameState turn (counter variable).
	 */
	private int curTurn;
	
	
	
	/**
	 * Returns the only instance of GameState
	 *
	 * @return theInstance current instance of GameState
	 */
	static synchronized GameState instance() {
		if (theInstance == null) {
			theInstance = new GameState();
		}
		return theInstance;
	}
	
	/**
	 * Main Constructor
	 */
	private GameState() {
		inventory = new ArrayList<Item>();
		log = new ArrayList<String>();
		curTurn = 0;
		
		/*
		 * At this point the game will have only four currency types with the following 
		 * coefficient values;
		 *  Steel: 1
		 * Copper: 2
		 * Silver: 4
		 *   Gold: 7
		 * 
		 */
		coinValues = new LinkedList<Coin>();
		coinValues.add(new Coin("Steel", 1));
		coinValues.add(new Coin("Copper", 2));
		coinValues.add(new Coin("Silver", 4));
		coinValues.add(new Coin("Gold", 7));
		
	}
	
	/**
	 * Restores GameState with a .sav file
	 * 
	 * @param filename
	 *            .sav file name
	 * @throws FileNotFoundException
	 * @throws IllegalSaveFormatException
	 * @throws Dungeon.IllegalDungeonFormatException
	 */
	@SuppressWarnings("resource")
	void restore(String filename) throws FileNotFoundException,
			IllegalSaveFormatException, Dungeon.IllegalDungeonFormatException {

		Scanner s = new Scanner(new FileReader(filename));

		if (!s.nextLine().equals(SAVE_FILE_VERSION)) {
			throw new IllegalSaveFormatException("Save file not compatible.");
		}

		String dungeonFileLine = s.nextLine();

		if (!dungeonFileLine.startsWith(Dungeon.FILENAME_LEADER)) {
			throw new IllegalSaveFormatException("No '" + Dungeon.FILENAME_LEADER
					+ "' after version indicator.");
		}

		dungeon = new Dungeon(dungeonFileLine.substring(Dungeon.FILENAME_LEADER.length()),
				false);
		dungeon.restoreState(s);

		s.nextLine(); // Throw away "Adventurer:".
		String currentRoomLine = s.nextLine();
		adventurersCurrentRoom = dungeon.getRoom(currentRoomLine.substring(
				CURRENT_ROOM_LEADER.length()));
		if (s.hasNext()) {
			String inventoryList = s.nextLine().substring(INVENTORY_LEADER.length());
			String[] inventoryItems = inventoryList.split(",");
			for (String itemName : inventoryItems) {
				try {
					addToInventory(dungeon.getItem(itemName));
				} catch (Item.NoItemException e) {
					throw new IllegalSaveFormatException("No such item '" + itemName
							+ "'");
				}
			}
		}
	}

	/**
	 * Base function used recursively for storing the current GameState to a
	 * .sav file.
	 *
	 * Helper method used during restore function to recreate base dungeon and
	 * setting players current position in the dungeon.
	 * 
	 * @param dungeon
	 *            The dungeon to be initialized.
	 */
	void initialize(Dungeon dungeon) {
		this.dungeon = dungeon;
		adventurersCurrentRoom = dungeon.getEntry();
		playerHealth = 100;

	}

	ArrayList<String> getInventoryNames() {
		ArrayList<String> names = new ArrayList<String>();
		for (Item item : inventory) {
			names.add(item.getPrimaryName());
		}
		return names;
	}

	/**
	 * Updates player inventor with an item. Item object is added to player
	 * inventory data structure.
	 * 
	 * @param item
	 *            Specific item to be added to inventory
	 */
	void addToInventory(Item item) /* throws TooHeavyException */ {
		inventory.add(item);
	}

	/**
	 * Updates player inventory by removing an item. Item object is removed from
	 * player inventory data structure.
	 * 
	 * @param item
	 *            specific item to be removed from inventory
	 */
	void removeFromInventory(Item item) {
		inventory.remove(item);
	}

	/**
	 * Helper method used to access a specified item from either within player
	 * inventory or in current room.
	 * 
	 * @param name
	 *            Primary name of item Object
	 */
	Item getItemInVicinityNamed(String name) throws Item.NoItemException {

		// First, check inventory.
		for (Item item : inventory) {
			if (item.goesBy(name)) {
				return item;
			}
		}

		// Next, check room contents.
		for (Item item : adventurersCurrentRoom.getContents()) {
			if (item.goesBy(name)) {
				return item;
			}
		}

		throw new Item.NoItemException();
	}

	/**
	 * Helper method used to access items in player inventory.
	 * 
	 * @param name
	 *            primary name of target Item object
	 * @return target Item object
	 * @throws Item.NoItemException
	 */
	Item getItemFromInventoryNamed(String name) throws Item.NoItemException {

		for (Item item : inventory) {
			if (item.goesBy(name)) {
				return item;
			}
		}
		throw new Item.NoItemException();
	}

	Room getAdventurersCurrentRoom() {
		return adventurersCurrentRoom;
	}

	void setAdventurersCurrentRoom(Room room) {
		adventurersCurrentRoom = room;
	}

	Dungeon getDungeon() {
		return dungeon;
	}

	public int getPlayerHealth() {
		// access then return player health
		return playerHealth;
	}
	public int getPlayerScore() {
		return playerScore;
	}
	public int getPlayerHunger() {
		return playerHunger;
	}

	public void setPlayerHunger(int playerHunger) {
		this.playerHunger = playerHunger;
	}

	public int getPlayerBank() {
		return playerBank;
	}
	
	public LinkedList<Coin> getCoinValues() {
		return coinValues;
	}

	public void setPlayerBank(int playerBank) {
		this.playerBank = playerBank;
	}

	public void setPlayerHealth(int playerHealth) {
		this.playerHealth = playerHealth;
	}
	
	public void setPlayerScore(int newScore) {
		this.playerScore = newScore;
	}

	public boolean isItemInRoom(String itemName) throws Item.NoItemException {
		Item item = getDungeon().getItem(itemName);
		if (adventurersCurrentRoom.getContents().contains(item)) {
			return true;
		}
		return false;
	}

	public boolean isItemInPlayerInventory(String itemName) throws Item.NoItemException {
		Item item = getDungeon().getItem(itemName);
		if (inventory.contains(item)) {
			return true;
		}
		return false;
	}

	void store() throws IOException {
		store(DEFAULT_SAVE_FILE);
	}

	void store(String saveName) throws IOException {
		String filename = saveName + SAVE_FILE_EXTENSION;
		PrintWriter w = new PrintWriter(new FileWriter(filename));
		w.println(SAVE_FILE_VERSION);
		dungeon.storeState(w);
		w.println("Adventurer:");
		w.println(CURRENT_ROOM_LEADER + getAdventurersCurrentRoom().getTitle());
		w.println(INVENTORY_LEADER + printInv());
		w.println(HEALTH_LEADER + getPlayerHealth());
		w.println(SCORE_LEADER + getPlayerScore());
		w.close();
	}

	public String printInv() {
		String buff = "";
		for (Item x : inventory) {
			buff += x.getPrimaryName() + ",";
		}
		buff = buff.substring(0, buff.length());
		return buff;
	}

	public String addMoney(String incomingCoin){
		int retval = 0;
		for (Coin c : coinValues){
			if (incomingCoin.equals(c.getName())){
				int purse = getPlayerBank();
				int deposit = c.getValue()*10;
				purse += deposit;
				setPlayerBank(purse);
				retval = deposit;
			}
		}
		return Integer.toString(retval);
	}
	// ++++++++++++++GAME LOGGER METHODS+++++++++++++++++
	// Possibly move this to its own class

	/**
	 * Adds and entry to the log data structure
	 * 
	 * @param logString
	 *            description of action/result taken in game.
	 */
	public void logAction(String logString) {
		log.add(logString);
	}

	public void clickTurn() {
		curTurn++;

	}

	// ++++++++++++++DEVELOPMENT & TEST METHODS+++++++++++++++++

	/**
	 * Development test method used to check player inventory by printing a list
	 * to the console.
	 */
	public void devPrintInventory() {
		int num = 0;
		if (!inventory.isEmpty()) {
			System.out.println("Players Inventory:");
			for (Item i : inventory) {
				System.out.println(num + ": " + i.getPrimaryName());
				num++;
			}
			System.out.println();
		} else {
			System.out.println("Player inventory is empty");
			System.out.println();
		}
	}

	public void devPrintGameLog() {
		int num = 0;
		for (String x : log) {
			System.out.print("Turn " + num + "\n" + x);

		}
	}
}
