/**
 * University of Mary Washington
 * CPSC 240 Section 2
 * 11/08/2016
 */

/**
 * Event sub class which is used to remove items from the game, whether through 
 * use, transformation, or damage.
 * 
 * @author Jacques Troussard
 *
 */
public class DisappearEvent extends Event {
	
	/**
	 * Primary name of target Item Object. 
	 */
	private String itemName;
	/**
	 * Not used for play. Logs actions taken by user.
	 */
	private String logEntry;
	
	/**
	 * This is an item manipulation trigger, in which the target item is removed from the
	 * game completely. This is used in conjunction with transform events as well when
	 * finite items are depleted or broken.
	 * 
	 * @param itemName Item object which is to be removed from game
	 */
	public DisappearEvent(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * Activates the DisappearEvent that returns a set message for breaking items 
	 * or a set message for consuming items.
	 * 
	 * @return message Will either tell if the item is broken or if it has been consumed.
	 * @throws NoItemException 
	 */
	@Override
	void trigger() throws Item.NoItemException {
		/* 
		 * Get item from vicinity and remove from the game. Since Command
		 * sub-classes already check for Item object validity, triggers will not
		 * check.
		 */
		Item targetItem = GameState.instance().getDungeon().getItem(itemName);
		String targetItemOrigin = "";
		// If target item is in player inventory remove it from player inventory.
		if (GameState.instance().isItemInPlayerInventory(itemName)) {
			GameState.instance().removeFromInventory(targetItem);
			targetItemOrigin = "player-inventory";
			//System.out.println("Inventory disappear success!");
			//GameState.instance().devPrintInventory();
			
		// Otherwise remove it from the room.
		} else {
			GameState.instance().getAdventurersCurrentRoom().remove(targetItem);
			targetItemOrigin = GameState.instance().getAdventurersCurrentRoom().getTitle();
			//System.out.println("Room contents disappear success!");
			//GameState.instance().getAdventurersCurrentRoom().devPrintRoomContents();
		}
		/*
		 * Remove the item from play by moving it into an inaccessible db within the 
		 * Dungeon object. At least at Stage 3a, the item will not have to be rehydrated 
		 * from a file it in later stages access, or recreation of the item is necessary.
		 */
		GameState.instance().getDungeon().removeItemFromGame(targetItem);
		String lead0 = "Event: ";
		String lead1 = "Removed From: ";
		logEntry = String.format("  %14sDisappear(%s) %n  %14s%s%n", lead0,itemName,lead1,targetItemOrigin);
		GameState.instance().logAction(logEntry);
	}
	
	// Main method to test DisappearEvent when triggered manually
	public static void main(String args[]) {
		Room r = new Room("room");
		Dungeon d = new Dungeon("test dungeon", r);
		Item i0 = new Item("item0");
		Item i1 = new Item("item1");
		Item i2 = new Item("item2");
		Item i3 = new Item("item3");
		Item i4 = new Item("item4");
		Item i5 = new Item("item5");
		Item i6 = new Item("item6");
		Item i7 = new Item("item7");
		
		d.add(i0);
		d.add(i1);
		d.add(i2);
		d.add(i3);
		d.add(i4);
		d.add(i5);
		d.add(i6);
		d.add(i7);
		
		r.add(i0);
		r.add(i1);
		r.add(i2);
		r.add(i6);
		r.add(i7);
		GameState.instance().initialize(d);
		GameState.instance().addToInventory(i3);
		GameState.instance().addToInventory(i4);
		GameState.instance().addToInventory(i5);
		
		Event e0 = new DisappearEvent("item2");
		Event e1 = new DisappearEvent("item7");
		Event e2 = new DisappearEvent("item5");
		try {
			e0.trigger();
			e1.trigger();
			e2.trigger();
			
		} catch (Item.NoItemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GameState.instance().getDungeon().devPrintAllItems();
		GameState.instance().devPrintGameLog();
		
	}

}
