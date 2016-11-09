/**
 * 
 */

/**
 * @author Jacques Troussard
 *
 */
public class DieEvent extends Event {

	/**
	 * This is an end game trigger, in which the player has lost. Die events can be
	 * caused by reaching 0 in either player health or hunger status. This trigger 
	 * +should reload the game from last restore point?
	 * +should reload the game from a fresh game?
	 * +should ask the player if they want to play again?
	 * +should just quit the program?
	 */
	public DieEvent() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Activates the DieEvent.
	 * 
	 * @return end of game message
	 */
	@Override
	String trigger() {
		// TODO Auto-generated method stub
		return null;
	}

}
