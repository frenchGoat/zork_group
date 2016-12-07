import java.util.ArrayList;

/**
 * @author Jacques Troussard
 *
 */
class InventoryCommand extends Command {

	/**
	 * Single character command 'i'
	 */
    InventoryCommand() {
    }

    /**
     * After confirming player inventory is not empty, prints contents to console.
     * Otherwise a message alerting player their inventory is empty is printed to the 
     * console.
     */
    public String execute() {
    	String retval = "";
    	String balance = "Bank: $" + GameState.instance().getPlayerBank() + "\n";
    	ArrayList<String> names = GameState.instance().getInventoryNames();
        if (names.size() == 0) {
            return balance + retval + "You are empty-handed.\n";
        }
        retval = "You are carrying:\n";
        for (String itemName : names) {
            retval += "   A " + itemName + "\n";
        }
        return balance + retval;
    }
}
