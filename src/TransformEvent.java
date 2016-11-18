/**
 * University of Mary Washington
 * CPSC 240 Section 2
 * 
 */

/**
 * This Event sub class transforms one object to another.
 * @author Jacques Troussard
 *
 */
public class TransformEvent extends Event {

	/**
	 * Event parameter details
	 */
	private String paramDetails;
	/**
	 * Primary name of target Item object
	 */
	private String targetItem;
	/**
	 * Primary name of replacement Item object
	 */
	private String newItem;
	/**
	 * Not used for play. Logs actions taken by user.
	 */
	private String logEntry;
	
	/**
	 * Takes two String arguments representing the primary names of Item objects. 
	 * originalItem is the name of the target removal item. newItem is the name of the
	 * replacement item.
	 * 
	 * @param targetItem primary name of Item object which is removed from room/inventory
	 * @param newItem primary name of the Item object which will be added to room/inventory
	 */
	
	public TransformEvent(String targetItem, String newItem) {
		this.targetItem = targetItem;
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
	void trigger() throws Item.NoItemException {
		/* 
		 * Find original in vicinity and new item in dungeon. Create temp variables to
		 * hold them as Item objects.
		 */
		Item incomingItem = GameState.instance().getDungeon().getItem(newItem);
		Item outgoingItem = GameState.instance().getItemInVicinityNamed(targetItem);
		
		String targetItemOrigin = "";
		/* If target item is in player inventory remove it from player inventory, and
		 * insert the replacement item giving the impression it has transformed.
		 */
		if (GameState.instance().isItemInPlayerInventory(targetItem)) {
			GameState.instance().addToInventory(incomingItem);
			GameState.instance().removeFromInventory(outgoingItem);
			targetItemOrigin = "player-inventory";
			System.out.println("(If condition) - Inventory disappear success!");
			GameState.instance().devPrintInventory();
			
		// Otherwise perform the pair of operations on the Room object.
		} else {
			GameState.instance().getAdventurersCurrentRoom().add(incomingItem);
			GameState.instance().getAdventurersCurrentRoom().remove(outgoingItem);
			targetItemOrigin = GameState.instance().getAdventurersCurrentRoom().getTitle();
			System.out.println("(Else condition) - Room contents disappear success!");
			GameState.instance().getAdventurersCurrentRoom().devPrintRoomContents();
		}
		/*
		 * Remove the item from play by moving it into an inaccessible db within the 
		 * Dungeon object. At least at Stage 3a, the item will not have to be rehydrated 
		 * from a file it in later stages access, or recreation of the item is necessary.
		 */
		GameState.instance().getDungeon().removeItemFromGame(outgoingItem);
		String lead0 = "Event: ";
		String lead1 = "Details: ";
		logEntry = String.format("  %14sTransformed(%s,%s) %n  %14s%sremoved from %s%n", 
				lead0,targetItem,newItem,targetItem,lead1,targetItemOrigin);
		GameState.instance().logAction(logEntry);
		
	}
	
	public static void main(String args[]) {
		Room r = new Room("room");
		Dungeon d = new Dungeon("test dungeon", r);
		Item i0 = new Item("DrPepper");
		Item i1 = new Item("Empty Soda Can");
		Item i2 = new Item("Match");
		Item i3 = new Item("Burnt Match");
		Item i4 = new Item("Chewing Gum");
		Item i5 = new Item("Gum Wrapper");
		Item i6 = new Item("Blue Moon Beer");
		Item i7 = new Item("Empty Beer Bottle");
		
		d.add(i0);
		d.add(i1);
		d.add(i2);
		d.add(i3);
		d.add(i4);
		d.add(i5);
		d.add(i6);
		d.add(i7);
		
		r.add(i0);
		r.add(i2);
		r.add(i4);
		r.add(i6);
		
		GameState.instance().initialize(d);
		GameState.instance().addToInventory(i0);
		GameState.instance().addToInventory(i2);
		GameState.instance().addToInventory(i4);
		
		Event e0 = new TransformEvent("DrPepper", "Empty Soda Can");
		Event e1 = new TransformEvent("Match", "Burnt Match");
		Event e2 = new TransformEvent("Chewing Gum", "Gum Wrapper");
		Event e3 = new TransformEvent("Blue Moon Beer", "Empty Beer Bottle");
		try {
			e0.trigger();
			e1.trigger();
			e2.trigger();
			e3.trigger();
			
		} catch (Item.NoItemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GameState.instance().getDungeon().devPrintAllItems();
		GameState.instance().devPrintGameLog();

	}

}
