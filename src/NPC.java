
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;
//import Item.NoItemException;


/**
 * Every NPC type will extend this class that gives each NPC a name, health,
 * conversation hashtable, and a current room that is read in from a file. Along
 * with getters for each field.
 *
 * @author Kaylee Payne
 * @author Jacques Troussard
 *
 */
public class NPC {

	static String MESSAGE_LEAD = "messages:";

	@SuppressWarnings("serial")
	static class NoNPCException extends Exception {
	}

	/**
	 * Name of NPC
	 */
	private String name;
	/**
	 * Health of NPC
	 */
	private int health;

	/**
	 * Hashtable of NPC conversations
	 */
	private Hashtable<String, Dialogue> conversations;
	/**
	 * Current Room of NPC
	 */
	private Room currentRoom;

	NPC() {
	}

	/**
	 * Creates an NPC with a string name.
	 *
	 * @param name
	 *            A string name for the NPC.
	 */
	NPC(String n) {
		this.name = n;
	}

	/**
	 * Creates an NPC with a scanner that reads from the dungeon file.
	 *
	 * @param scanner
	 *            The scanner of the Dungeon file.
	 */
	NPC(Scanner sc) {
	}

	/**
	 * Access Dialogue objects from conversations ds, keyed by a single
	 * character String.
	 * 
	 * @return Hashtable of Dialogue objects, keyed by single character Strings.
	 */
	public Hashtable<String, Dialogue> getConversations() {
		return conversations;
	}

	/**
	 * Initializes conversations data structure and Npcs current room.
	 */
	public void init() {
		conversations = new Hashtable<String, Dialogue>();
		store = new LinkedList<String>();
		this.currentRoom = null;
	}

	/**
	 * Returns the name of the NPC.
	 *
	 * @return name The name of the NPC.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the health of the NPC.
	 *
	 * @return health The name of the NPC.
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Returns the current room of the NPC.
	 *
	 * @return currentRoom The current room of the NPC.
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}

	/**
	 * Sets the health of the NPC.
	 *
	 * @param health
	 *            The new health of the NPC
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * Sets the current room of the NPC.
	 *
	 * @param currentRoom
	 *            The new currentRoom of the NPC
	 */
	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	/**
	 * Sets the name of the NPC.
	 *
	 * @param name
	 *            The new name of the NPC
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the correct response from the NPC's conversation Hashtable based
	 * off of a String prompt.
	 * 
	 * @param prompt
	 *            The user's conversation prompt that will be a key for a
	 *            response in conversation.
	 * @return converse The string response with the key of prompt.
	 */
	public String interact(String prompt) {
		return null;
	}

	/**
	 * Helper method used within the openDialogue method. Prompts user for
	 * single character String input which represents a key used to access
	 * Dialogue objects in conversationsdata structure.
	 * 
	 * @param commandLine
	 *            Scanner to read userinput.
	 * @return User input. Should only be a single character String.
	 */
	@SuppressWarnings("unused")
	private static String promptUser(Scanner commandLine) {
		System.out.print("> ");
		return commandLine.nextLine();
	}

	LinkedList<String> store;
	/**
	 * This method will take program control away from the Interpreter while the
	 * player can engage in a 'conversation' with an NPC. If the target Npc's
	 * conversation data structure is empty, a speech bubble with elipses is
	 * created, and control is passed back to Interpreter. However if the Npc
	 * has Dialogue objects in its conversations DS a list of conversation
	 * options is opened, and when selected will return the related message
	 * string as well as triggering any necessary events.
	 * @throws Item.NoItemException 
	 */
	public void openDialogue() throws Item.NoItemException {
		if (conversations.isEmpty()) {
			// silent npc
			String silent = " \".  .  .  .\" ";
			System.out.print(createSpeechBubble(silent));
			System.out.println("  " + name.toUpperCase() + "\n");
			System.out.println("\nThey don't seem interested in talking with you.\n");
		} else {
			Scanner option = new Scanner(System.in);
			String command = "new";
			String options = "abc";
			while (!command.equals("c")) {
				System.out.println("What do you want to talk about with " + this.name
						+ " ?");
				System.out.println("[a] Say hello.");
				if (this instanceof Vendor) {
					System.out.println("[b] Trade with " + this.name + ".");
				} else {
					System.out.println("[b] Ask a question.");
				}
				System.out.println("[c] Leave conversation.");
				command = promptUser(option);
				if (options.contains(command)) {
					Dialogue selection = conversations.get(command);
					System.out.println(createSpeechBubble(selection.getMessage()));
					System.out.println("  " + this.name.toUpperCase() + "\n");
					if (!selection.getActions().isEmpty()) {
						for (String eventDetails : selection.getActions()) {
							EventActivator ea = new EventActivator("*" + this.name + "*" + eventDetails);
							ea.activate();
						}
					}
				} else {
					System.out.println("Try speaking clearly.");
				}
			}

		}

	}

	/**
	 * Helper method used to center text on a ascii speech bubble.
	 * 
	 * @param text
	 *            Incoming text to be centered.
	 * @return text padded on either side with blank spaces so that it is
	 *         centered.
	 */
	public static String createSpeechBubble(String text) {
		String top = "==============================================";
		String bottom = "==\\   /======================================\n   \\ /\n    V";
		String edge = "/";

		String[] consoleLength = new String[42];
		String[] parts = text.split(" ");
		String result = "";
		String bubble = top + "\n" + edge + top.substring(2).replace("=", " ") + edge
				+ "\n";

		int counter = 0;
		for (String x : parts) {
			char[] word = x.toCharArray();
			if ((counter + word.length) > consoleLength.length) {
				result = center(result.substring(0, result.length() - 1), 46, "/");
				bubble += result + "\n";
				counter = 0;
				result = "";
			}
			for (char c : word) {
				result += Character.toString(c);
				counter++;
			}
			result += " ";
			counter += 1;
		}

		if (!result.isEmpty()) {
			result = center(result.substring(0, result.length() - 1), 46, "/") + "\n";
			result += edge + top.substring(2).replace("=", " ") + edge;
			bubble += result + "\n";
			return bubble + bottom;
		} else {
			return bubble + bottom;
		}
	}

	public LinkedList<String> getStore() {
		return store;
	}
	
	public static String center(String text, int length, String edge) {
		String result; // String to build as retrun value.
		String[] rez; // Pre-result in array data type.
		char[] rawTextAsChar; // raw param as array of chars (text param)
		int lineLength; // Total length of print line
		int size; // Count of how many characters are in para text
		int startingIndex; // Where the characters from text will be indexed
							// first
		String border; // Single character String to act as border

		// Set some initial variables
		rawTextAsChar = text.toCharArray();
		size = text.length();
		lineLength = length;
		startingIndex = (int) lineLength / 2;
		rez = new String[lineLength];
		border = edge;

		// Even out size of parameter length
		if (size % 2 != 0) {
			size++;
		}

		// Determine starting index and load into pre=result array
		startingIndex = (int) startingIndex - size / 2;
		for (char c : rawTextAsChar) {
			rez[startingIndex] = Character.toString(c);
			startingIndex++;
		}

		// Set borders
		rez[0] = border;
		rez[lineLength - 1] = border;

		// Create counter helper to step through rez array and check for missing
		// spaces
		int counter = 0;
		result = "";

		for (String x : rez) {
			if (x == null) {
				rez[counter] = " ";
			}
			result += rez[counter];
			counter++;
		}

		return result;
	}
}
