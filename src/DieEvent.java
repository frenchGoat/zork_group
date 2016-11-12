/**
 * University of Mary Washington
 * CPSC 240 Section 2
 * 
 */

/**
 * A global event which ends the game if the players health or hunger status reach
 * zero.
 * 
 * @author Jacques Troussard
 *
 */
public class DieEvent extends Event {

	/**
	 * This is an end game trigger, in which the player has lost. Die events can be
	 * caused by reaching 0 in either player health or hunger status. This trigger 
	 * will reload the game from last restore point.
	 * 
	 */
	public DieEvent() {}

	/**
	 * Activates the DieEvent.
	 * 
	 * @return message The end of game message
	 */
	@Override
	void trigger() {
		// TODO Auto-generated method stub
	}

}
