import java.util.ArrayList;
import java.util.Random;
import java.math.*;

/**
 * University of Mary Washington
 * CPSC 240 Section 2
 */

/**
 * This event will teleport the player into a random room.
 * 
 * @author Jacques Troussard
 *
 */
public class TeleportEvent extends Event {

	/**
	 * Destination Room object
	 */
	private Room destinationRoom;
	// Following fields not used for play
	/**
	 * Not used for play. Logs actions taken by player.
	 */
	private String logEntry;
	/**
	 * Original room temp variable for log entry.
	 */
	public String fromRoom;

	/**
	 * Takes Room object argument and assigns it to instance variable
	 * destinationRoom.
	 * 
	 * @param destinationRoom
	 *            Room - Destination room.
	 */
	public TeleportEvent(Room destinationRoom) {
		this.destinationRoom = destinationRoom;
		fromRoom = GameState.instance().getAdventurersCurrentRoom().getTitle();
	}
	
	public TeleportEvent() {
		this.destinationRoom = null;
		fromRoom = GameState.instance().getAdventurersCurrentRoom().getTitle();
	}

	/**
	 * This is a player status modifier trigger. The GameState
	 * adventurersCurrentRoom variable is set to the destinationRoom, selected
	 * at random from the teleDests db within the Dungeon object.
	 */
	@Override
	void trigger() {
		/*
		 * If event constructor has been passed a destination parameter the 
		 * GameState's currentAdventurer's location is updated to the parameter.
		 * The condition of a useless teleport (ie. destination location is the same
		 * as currentlocation) will not be check. Without a destination parameter 
		 * possible locations are downloaded from the current Dungeons teleDests db
		 * and a destination is selected by generating a random index number.
		 */
		if (destinationRoom != null) {
			GameState.instance().setAdventurersCurrentRoom(destinationRoom);
		} else {
				ArrayList<Room> randomDestinationList = new ArrayList<Room>(
						GameState.instance().getDungeon().getTeleTable().values());
				do {
					int randomIndex = (int) (Math.random() * randomDestinationList.size());
					destinationRoom = randomDestinationList.get(randomIndex);
					//System.out.println("LOOP RESULTS: " + fromRoom + ">>>" + destinationRoom.getTitle());
				} while (destinationRoom.getTitle().equals(fromRoom));
				GameState.instance().setAdventurersCurrentRoom(destinationRoom);
		} 		
		// log event
		String lead0 = "Event: ";
		String lead1 = "Details: ";
		logEntry = String.format("  %14sTeleport() %n  %14s%s--->%s%n", lead0, lead1, fromRoom,
				destinationRoom.getTitle());
		GameState.instance().logAction(logEntry);

	}
	
	public String getFromRoom() {
		return getFromRoom();
	}

	public static void main(String args[]) {
		// where the player will teleport from
		Room r = new Room("room");
		// will teleport to one of these rooms
		Room t0 = new Room("t0");
		Room t1 = new Room("t1");
		Room t2 = new Room("t2");
		Room xx = new Room("direct teleport room");
		Dungeon d = new Dungeon("test dungeon", r);

		GameState.instance().initialize(d);

		d.add(r);
		d.add(t0);
		d.add(t1);
		d.add(t2);
		d.add(xx);
		d.getTeleTable().remove(xx.getTitle());

		TeleportEvent e0 = new TeleportEvent();		
		e0.trigger();
		TeleportEvent e1 = new TeleportEvent();
		e1.trigger();
		TeleportEvent e2 = new TeleportEvent();		
		e2.trigger();
		TeleportEvent e3 = new TeleportEvent();
		e3.trigger();
		TeleportEvent e4 = new TeleportEvent();		
		e4.trigger();
		TeleportEvent e5 = new TeleportEvent();
		e5.trigger();
		TeleportEvent e6 = new TeleportEvent();		
		e6.trigger();
		TeleportEvent e7 = new TeleportEvent();
		e7.trigger();
		// Direct teleport test
		TeleportEvent e8 = new TeleportEvent(xx);
		e8.trigger();
		// Randomly Teleport out of room not in teleport destination list
		TeleportEvent e9 = new TeleportEvent();
		e9.trigger();
		// Teleport directly out of room not in teleport destination list
		TeleportEvent e00 = new TeleportEvent(r);
		e00.trigger();
		GameState.instance().devPrintGameLog();

	}

}
