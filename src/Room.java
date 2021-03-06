import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.IOException;
import java.io.PrintWriter;

public class Room {
	/**
	 * Character name color
	 */
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_RESET = "\u001B[0m";

	@SuppressWarnings("serial")
	class NoRoomException extends Exception {
	}

	/**
	 * Header String for item contents when writing/reading from a file.
	 */
	static String CONTENTS_STARTER = "Contents: ";
	/**
	 * Header String for NPC present in room when writing/reading from a file.
	 */
	static String NPC_PRESENCE = "NPC Present: ";

	/**
	 * Name/ID for Room object
	 */
	private String title;
	/**
	 * Full description of Room. Used to print to console for player.
	 */
	private String desc;
	/**
	 * Boolean for whether GameState's adventurerCurrentRoom variable has ever
	 * been assigned as this Room object.
	 */
	private boolean beenHere;
	/**
	 * Indicates whether random coins are allowed to be generated in this room.
	 */
	private boolean randCoin;
	/**
	 * Indicates whether random monsters are allowed to be generated in this
	 * room.
	 */
	private boolean randMob;
	/**
	 * Data structure used to store Item objects which in game are present in
	 * the Room.
	 */
	private ArrayList<Item> contents;
	/**
	 * Data structure used to store Exit objects which in game are available for
	 * the player to access, regardless of their isLocked variable.
	 */
	private ArrayList<Exit> exits;
	/**
	 * Data structure used to store NPC objects which in game are present in the
	 * Room.
	 */
	private ArrayList<NPC> npcs;

	Room(String title) {
		init();
		this.title = title;
		this.randCoin = this.randMob = false;
	}

	Room(Scanner s, Dungeon d) throws NoRoomException,
			Dungeon.IllegalDungeonFormatException {

		this(s, d, true);
	}

	/**
	 * Given a Scanner object positioned at the beginning of a "room" file
	 * entry, read and return a Room object representing it.
	 * 
	 * @param d
	 *            The containing {@link edu.umw.stephen.bork.Dungeon} object,
	 *            necessary to retrieve {@link edu.umw.stephen.bork.Item}
	 *            objects.
	 * @param initState
	 *            should items listed for this room be added to it?
	 * @throws NoRoomException
	 *             The reader object is not positioned at the start of a room
	 *             entry. A side effect of this is the reader's cursor is now
	 *             positioned one line past where it was.
	 * @throws IllegalDungeonFormatException
	 *             A structural problem with the dungeon file itself, detected
	 *             when trying to read this room.
	 */
	Room(Scanner s, Dungeon d, boolean initState) throws NoRoomException,
			Dungeon.IllegalDungeonFormatException {

		init();
		title = s.nextLine();
		desc = "";
		if (title.equals(Dungeon.TOP_LEVEL_DELIM)) {
			throw new NoRoomException();
		}

		String lineOfDesc = s.nextLine();
		while (!lineOfDesc.equals(Dungeon.SECOND_LEVEL_DELIM) && !lineOfDesc.equals(
				Dungeon.TOP_LEVEL_DELIM)) {

			if (lineOfDesc.startsWith(CONTENTS_STARTER)) {
				if (lineOfDesc.contains("<")) {
					String[] lineParts = lineOfDesc.split(Pattern.quote(">"));
					String[] randPermissions = lineParts[0].substring(CONTENTS_STARTER
							.length() + 1, lineParts[0].length()).split(",");
					setRandPermissions(randPermissions);
					try {
						lineOfDesc = CONTENTS_STARTER + lineParts[1];
					} catch (ArrayIndexOutOfBoundsException e) {
						lineOfDesc = "no items after permissions";
					}

				}
				if (!lineOfDesc.equals("no items after permissions")) {
					String itemsList = lineOfDesc.substring(CONTENTS_STARTER.length());
					String[] itemNames = itemsList.split(",");
					for (String itemName : itemNames) {
						try {
							if (initState) {
								add(d.getItem(itemName));
							}
						} catch (Item.NoItemException e) {
							throw new Dungeon.IllegalDungeonFormatException(
									"No such item '" + itemName + "'");
						}
					}
					lineOfDesc = s.nextLine();
				}else{
					// Get rid of "no items after permission" 
					lineOfDesc = s.nextLine();
				}

			}
			if (lineOfDesc.startsWith(NPC_PRESENCE)) {
				String[] npcList = lineOfDesc.substring(NPC_PRESENCE.length()).split(",");
				for (String name : npcList) {
					NPC npc = d.getNpcs().get(name);
					npcs.add(npc);
				}
				lineOfDesc = s.nextLine();

			} else {
				desc += lineOfDesc + "\n";
				lineOfDesc = s.nextLine();
			}

		}

		// throw away delimiter
		if (!lineOfDesc.equals(Dungeon.SECOND_LEVEL_DELIM)) {
			throw new Dungeon.IllegalDungeonFormatException("No '"
					+ Dungeon.SECOND_LEVEL_DELIM + "' after room.");
		}
	}

