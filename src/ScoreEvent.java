/**
 * 
 */

/**
 * @author Jacques Troussard
 *
 */
public class ScoreEvent extends Event {

	private int points;
	
	/**
	 * @param points int - Amount of points to add/update player score with.
	 */
	public ScoreEvent(int points) {
		this.points = points;
	}

	/* (non-Javadoc)
	 * @see Event#trigger()
	 */
	@Override
	String trigger() {
		// TODO Auto-generated method stub
		return null;
	}

}
