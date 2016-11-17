import java.util.regex.Pattern;

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
     * event identifier followed by (if needed) arguments delimited with simple spaces
     * 
	 * @param eventDescription string of eventDescription passed by initiating action
	 * @return specific Event object as per the parsing
	 */
	public Event parse(String eventDescription) {
		String[] parts = eventDescription.split(Pattern.quote("("));
		String event = parts[0];
		String param = parts.length >= 2 ? parts[1] : "";
        if (event.equalsIgnoreCase("die")) {
            return new DieEvent(param);
        }
        if (event.equals("Disappear")) {
            return new DisappearEvent(param);
        }
        if (event.equals("Lock")) {
            return new LockedEvent(param);
        }
        if (event.equals("Score")) {
            return new ScoreEvent(param);
        }
        if (event.equals("Teleport")) {
            return new TeleportEvent(param);
        }
        if (event.equals("Transform")) {
            return new TransformEvent(param);
        }
        if (event.equals("Wound")) {
            return new WoundEvent(param);
        } else {
        	return new UnknownEvent(eventDescription);
        }
	}

}
