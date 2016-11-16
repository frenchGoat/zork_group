/**
 * 
 */

/**
 * This class creates and object which will be used to count events
 * which will allow any mech triggering events to know how many to
 * trigger.
 * 
 * @author Jacques Troussard
 *
 */
public class EventCounter {
	
	/**
	 * Unparsed event detail line
	 */
	String eventString;
	
	public EventCounter(String eventString) {
		this.eventString = eventString;
	}
	
	public int countEvents() {
		int counter = 1;
		for (int i = 0; i<eventString.length(); i++) {
			if (this.eventString.charAt(i)==',') {
					counter ++;
			}
		}
		return counter;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
