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
	 * This method receives an event description which is passed to the factory
	 * by another action/command taken in the game, ie. ItemSpecificCommand or
	 * some action taken during trading/combat/dialog. Then it is parsed it into
	 * strings. These Strings are being stored in a simple array. Event input
	 * will start with a specific event identifier followed by (if needed)
	 * arguments delimited with simple spaces
	 * 
	 * @param eventDescription
	 *            string of eventDescription passed by initiating action
	 * @param target
	 *            target object if any exists
	 * @return specific Event object as per the parsing
	 */
	public Event parse(String target, String eventDescription) {
		String[] parts = eventDescription.split(Pattern.quote("("));
		String event = parts[0];
		String param = parts.length >= 2 ? parts[1].substring(0, parts[1].length()-1) : "";
		switch (event) {
		case "Die":
			return new DieEvent();
		case "Disappear":
			return new DisappearEvent(target);
		case "Lock":
			return new LockedEvent(Boolean.parseBoolean(param));
		case "Score":
			return new ScoreEvent(Integer.parseInt(param));
		case "Teleport":
			return new TeleportEvent();
		case "Transform":
			return new TransformEvent(target, param);
		case "Wound":
			return new WoundEvent(Integer.parseInt(param));
		case "Trade":
			return new TradeEvent(target);
		default:
			return new UnknownEvent(eventDescription);
		}
	}
	
	public Event parse(String eventDescription) {
		return parse(null, eventDescription);
	}

}
