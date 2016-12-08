import java.util.Scanner;

/**
 * @author Jacques Troussard
 *
 */
public class Interpreter {

	public static String cmdBrd =
			"\n******************************************************************\n";
	public static String brd =
			"------------------------------------------------------------------\n";

	private static GameState state; // not strictly necessary; GameState is
									// singleton

	public static String USAGE_MSG = "Usage: Interpreter borkFile.bork|saveFile.sav.";

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
				System.out.println("\nWelcome to " + state.getDungeon().getName()
						+ "!\n");

				System.out.println(sizeForConsole(
						"Hello adventurer and welcome to the world of Dragon Trainer. "
								+ "You live on a small island in a Nordic archipelago where "
								+ "dragons and other mystical creatures live, for lack of better "
								+ "terms, alongside humans. You’ll begin your adventure in your "
								+ "cozy home at the edge of town. Lately the village has been "
								+ "experiencing lots of dragon attacks originating from a "
								+ "neighboring island, perhaps you can do something about it? "
								+ "Good luck dragon trainer!"));

			} else if (args[0].endsWith(".sav")) {
				state.restore(args[0]);
				System.out.println("\nWelcome back to " + state.getDungeon().getName()
						+ "!");
			} else {
				System.err.println(USAGE_MSG);
				System.exit(2);
			}

			System.out.print(sizeForConsole("\n" + state.getAdventurersCurrentRoom()
					.describe() + "\n"));

			System.out.println("\nEnter 'help' for list of commands");

			System.out.print(brd);
			command = promptUser(commandLine);

			while (!command.equals("q")) {
				// Check win loss conditions before prompting for command
				if (state.getPlayerHealth() <= 0) {
					DieEvent die = new DieEvent();
					die.trigger();
				} else if (state.getPlayerScore() >= 100) {
					WinEvent win = new WinEvent();
					win.trigger();
				}

				if (command.equals("help")){
					System.out.print(CommandFactory.instance().parse(command)
							.execute());
				}else{
					System.out.print(sizeForConsole(CommandFactory.instance().parse(command)
							.execute()));
				}
				

				System.out.print(brd);
				command = promptUser(commandLine);
				GameState.instance().clickTurn();
			}

			System.out.println("Bye!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Helper method used to read user input in main program
	 * 
	 * @param commandLine
	 *            input from user
	 * @return next line awating user input
	 */
	private static String promptUser(Scanner commandLine) {

		System.out.print("> ");
		return commandLine.nextLine();
	}

	public static String sizeForConsole(String text) {
		String[] consoleLength = new String[66];
		String[] parts = text.replaceAll("\n", "%").split(" ");
		String result = "";
		int counter = 0;
		for (String x : parts) {
			char[] word = x.toCharArray();
			if ((counter + word.length) > consoleLength.length || word.toString().equals(
					"%")) {
				result += "\n";
				counter = 0;
			}
			for (char c : word) {
				if (Character.toString(c).equals("%")) {
					result += "\n";
					counter = 0;
				} else {
					result += Character.toString(c);
					counter++;
				}
			}
			result += " ";
			counter += 1;
		}
		return result.substring(0,result.length()-1);
	}

}
