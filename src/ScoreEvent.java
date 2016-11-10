/**
 * 
 */

/**
 * @author Jacques Troussard
 *
 */
public class ScoreEvent extends Event {

	/**
	 * Amount of points, parsed from event description.
	 */
	private int points;
	
	/**
	 * Takes the points argument and sets the instance variable to it.
	 *   
	 * @param points amount of points as an integer to add/update player score with.
	 */
	public ScoreEvent(int points) {
		this.points = points;
	}

	/**
	 * This is a player status modifier trigger. The GameState playerScore variable is
	 * updated by adding the points variable to the running total held by playerScore
	 * (GameState)
	 * 
	 * @return message describing the points added and the current score
	 */
	@Override
	String trigger() {
		// TODO Auto-generated method stub
		return null;
	}

}
