class DropCommand extends Command {

	/**
	 * Primary name of target Item object
	 */
    private String itemName;
    
    /**
     * Command structure takes in parsed user input from CommandFactory. If parsed
     * correctly verb is removed and the primary name of targeted Item Object is passed
     * and set to itemName variable.
     * 
     * @param itemName primary name of target Item object
     */
    DropCommand(String itemName) {
        this.itemName = itemName;
    }

    /**
     * After confirming target item name variable is not empty and exists in the players
     * inventory the corresponding Item object is removed from inventory and added to the
     * contents of the players current room.
     * 
     * @return message describing action taken by the player if any
     */
    public String execute() {
        if (itemName == null || itemName.trim().length() == 0) {
            return "Drop what?\n";
        }
        try {
            Item theItem = GameState.instance().getItemFromInventoryNamed(
                itemName);
            GameState.instance().removeFromInventory(theItem);
            GameState.instance().getAdventurersCurrentRoom().add(theItem);
            return itemName + " dropped.\n";
        } catch (Item.NoItemException e) {
            return "You don't have a " + itemName + ".\n";
        }
    }
}
