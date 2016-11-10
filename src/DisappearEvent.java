/**
 * University of Mary Washington
 * CPSC 240 Section 2
 * 11/08/2016
 */

/**
 * @author Jacques Troussard
 *
 */
public class DisappearEvent extends Event {
	
	/**
	 * Primary name of target Item Object. 
	 */

	private String itemName;
	
	/**
	 * This is an item manipulation trigger, in which the target item is removed from the
	 * game completely. This is used in conjunction with transform events as well when
	 * finite items are depleted or broken.
	 * 
	 * @param itemName primary name of Item which is to be removed from game
	 */
	public DisappearEvent(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * Activates the DisappearEvent that returns a set message for breaking items 
	 * or a set message for consuming items.
	 * 
	 * @return message Will either tell if the item is broken or if it has been consumed.
	 */
	@Override
	String trigger() {
		// TODO Auto-generated method stub.
		return null;
	}

}
