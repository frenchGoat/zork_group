/**
 * University of Mary Washington
 * CPSC 240 Section 2
 * DONE
 */

/**
 * @author Jacques Troussard
 * @author Michelle Booth
 *
 */
public class WinEvent extends Event {
	
	/**
	 * End of game message.
	 */
	private String winMessage;
	
	/**
	 * This is an end game trigger, in which the player has won.
     * WinEvent will be caused when the player reaches a certain score.
     * 
     * @param w win message to print to console
	 */
	public WinEvent(String w) {
		this.winMessage = w;
   
	}

    /**
	 * Activates the WinEvent.
	 * 
	 * @return message the win game message
	 */
	@Override
	String trigger() {
		return winMessage;
	}
}
