/**
 * 
 */

/**
 * Command used to list contents of room. +++Not finished+++
 * @author Jacques Troussard
 *
 */
public class LookCommand extends Command {

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
		// TODO Auto-generated method stub
		return GameState.instance().getAdventurersCurrentRoom().getContents().toString();
	}

}
