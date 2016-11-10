/**
 * University of Mary Washington
 * CPSC 240 Section 2
 * DONE
 */

/**
 * @author Jacques Troussard
 *
 */
public class TransformEvent extends Event {

	/**
	 * Primary name of target Item object
	 */
	private String originalItem;
	/**
	 * Primary name of replacement Item object
	 */
	private String newItem;
	
	/**
	 * Takes two String arguments representing the primary names of Item objects. 
	 * originalItem is the name of the target removal item. newItem is the name of the
	 * replacement item.
	 * 
	 * @param originalItem primary name of Item object which is removed from room/inventory
	 * @param newItem primary name of the Item object which will be added to room/inventory
	 */
	public TransformEvent(String originalItem, String newItem) {
		this.originalItem = originalItem;
		this.newItem = newItem;
	}

	/**
	 * This is an Item identifier & player/room/NPC inventory modifier trigger. If the 
	 * arguments are matched to their respective Item objects, then their location is
	 * confirmed to be in the vicinity (ie. player/NPC inventory or current room), if
	 * successful the original item is removed from the game and the new Item is placed
	 * in the same location as the original.
	 * 
	 * @return message of successfulness of transformation
	 */
	@Override
	String trigger() {
		return null;
	}

}
