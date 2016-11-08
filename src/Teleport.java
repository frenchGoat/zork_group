/**
 * 
 */

/**
 * @author Jacques Troussard
 *
 */
public class Teleport extends Event {

	private Room destinationRoom;
	
	/**
	 * @param destinationRoom Room - Destination room.
	 */
	public Teleport(Room destinationRoom) {
		this.destinationRoom = destinationRoom;
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
