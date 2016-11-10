/**
 * @author Jacques Troussard
 *
 */
class UnknownCommand extends Command {

	/**
	 * User input that wasn't parsed into a predefined command
	 */
    private String bogusCommand;

    /**
     * Takes a string argument and assigns it to the instance variable, bogusCommand.
     * @param bogusCommand
     */
    UnknownCommand(String bogusCommand) {
        this.bogusCommand = bogusCommand;
    }

    /**
     * Prints error message alerting the player that their command could not be parsed
     * into a valid command.
     * 
     * @return error message
     */
    String execute() {
        return "I'm not sure what you mean by \"" + bogusCommand + "\".\n";
    }
}
