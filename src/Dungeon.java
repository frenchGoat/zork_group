import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Random;

/**
 * @author Jacques Troussard
 *
 */
public class Dungeon {

	@SuppressWarnings("serial")
	public static class IllegalDungeonFormatException extends Exception {
		public IllegalDungeonFormatException(String e) {
			super(e);
		}
	}

	// Variables relating to both dungeon file and game state storage.
	public static String TOP_LEVEL_DELIM = "===";
	public static String SECOND_LEVEL_DELIM = "---";

	// Variables relating to dungeon file (.bork) storage.
	public static String ROOMS_MARKER = "Rooms:";
	public static String EXITS_MARKER = "Exits:";
	public static String ITEMS_MARKER = "Items:";
	public static String NPCS__MARKER = "NPCs:";

	// Variables relating to game state (.sav) storage.
	static String FILENAME_LEADER = "Dungeon file: ";
	static String ROOM_STATES_MARKER = "Room states:";

	// Variables relating to Dungeon.
	/**
	 * Name used to identify loaded dungeon object.
	 */
	private String name;
	/**
	 * Player spawn location.
	 */
	private Room entry;
	/**
	 * Data structure storing dungeon rooms.
	 */
	private Hashtable<String, Room> rooms;
	/**
	 * Data structure storing teleport destination room options.
	 */
	private Hashtable<String, Room> teleDests;
	/**
	 * Data structure storing all possible dungeon items.
	 */
	private Hashtable<String, Item> items;
	/**
	 * Data structure for storing items taken out of play
	 */
	private Hashtable<String, Item> itemsOutOfPlay;
	/**
	 * Data structure storing all possible NPCs.
	 */
	private Hashtable<String, NPC> npcs;

	// Variables related to identifying files.
	private String filename;

	Dungeon(String name, Room entry) {
		init();
		this.filename = null; // null indicates not hydrated from file.
		this.name = name;
		this.entry = entry;
		rooms = new Hashtable<String, Room>();
		teleDests = new Hashtable<String, Room>();
	}

	/**
	 * Read from the .bork filename passed, and instantiate a Dungeon object
	 * based on it.
	 * 
	 * @param filename
	 *            Full bork file name including extension.
	 * @throws FileNotFoundException
	 * @throws IllegalDungeonFormatException
	 */
	public Dungeon(String filename) throws FileNotFoundException,
			IllegalDungeonFormatException {

		this(filename, true);
	}

	/**
	 * Read from the .bork filename passed, and instantiate a Dungeon object
	 * based on it, including the items/NPCs in their original locations.
	 * 
	 * @param filename
	 *            Full bork file name including extension
	 * @param initState
	 *            Represents the desire to reload the map or restore
	 * @throws FileNotFoundException
	 * @throws IllegalDungeonFormatException
	 */
	@SuppressWarnings("resource")
	public Dungeon(String filename, boolean initState) throws FileNotFoundException,
			IllegalDungeonFormatException {

		init();
		this.filename = filename;

		Scanner s = new Scanner(new FileReader(filename));
		name = s.nextLine();

		s.nextLine(); // Throw away version indicator.

		// Throw away delimiter.
		if (!s.nextLine().equals(TOP_LEVEL_DELIM)) {
			throw new IllegalDungeonFormatException("No '" + TOP_LEVEL_DELIM
					+ "' after version indicator.");
		}

		// Throw away Items starter.
		if (!s.nextLine().equals(ITEMS_MARKER)) {
			throw new IllegalDungeonFormatException("No '" + ITEMS_MARKER
					+ "' line where expected.");
		}

		try {
			// Instantiate items.
			while (true) {
				add(new Item(s));
			}
		} catch (Item.NoItemException e) {
			/* end of items */ }

		// Throw away NPCS starter.
		if (!s.nextLine().equals(NPCS__MARKER)) {
			throw new IllegalDungeonFormatException("No '" + NPCS__MARKER
					+ "' line where expected.");
		}

		try {
			// Instantiate npc.
			while (true) {
				NpcMaker incomingNpc = new NpcMaker();
				add(incomingNpc.makeNpc(s));
			}
			// Throw away end of npcs delimiter
			
			/*
			 *  I have no idea why but when using NPC.NoNPCException makes this 'block
			 *   of code un reachable' for now switch to regular exception
			 */
		} catch (Exception e) {
			/* end of npcs */ }

		// Throw away Rooms starter.
		if (!s.nextLine().equals(ROOMS_MARKER)) {
			throw new IllegalDungeonFormatException("No '" + ROOMS_MARKER
					+ "' line where expected.");
		}

		try {
			// Instantiate and add first room (the entry).
			entry = new Room(s, this, initState);
			add(entry);

			// Instantiate and add other rooms.
			while (true) {
				add(new Room(s, this, initState));
			}
		} catch (Room.NoRoomException e) {
			/* end of rooms */ }

		// Throw away Exits starter.
		if (!s.nextLine().equals(EXITS_MARKER)) {
			throw new IllegalDungeonFormatException("No '" + EXITS_MARKER
					+ "' line where expected.");
		}

		try {
			// Instantiate exits.
			while (true) {
				Exit exit = new Exit(s, this);
			}
		} catch (Exit.NoExitException e) {
			/* end of exits */ }

		s.close();
		randomObjGeneration();
	}

