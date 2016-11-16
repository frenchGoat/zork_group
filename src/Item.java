import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Hashtable;

/**
 * @author Jacques Troussard
 *
 */
public class Item {

	@SuppressWarnings("serial")
	static class NoItemException extends Exception {
	}

	/**
	 * Primary name of item, used in game as id, used in data structures as key.
	 */
	private String primaryName;
	/**
	 * Weight of item.
	 */
	private int weight;
	/**
	 * Hashtable used to store messages and events related to item.
	 */
	private Hashtable<String, String> messages;
	/**
	 * ArrayList used to store events related to item specific commands. Events
	 * are keyed by their corresponding verb (itemSpecific command). example:
	 * file read in -> drink[Transform(emptyCan), Wound(-1)]:'Generic Message'
	 * would store the Event Transform(emptyCan) keyed to 'drink', and the Event
	 * Wound(-1) keyed to 'drink'. The event factory will be passed this db and
	 * all necessary events keyed to action verb the command factory parsed will
	 * be invoked.
	 */
	private Hashtable<String, String> triggers;
	/**
	 * Takes w as a int parameter and assigns it to the instance variable,
	 * weight.
	 * 
	 * @param w
	 *            amount of space modified when taken into inventory.
	 */
	Item(int w) {
		this.weight = w;
	}

	Item(String n) {
		this.primaryName = n;
	}
	/**
	 * For testing purposes only!
	 */
	Item() {
		init();
	}

	Item(Scanner s) throws NoItemException, Dungeon.IllegalDungeonFormatException {

		messages = new Hashtable<String, String>();
		triggers = new Hashtable<String, String>();

		// Read item name.
		primaryName = s.nextLine();
		if (primaryName.equals(Dungeon.TOP_LEVEL_DELIM)) {
			throw new NoItemException();
		}

		// Read item weight.
		weight = Integer.valueOf(s.nextLine());

		// Read and parse verbs lines, as long as there are more.
		String verbLine = s.nextLine();
		while (!verbLine.equals(Dungeon.SECOND_LEVEL_DELIM)) {
			if (verbLine.equals(Dungeon.TOP_LEVEL_DELIM)) {
				throw new Dungeon.IllegalDungeonFormatException("No '" + Dungeon.SECOND_LEVEL_DELIM + "' after item.");
			}
			String[] verbParts = verbLine.split(":");
			// Does verb have events connected to it?
			if (verbParts[0].contains("[")) {
				String eventLine = verbParts[0]; // verb with event details attached
				String[] eventParts = eventLine.split(Pattern.quote("[")); // separate verb from event details
				// load data structures -  event details are not parsed yet -> specific event or eventFactory's job
				triggers.put(eventParts[0], eventParts[1].substring(0, eventParts[1].length() - 1)); 
				messages.put(eventParts[0], verbParts[1]);
			// Verb without event parts can be added to messages hashtable without edits.
			} else {
				messages.put(verbParts[0], verbParts[1]);
			}
			verbLine = s.nextLine();
		}
	}
	/**
	 * Used by parser to confirm whether user command input of an item name is
	 * valid for an existing Item object.
	 * 
	 * @param name
	 *            non-primary name string
	 * @return boolean confirming whether non-primary name is a valid name for
	 *         Item
	 */
	boolean goesBy(String name) {
		// could have other aliases
		return this.primaryName.equals(name);
	}

	String getPrimaryName() {
		return primaryName;
	}

	public String getMessageForVerb(String verb) {
		return messages.get(verb);
	}
	
	/**
	 * Returns the event detail string related the keyed verb. Can be more than
	 * one event in a single line.
	 * 
	 * @param verb key for hashtable, triggers.
	 * @return the event details string which can contain details for more than one
	 * event
	 */
	public String getEventDetailsForVerb(String verb) {
		return triggers.get(verb);
	}

	/**
	 * Simply returns Item object primary name
	 */
	public String toString() {
		return primaryName;
	}
	
	public boolean hasEvents() {
		return !triggers.isEmpty();
	}
	
	/**
	 * For testing purposes only!
	 */
	public void init() {
		messages = new Hashtable<String, String>();
		triggers = new Hashtable<String, String>();
	}
	
	
	
	
	
	//Item Methods Test
	
	public static void main (String args[]) {
		Item i = new Item();
		i.triggers.put("someVerb","triggerDetails");
		System.out.println(i.hasEvents());
		Item j = new Item();
		System.out.println(j.hasEvents());
	}
}
