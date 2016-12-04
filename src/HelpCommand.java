/**
 * 
 */

/**
 * A simple command to display possible command options to the player
 * 
 * @author Jacques Troussard
 *
 */
public class HelpCommand extends Command {

	/**
	 * 
	 */
	public HelpCommand() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Command#execute()
	 */
	@Override
	String execute() {
		String helpString;
		helpString = "                            HELP SCREEN                             " 
				+ Interpreter.cmdBrd + "MOVEMENT COMMANDS" + Interpreter.cmdBrd + "  n=North\n"
				+ "  e=East\n  s=South\n  w=West\n  u=Up\n  d=Down" + Interpreter.cmdBrd + "OTHER SINGLE CHARACTER"
				+ " COMMANDS" + Interpreter.cmdBrd + "  h=Check player health condition\n  i=Inventory\n"
				+ "  q=Quit game\n  s=Save Game" + Interpreter.cmdBrd + "BASIC COMMANDS" + Interpreter.cmdBrd 
				+ "  +take\n  +drop\n  +look - Use this command by its self to display room exits and contents. \n"
				+ "  +score - Use this command by its self to display player's current score." + Interpreter.cmdBrd
				+ "OTHER GAME NOTES" + Interpreter.cmdBrd + "All test items are examinable/drinkable\n";
		return helpString;
	}

}
