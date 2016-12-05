/**
 * University of Mary Washington
 * CPSC 240 Section 23
 * DONE 
 */
/**
 * @author Jacques Troussard
 *
 */
class TakeCommand extends Command {

	/**
	 * The primary name of target Item object
	 */
    private String itemName;

    /**
     * Take itemName String as argument and sets it equal to the instance variable 
     * itemName
     * 
     * @param itemName primary name of target Item object
     */
    TakeCommand(String itemName) {
        this.itemName = itemName;
    }

    /**
     * After confirming the itemName variable is not null or empty, checks the players
     * current room location contents array list (via GameState) for the Item object
     * with the primary name matching the itemName. If successful alerts player by
     * returning success message. If the itemName doesn't match any primary item name in
     * the Room's contents db, checks the players inventory for the same item, if 
     * present alerts the player by returning an item is present message. If both
     * actions fail, alerts the player by returning a failure message.
     * 
     * @return String message alerting the player of successfulness of take command
     */
    public String execute() {
        if (itemName == null || itemName.trim().length() == 0) {
            return "Take what?\n";
        }
        try {
            Room currentRoom = 
                GameState.instance().getAdventurersCurrentRoom();
            Item theItem = currentRoom.getItemNamed(itemName);
            	if (itemName.contains("coin")){
            		return ("You added $" + GameState.instance().addMoney(itemName)
            				+ " to your purse.\n");
            	}else {
            		GameState.instance().addToInventory(theItem);
            	}
            currentRoom.remove(theItem);
            return itemName + " taken.\n";
        } catch (Item.NoItemException e) {
            // Check and see if we have this already. If no exception is
            // thrown from the line below, then we do.
            try {
                GameState.instance().getItemFromInventoryNamed(itemName);
                return "You already have the " + itemName + ".\n";
            } catch (Item.NoItemException e2) {
                return "There's no " + itemName + " here.\n";
            }
        }
    }
}