	private void add(NPC npc) {
		npcs.put(npc.getName(), npc);

	}

	/**
	 * Loops through every Room object checking for its random object generation
	 * permissions. When it finds a match the appropriate generator is call.
	 */
	private void randomObjGeneration() {
		Random randInt = new Random();
		Room targetRoom = null;
		int coinGenRate = 35;
		int mobGenRate = 20;
		for (String key : rooms.keySet()) {
			targetRoom = rooms.get(key);
			int success = randInt.nextInt(101);
			if (targetRoom.isRandCoin() && success < coinGenRate) {
				// System.out.println("MAKING COIN");
				String addCoin = coinGenerator(randInt);
				targetRoom.add(items.get(addCoin));
				// System.out.println(items.get(addCoin).getPrimaryName());
			}
			if (targetRoom.isRandMob()) {

			}

		}

	}

	/**
	 * Takes a random number generator. Loops through all the coin values and
	 * creates probabilities based on their value. Higher value coins have a
	 * lower probability. A random number is generated and then the normalized
	 * coin values are incremented until the running total surpasses the random
	 * number which indicates which coin should be generated.
	 * 
	 * @param r
	 *            Random number generator object
	 * @return Coin value description for the dungeon to decode.
	 */
	private String coinGenerator(Random r) {
		LinkedList<Coin> arr = GameState.instance().getCoinValues();
		Hashtable<String, Double> coinDropRates = new Hashtable<String, Double>();
		double norm = 0;
		double seed = r.nextDouble();
		double prob = 0;
		int high = 0;

		for (Coin c : arr) {
			if (c.getValue() > high) {
				high = c.getValue();
			}
			norm += c.getValue();
		}
		for (Coin c : arr) {
			norm = c.getValue() / high;
		}
		for (Coin c : arr) {
			coinDropRates.put(c.getName(), Double.valueOf(high / c.getValue()) / norm);
		}
		for (String key : coinDropRates.keySet()) {
			prob += coinDropRates.get(key).doubleValue();
			if (prob > seed) {
				return key;
			}

		}
		// System.out.println("FAILED COIN GENERATION");
		return null;

	}

	// common object initialization tasks, regardless of which constructor
	// is used.
	private void init() {
		rooms = new Hashtable<String, Room>();
		teleDests = new Hashtable<String, Room>();
		items = new Hashtable<String, Item>();
		itemsOutOfPlay = new Hashtable<String, Item>();
		npcs = new Hashtable<String, NPC>();
	}

	/*
	 * Store the current (changeable) state of this dungeon to the writer
	 * passed.
	 */
	void storeState(PrintWriter w) throws IOException {
		w.println(FILENAME_LEADER + getFilename());
		w.println(ROOM_STATES_MARKER);
		for (Room room : rooms.values()) {
			room.storeState(w);
		}
		w.println(TOP_LEVEL_DELIM);
	}

	/*
	 * Restore the (changeable) state of this dungeon to that reflected in the
	 * reader passed.
	 */
	void restoreState(Scanner s) throws GameState.IllegalSaveFormatException {

		// Note: the filename has already been read at this point.

		if (!s.nextLine().equals(ROOM_STATES_MARKER)) {
			throw new GameState.IllegalSaveFormatException("No '" + ROOM_STATES_MARKER
					+ "' after dungeon filename in save file.");
		}

		String roomName = s.nextLine();
		while (!roomName.equals(TOP_LEVEL_DELIM)) {
			getRoom(roomName.substring(0, roomName.length() - 1)).restoreState(s, this);
			roomName = s.nextLine();
		}
	}

