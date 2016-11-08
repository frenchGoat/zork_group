/**
 * 
 */

/**
 * @author Jacques Troussard
 *
 */
public class DisappearEvent extends Event {

	private String itemName;
	
	/**
	 * @param itemName String = primary name of Item which is to be removed from game.
	 */
	public DisappearEvent(String itemName) {
		this.itemName = itemName;
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
