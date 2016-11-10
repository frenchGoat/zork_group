/**
 * University of Mary Washington
 * CPSC 240 Section 2
 * 11/08/2016
 */

/**
 * @author Michelle Booth
 *
 */
public class LockedEvent extends Event {
   
   /**
   * Locked status of exit
   */

	private boolean locked;
	
	/**
	 * This is an exit manipulation where the locked status of the exit is changed
	 * 
	 * @param locked boolean = the current locked state of the exit
	 */
	public LockedEvent(boolean locked) {
		this.locked = locked;
	}

	/**
	 * Activates the LockedEvent
    * @param Item key name of the key object
	 * @return new locked status
	 */
	String trigger(key Item) {

		return "locked";
	}

}
