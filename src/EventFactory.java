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
	 * @return
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
	 * @param eventDescription
	 * @return
	 */
	public Event parseEvent(String eventDescription) {
		return new UnknownEvent(eventDescription);
	}

}
