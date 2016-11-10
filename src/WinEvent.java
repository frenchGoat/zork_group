/**
 * 
 */

/**
 * @author Jacques Troussard
 * @author Michelle Booth
 *
 */
public class WinEvent extends Event {
	/**
	 * This is an end game trigger, in which the player has won.
    * WinEvent will be caused when the player reaches a certian score 
	 */
	
	public WinEvent() {
   
	}

    /**
	 * Activates the WinEvent.
	 * 
	 * @return message The won game message
	 */
	@Override
	String trigger() {
		return winMessage;
	}
}