	/**
	 * Common object initialization tasks.
	 */
	private void init() {
		contents = new ArrayList<Item>();
		exits = new ArrayList<Exit>();
		beenHere = false;
		npcs = new ArrayList<NPC>();
	}

	/**
	 * Returns Room object title name.
	 * 
	 * @return String title name
	 */
	String getTitle() {
		return title;
	}

	/**
	 * Sets the Room objects desc variable
	 * 
	 * @param desc
	 *            full description of the room
	 */
	void setDesc(String desc) {
		this.desc = desc;
	}

	/*
	 * Store the current (changeable) state of this room to the writer passed.
	 */
	void storeState(PrintWriter w) throws IOException {
		w.println(title + ":");
		w.println("beenHere=" + beenHere);
		if (contents.size() > 0) {
			w.print(CONTENTS_STARTER);
			for (int i = 0; i < contents.size() - 1; i++) {
				w.print(contents.get(i).getPrimaryName() + ",");
			}
			w.println(contents.get(contents.size() - 1).getPrimaryName());
		}
		w.println(Dungeon.SECOND_LEVEL_DELIM);
	}

	/**
	 * Given a Scanner object positioned at the beginning of a "Room" file entry
	 * from a .sav file, read and return a Room object representing it.
	 * 
	 * @param s
	 *            scanner object passed by the previous calling (GameState)
	 * @param d
	 *            current dungeon object created by the restore call in
	 *            GameState
	 * @throws GameState.IllegalSaveFormatException
	 */
	void restoreState(Scanner s, Dungeon d) throws GameState.IllegalSaveFormatException {

		String line = s.nextLine();
		if (!line.startsWith("beenHere")) {
			throw new GameState.IllegalSaveFormatException("No beenHere.");
		}
		beenHere = Boolean.valueOf(line.substring(line.indexOf("=") + 1));

		line = s.nextLine();
		if (line.startsWith(CONTENTS_STARTER)) {
			String itemsList = line.substring(CONTENTS_STARTER.length());
			String[] itemNames = itemsList.split(",");
			for (String itemName : itemNames) {
				try {
					add(d.getItem(itemName));
				} catch (Item.NoItemException e) {
					throw new GameState.IllegalSaveFormatException("No such item '"
							+ itemName + "'");
				}
			}
			s.nextLine(); // Consume "---".
		}
	}

	/**
	 * This method prints the details of the room on the console for the player,
	 * taking into consideration its contents, exits and the status of the Rooms
	 * boolean hasBeen variable. If it is false the Room title and description
	 * variables are formated and printed to the console. If is is true then
	 * only the title is printed to the console. For both conditions the Item
	 * and NPC object names are formated and printed to the console.
	 * 
	 * @return message string to be printed to the console
	 */
	public String describe() {
		String description;
		if (beenHere) {
			description = ":" + title + ":";
			if (!npcs.isEmpty()) {
				description += "\nYou notice " + makeNpcList();
			}

			for (Exit exit : exits) {
				description += "\n" + exit.describe();
			}
		} else {
			description = ":" + title + ":" + "\n" + desc + "\n";
			if (!npcs.isEmpty()) {
				description = description.trim() + " You also notice " + makeNpcList()
						+ "\n";
			}
		}
		if (!contents.isEmpty()) {
			for (Item item : contents) {
				description += "\nThere is a " + item.getPrimaryName() + " here.";
			}
			if (contents.size() > 0) {
				description += "\n";
			}
		} else {
			description += "\nThere are no items in this room.";
		}
		if (!beenHere) {
			for (Exit exit : exits) {
				description += "\n" + exit.describe();
			}
		}
		beenHere = true;
		return description + "\n";
	}

	/**
	 * Takes a single character, representing a direction which to exit a Room
	 * object, and confirms that and exit with the matching direction exists. If
	 * the exit direction is present the exit's destination Room object is
	 * return, otherwise the return value is set to null.
	 * 
	 * @param dir
	 *            single character representing the potential direction an exit
	 * @return the destination room object
	 */
	public Room leaveBy(String dir) {
		for (Exit exit : exits) {
			if (exit.getDir().equals(dir)) {
				return exit.getDest();
			}
		}
		return null;
	}

