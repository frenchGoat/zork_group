/**
 * 
 */

/**
 * @author Jacques Troussard
 *
 */
public class TransformEvent extends Event {

	private String originalItem;
	private String newItem;
	
	/**
	 * @param originalItem String - primary name of Item object which is removed from room/inventory
	 * @param newItem String - primary name of the Item object which will be added to room/inventory
	 */
	public TransformEvent(String originalItem, String newItem) {
		this.originalItem = originalItem;
		this.newItem = newItem;
	}

	/* (non-Javadoc)
	 * @see Event#trigger()
	 */
	@Override
	String trigger() {
		return null;
	}

}