	/**
	 * Returns the spawn room of the dungeon.
	 * 
	 * @return spawn room for dungeon
	 */
	public Room getEntry() {
		return entry;
	}

	/**
	 * Returns the dungeon name.
	 *
	 * @return name The name of the dungeon.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the filename with the extension.
	 *
	 * @return filename Full dungeon file name with extension.
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Adds a room to the dungeons rooms list.
	 * 
	 * @param room
	 *            Room object to be added to the rooms and teleDests hashtable,
	 *            keyed by room title(name).
	 */
	public void add(Room room) {
		rooms.put(room.getTitle(), room);
		teleDests.put(room.getTitle(), room);
	}

	/**
	 * Adds a room to the designated data structure.
	 * 
	 * @param dest
	 *            Destination data structure for room storage.
	 * @param room
	 *            Room object to be added to the designated hashtable, keyed by
	 *            room title(name).
	 */
	public void add(Hashtable<String, Room> dest, Room room) {
		dest.put(room.getTitle(), room);
	}

	/**
	 * Adds an item to the dungeon items list.
	 *
	 * @param item
	 *            Item object to be added to the items hashtable, keyed by
	 *            primary name.
	 */
	public void add(Item item) {
		items.put(item.getPrimaryName(), item);
	}

	/**
	 * Get the Room object whose name is passed. This has nothing to do with
	 * where the Adventurer might be.
	 * 
	 * @param roomTitle
	 *            Room name
	 * @return room Room object from rooms hashtable keyed by room name
	 */
	public Room getRoom(String roomTitle) {
		return rooms.get(roomTitle);
	}

	/**
	 * Get the Item object whose primary name is passed. This has nothing to do
	 * with where the Adventurer might be, or what's in his/her inventory, etc.
	 *
	 * @param primaryItemName
	 *            String - Used as key for items hashtable
	 * @return Item object from items hashtable
	 * @throws Item.NoItemException
	 */
	public Item getItem(String primaryItemName) throws Item.NoItemException {

		if (items.get(primaryItemName) == null) {
			throw new Item.NoItemException();
		}
		return items.get(primaryItemName);
	}

	/**
	 * This is a helper method which effectively removes and Item object from
	 * the game - by removing it from the db holding Items linked to the
	 * Dungeon. Due to frequent additions of features, the items will not
	 * technically be deleted, but moved into an inaccessible(for now) db so
	 * that if a new feature is added to the game which requires bringing an
	 * Item taking out of play, but into the dungeon/game, it can be done
	 * without having to read it from a file. The idea being, when the file is
	 * originally read, and creates items, it only creates an item once.
	 * 
	 * @param item
	 *            target Item object which is removed from the game ie. Dungeon
	 *            object db structure holding all Item objects *
	 * @throws Item.NoItemException
	 */
	public void removeItemFromGame(Item item) throws Item.NoItemException {
		String targetItem = item.getPrimaryName();
		if (items.get(targetItem) == null) {
			throw new Item.NoItemException();
		} else {
			itemsOutOfPlay.put(targetItem, item);
			items.remove(targetItem);
		}

	}

	public Hashtable<String, Room> getTeleTable() {
		return teleDests;
	}

	public Hashtable<String, NPC> getNpcs() {
		return npcs;
	}

	/**
	 * Development test method used to check game item master list by printing a
	 * list of all in-play and out-of-play items to the console.
	 */
	public void devPrintAllItems() {
		int num = 0;
		if (!items.isEmpty() || !itemsOutOfPlay.isEmpty()) {
			if (!items.isEmpty()) {
				System.out.println("Items-In-Play:");
				for (String key : items.keySet()) {
					System.out.println(num + ": " + items.get(key).getPrimaryName());
					num++;
				}
				System.out.println();
			} else {
				System.out.println("There are not items in play");
			}
			num = 0;
			if (!itemsOutOfPlay.isEmpty()) {
				num = 0;
				System.out.println("Items-Out-of-Play:");
				for (String key : itemsOutOfPlay.keySet()) {
					System.out.println(num + ": " + itemsOutOfPlay.get(key)
							.getPrimaryName());
					num++;
				}
				System.out.println();
			} else {
				System.out.println("ALL items are in play");
			}
		} else {
			System.out.println("There are no items at all");
		}
	}

}