	/**
	 * Takes an Exit object and adds it to the Room's exits array list.
	 * 
	 * @param exit
	 *            exit object representing a viable movement direction for the
	 *            player
	 */
	void addExit(Exit exit) {
		exits.add(exit);
	}

	/**
	 * Takes in an Item object and adds it to the Room's contents array list.
	 * 
	 * @param item
	 */
	void add(Item item) {
		contents.add(item);
	}

	/**
	 * Takes in an NPC object and adds it to the Room's npcs array list.
	 * 
	 * @param npc
	 */
	void add(NPC npc) {
		npcs.add(npc);
	}

	/**
	 * Takes in an Item object and removes it from the Room's contents array
	 * list.
	 * 
	 * @param item
	 *            the target item object to be removed from the room's contents
	 *            array list
	 */
	void remove(Item item) {
		contents.remove(item);
	}

	/**
	 * Takes in an NPC object and removes it from the Room's npcs array list.
	 * 
	 * @param npc
	 *            the target npc object to be removed from the room's npcs array
	 *            list
	 */
	void remove(NPC npc) {
		npcs.remove(npc);
	}

	/**
	 * Takes in a String as a search parameter, representing the primary name of
	 * an Item. Checks the room's contents array list for a match. If match is
	 * made returns an Item, otherwise throws exception.
	 * 
	 * @param name
	 *            search parameter, as name of item
	 * @return item object
	 * @throws Item.NoItemException
	 */
	Item getItemNamed(String name) throws Item.NoItemException {
		for (Item item : contents) {
			if (item.goesBy(name)) {
				return item;
			}
		}
		throw new Item.NoItemException();
	}

	/**
	 * Takes in a String as a search parameter, representing the name of a NPC
	 * object. Checks the room's npcs array list for a match. If match is made
	 * returns an NPC object, otherwise throws exception.
	 * 
	 * @param name
	 *            search parameter, as name of npc
	 * @return target npc object
	 * @throws NPC.NoNPCException
	 */
	NPC getNpcNamed(String name) throws NPC.NoNPCException {
		for (NPC npc : npcs) {
			if (npc.getName().equals(name)) {
				return npc;
			}
		}
		throw new NPC.NoNPCException();
	}

	/**
	 * Returns the Item objects within the contents db
	 * 
	 * @return list of Items present in the Room
	 */
	ArrayList<Item> getContents() {
		return contents;
	}

	/**
	 * Takes String array, generated from parsed room description line at time
	 * of room initialization and sets the random object generation permissions.
	 *
	 * @param randPermissionList
	 *            String array containing permission checks for random
	 *            <p>
	 *            object generation.
	 */
	void setRandPermissions(String[] randPermissionList) {
		for (String permissionType : randPermissionList) {
			switch (permissionType.trim()) {
			case "coin":
				randCoin = true;
				break;
			case "mob":
				randMob = true;
				break;
			default:
				randCoin = randMob = false;
				break;
			}
		}
	}

	public boolean isRandCoin() {
		return randCoin;
	}

	public boolean isRandMob() {
		return randMob;
	}

	/**
	 * Returns a String listing all or the only npc(s) present in the room
	 * object. Grammatical differences between plural and singular npcs are
	 * handled.
	 * 
	 * @return String of npcs present in room.
	 */
	public String makeNpcList() {
		String list = "";
		if (!npcs.isEmpty() && npcs.size() > 1) {
			for (NPC x : npcs) {
				list += " " + x.getName() + ",";
			}
			list = ANSI_CYAN + list.substring(0, list.length() - 1) + ANSI_RESET  + " are here.";
		} else if (!npcs.isEmpty()) {
			list += ANSI_CYAN + npcs.get(0).getName() + ANSI_RESET  + " is here.";
		}
		return list;
	}

	/**
	 * Development test method used to check room contents by printing a list to
	 * the console.
	 */
	public void devPrintRoomContents() {
		int num = 0;
		if (!contents.isEmpty()) {
			System.out.println("Room Contains:");
			for (Item i : contents) {
				System.out.println(num + ": " + i.getPrimaryName());
				num++;
			}
			System.out.println();
		} else {
			System.out.println("Room is empty");
			System.out.println();
		}
	}
}
