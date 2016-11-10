/**
 * University of Mary Washington
 * CPSC 240 Section 2
 */

/**
 * @author Jacques Troussard
 *
 */
public class TeleportEvent extends Event {

	private Room destinationRoom;
	
	/**
	 * Takes Room object argument and assigns it to instance variable destinationRoom.
	 * 
	 * @param destinationRoom Room - Destination room.
	 */
	public TeleportEvent(Room destinationRoom) {
		this.destinationRoom = destinationRoom;
	}

	/**
	 * This is a player status modifier trigger. The GameState adventurersCurrentRoom
	 * variable is set to the destinationRoom and returns a message alerting the player
	 * of the change.
	 * 
	 * @return message of teleport effects
	 */
	@Override
	String trigger() {
		// TODO Auto-generated method stub
		return null;
	}

}
