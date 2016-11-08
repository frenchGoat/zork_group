/**
 * 
 */

/**
 * @author Jacques Troussard
 *
 */
public class DisappearEvent extends Event {
	
	/**
	 * Primary name of target item Object. 
	 */

	private String itemName;
	
	/**
	 * This is an item manipulation trigger, in which the target item is removed from the
	 * game completely. This is used in conjuction with transform events as well when
	 * finite items are depleted or broken.
	 * 
	 * @param itemName primary name of Item which is to be removed from game
	 */
	public DisappearEvent(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * Activates the DisappearEvent. 
	 * +Return statement should be tailored to the situation?
	 * ++Tiggering via usage or breaking will alert the user?
	 * ++Transformation triggers remain silent?
	 * 
	 * @return +should this be canned or have multiple results?
	 */
	@Override
	String trigger() {
		// TODO Auto-generated method stub
		return null;
	}

}
