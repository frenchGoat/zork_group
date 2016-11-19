import java.util.ArrayList;

/**
 * 
 */

/**
 * Command used to list contents of room. +++Not finished+++
 * @author Jacques Troussard
 *
 */
public class LookCommand extends Command {
	String currentRoom = GameState.instance().getAdventurersCurrentRoom().getTitle();
	ArrayList<Item> contents = GameState.instance().getAdventurersCurrentRoom().getContents();
	String lookLeader = "Looking around ";
	

	/**
	 * 
	 */
	public LookCommand() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see Command#execute()
	 */
	@Override
	String execute() {
		if (contents.isEmpty()) {
			return lookLeader + currentRoom + " you see nothing." + "\n";
		}
		return lookLeader + currentRoom + " you see; " + contents.toString() + "\n";
	}

}
