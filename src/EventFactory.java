/**
 * 
 */

/**
 * @author Jacques Troussard
 *
 */
public class EventFactory {
	
	private static EventFactory theInstance;
	
	/**
	 * @return instance of EventFactory
	 */
	public static synchronized EventFactory instance() {
		if (theInstance == null) {
            theInstance = new EventFactory();
        }
        return theInstance;
	}
	
	private EventFactory() {
	}
	
	/**
	 * This method receives an event description which is passed to the factory by 
	 * another action/command taken in the game, ie. ItemSpecificCommand or some action
	 * taken during trading/combat/dialog. Then it is parsed it into strings. These
     * Strings are being stored in a simple array. Event input will start with a specific
     * event identifier followed by (if needed) arugments delimited with simple spaces
     * 
	 * @param eventDescription string of eventDescription passed by initiating action
	 * @return specific Event object as per the parsing
	 */
	public Event parse(String eventDescription) {
		String parts[] = command.split(" ");
        String verb = parts[0];
        String noun = parts.length >= 2 ? parts[1] : "";
        if (verb.equals("save")) {
            return new SaveCommand(noun);
        }
        if (verb.equals("take")) {
            return new TakeCommand(noun);
        }
        if (verb.equals("drop")) {
            return new DropCommand(noun);
        }
        if (verb.equals("i") || verb.equals("inventory")) {
            return new InventoryCommand();
        }
        if (verb.equals("h") || verb.equals("health")) { 
        	return new HealthCommand();
        }
        if (MOVEMENT_COMMANDS.contains(verb)) {
            return new MovementCommand(verb);
        }
        if (parts.length == 2) {
            return new ItemSpecificCommand(verb, noun);
        }
        return new UnknownCommand(command);
		return new UnknownEvent(eventDescription);
	}

}
