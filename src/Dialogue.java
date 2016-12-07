import java.util.ArrayList;
import java.util.regex.Pattern;
/**
 * University of Mary Washington
 * CPSC 240 Section 2
 * Group Project 3-b
 * 
 * This class is used to add depth to messages stored within an NPCs conversation data structure. Along 
 * with the text strings, there will also be a storage and activation system for events that will be triggered by 
 * certain conversations.
 * 
 * @author Jacques Troussard
 *
 */
public class Dialogue {

	/**
	 * The actual message.
	 */
	private String message;
	
	/**
	 * Single alpha character keyed to a specific message string.
	 */
	private String messageKey;
	
	
	/**
	 * Data structure used to store event description strings related to this message.
	 */
	private ArrayList<String> actions;


	/**
	 * Constructor fortesting purposes.
	 * 
	 * @param message
	 * 				String of actual message.
	 * @param messageKey
	 * 				Single character string keyed to this message String.
	 */
	public Dialogue(String message, String messageKey) {
		super();
		init();
		this.message = message.substring(2);
		this.messageKey = messageKey;
	}
	
	/**
	 * Constructor reads messages string which is pulled from file during hydration. After the NpcMaker creates the 
	 * subclass of the Npc, that subclass takes the scanner, parses the section related to the current Npc and when
	 * it reads "messages:...", it splits the messages up individually and passes them one at a time to this
	 * constructor until all Dialogues have been created and stored in the Npcs conversation data structure.
	 * 
	 * @param messageLine
	 * 				String read in for bork file and parsed by Npc subclass, with the following format: (key),(message)
	 */
	public Dialogue(String messageLine) {
		init();
		this.messageKey = Character.toString(messageLine.charAt(0));
		if (messageLine.contains(Pattern.quote("["))) {
			String[] parts = message.substring(2).split(Pattern.quote(","));
			this.message = parts[1].substring(2);
			actions.add(parts[0].substring(0, parts[0].length()));
		}else{
			this.message = messageLine.substring(2);
		}
	}
	
	/**
	 * Initializes data structure. Used in constructors.
	 */
	public void init(){
		actions = new ArrayList<String>();
	}

	/**
	 * Returns String message.
	 * @return String message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Returns String character used as key when stored into Npc conversation data structure.
	 * @return Single character String
	 */
	public String getMessageKey() {
		return messageKey;
	}

	/**
	 * Returns data sturcture containing event description strings that should be activated if this dialogue is called.
	 * @return ArrayList of event description Strings
	 */
	public ArrayList<String> getActions() {
		return actions;
	}
}
