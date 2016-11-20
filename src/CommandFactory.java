
import java.util.List;
import java.util.Arrays;

/**
 * The CommandFactory Class will take in user commands as strings, parse them and
 * try to determine what the player is trying to accomplish. If parsed commands
 * can be matched to valid sub-command classes, they will be created and executed,
 * possibly causing sub-event classes to be created, but always will return a string
 * to be printed on the console to alert the player as to the results of their 
 * input/actions.
 * 
 * @author Jacques Troussard
 *
 */
public class CommandFactory {

    private static CommandFactory theInstance;
    /**
     * List of possible movement commands which the player may use.
     */
    public static List<String> MOVEMENT_COMMANDS = 
        Arrays.asList("n","w","e","s","u","d" );

    public static synchronized CommandFactory instance() {
        if (theInstance == null) {
            theInstance = new CommandFactory();
        }
        return theInstance;
    }

    private CommandFactory() {
    }

    /**
     * This method receives the user command and attempts to parse it into strings. These
     * Strings are being stored in a simple array. User input is assumed to come in two
     * basic forms. 1) single character ex. 'q' 2) a multiple part command composed of 
     * a verb key word and at least one noun.
     * 
     * @param command string of input received from player
     * @return the specific command to execute
     */
    public Command parse(String command) {
        String parts[] = command.split(" ");
        String verb = parts[0];
        String noun = parts.length >= 2 ? parts[1] : "";
        if (verb.equals("save")) {
            return new SaveCommand(noun);
        }
        if (verb.equals("take")) {
            return new TakeCommand(noun);
        }
        if (verb.equals("drop")) {
            return new DropCommand(noun);
        }
        if (verb.equals("look")) {
        	return new LookCommand();
        }
        if (verb.equals("score")) {
        	return new ScoreCommand();
        }
        if (verb.equals("i") || verb.equals("inventory")) {
            return new InventoryCommand();
        }
        if (verb.equals("h") || verb.equals("health")) { 
        	return new HealthCommand();
        }
        if (MOVEMENT_COMMANDS.contains(verb)) {
            return new MovementCommand(verb);
        }
        if (parts.length == 2) {
            return new ItemSpecificCommand(verb, noun);
        }
        return new UnknownCommand(command);
    }
}
