import java.util.Scanner;


/**
 * @author Jacques Troussard
 *
 */
public class Interpreter {
	
	public static String cmdBrd = "\n******************************************************************\n";

    private static GameState state; // not strictly necessary; GameState is 
                                    // singleton

    public static String USAGE_MSG = 
        "Usage: Interpreter borkFile.bork|saveFile.sav.";

    public static void main(String args[]) {

        if (args.length < 1) {
            System.err.println(USAGE_MSG);
            System.exit(1);
        }

        String command;
        Scanner commandLine = new Scanner(System.in);

        try {
            state = GameState.instance();
            System.out.println("                \\||/");
            System.out.println("                |  @___oo");
            System.out.println("      /\\  /\\   / (__,,,,|");
            System.out.println("     ) /^\\) ^\\/ _)");
            System.out.println("     )   /^\\/   _)");
            System.out.println("     )   _ /  / _)");
            System.out.println(" /\\  )/\\/ ||  | )_)");
            System.out.println("<  >      |(,,) )__)");
            System.out.println(" ||      /    \\)___)\\");
            System.out.println(" | \\____(      )___) )___");
            System.out.println(" \\_______(_______;;; __;;;");
            
            if (args[0].endsWith(".bork")) {
                state.initialize(new Dungeon(args[0]));
                System.out.println("\nWelcome to " + 
                    state.getDungeon().getName() + "!");
            } else if (args[0].endsWith(".sav")) {
                state.restore(args[0]);
                System.out.println("\nWelcome back to " + 
                    state.getDungeon().getName() + "!");
            } else {
                System.err.println(USAGE_MSG);
                System.exit(2);
            }
            
            System.out.print("\n" + 
                state.getAdventurersCurrentRoom().describe() + "\n");
            
            System.out.println("Enter 'help' for list of commands");

            command = promptUser(commandLine);

            while (!command.equals("q")) {
            	// Check win loss conditions before prompting for command
            	
            	
            	System.out.print(cmdBrd);
            	System.out.print(
                    CommandFactory.instance().parse(command).execute());

                command = promptUser(commandLine);
                GameState.instance().clickTurn();
            }

            System.out.println("Bye!");

        } catch(Exception e) { 
            e.printStackTrace(); 
        }
    }

    /**
     * Helper method used to read user input in main program
     * 
     * @param commandLine input from user
     * @return next line awating user input
     */
    private static String promptUser(Scanner commandLine) {

        System.out.print("> ");
        return commandLine.nextLine();
    }

}
