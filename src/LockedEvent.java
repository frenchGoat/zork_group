/**
 * University of Mary Washington
 * CPSC 240 Section 2
 * 11/08/2016
 */

/**
 * @author Michelle Booth
 * @author Jacques Troussard
 *
 */
public class LockedEvent extends Event {

	/**
	 * Locked status of exit
	 */

	private boolean locked;

	/**
	 * This is an exit manipulation where the locked status of the exit is
	 * changed
	 * 
	 * @param locked
	 *            boolean = the current locked state of the exit
	 */
	public LockedEvent(boolean locked) {
		this.locked = locked;
	}

	/**
	 * Activates the LockedEvent
	 * 
	 * @param Item
	 *            key name of the key object
	 * @return new locked status
	 */

	/*
	 * Hi michelle! Your trigger shouldn't take in any arguments. Check out the
	 * Command Factory Pattern, or other events to get a template.
	 */
	String trigger(key Item) {

		return "locked";
	}

}
