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
		String hr = "******************************************************************";
		String title0  = center("HELP SCREEN", 66, " ");
		String title1  = String.format("%1$-30s %2$30s","MOVEMENT COMMANDS", "OTHER SINGLE CHARACTER COMMANDS");
		String t1Line0 = String.format("%1$-30s %2$30s","n=North", "h=Check player health condition");
		String t1Line1 = String.format("%1$-30s %2$30s","e=East", "i=Inventory");
		String t1Line2 = String.format("%1$-30s %2$30s","s=South", "q=Quit game");
		String t1Line3 = String.format("%1$-30s %2$30s","w=West", "s=Save game");
		String t1Line4 = String.format("%1$-30s", "u=Up"); 
		String t1Line5 = String.format("%1$-30s","d=Down");
		String title2  = center("BASIC COMMANDS", 66, " ");
		String cmd0 = String.format("%1$-10s %2$-15s %3$s", "+talk to","<NPC NAME>","- Brings up dialogue options.");
		String cmd1 = String.format("%1$-10s %2$-15s %3$s", "+take","<ITEM NAME>","- Adds item to inventory");
		String cmd2 = String.format("%1$-10s %2$-15s %3$s", "+drop","<ITEM NAME>","- Removes item from inventory, and drops in room.");
		String cmd3 = String.format("%1$-10s %2$-15s %3$s", "+look","<>","- Display room exits and contents.");
		String cmd4 = String.format("%1$-10s %2$-15s %3$s", "+score","<>","- Display player's current score.");
		String title3 = center("OTHER GAME NOTES", 66, "");
		String notes = "All test items are examinable/drinkable";
		String helpString;
		
		helpString = 
				title0 + "\n" +
				hr     + "\n" +
				title1 + "\n" +
				hr     + "\n" +
				t1Line0+ "\n" +
				t1Line1+ "\n" +
				t1Line2+ "\n" +
				t1Line3+ "\n" +
				t1Line4+ "\n" +
				hr     + "\n" +
				title2 + "\n" +
				hr     + "\n" +
				cmd0   + "\n" +
				cmd1   + "\n" +
				cmd2   + "\n" +
				cmd3   + "\n" +
				cmd4   + "\n" +
				hr     + "\n" +
				title3 + "\n" +
				hr     + "\n" +
				notes  + "\n";
		return helpString;
	}
	
	public String center(String text, int length, String edge) {
		String result; // String to build as retrun value.
		String[] rez; // Pre-result in array data type.
		char[] rawTextAsChar; // raw param as array of chars (text param)
		int lineLength; // Total length of print line
		int size; // Count of how many characters are in para text
		int startingIndex; // Where the characters from text will be indexed first
		String border; // Single character String to act as border

		// Set some initial variables
		rawTextAsChar = text.toCharArray();
		size = text.length();
		lineLength = length;
		startingIndex = (int) lineLength / 2;
		rez = new String[lineLength];
		border = edge;
		
		// Even out size of parameter length
		if (size % 2 != 0) {
			size++;
		}

		// Determine starting index and load into pre=result array
		startingIndex = (int) startingIndex - size / 2;
		for (char c : rawTextAsChar) {
			rez[startingIndex] = Character.toString(c);
			startingIndex++;
		}

		// Set borders
		rez[0] = border;
		rez[lineLength - 1] = border;

		// Create counter helper to step through rez array and check for missing
		// spaces
		int counter = 0;
		result = "";

		for (String x : rez) {
			if (x == null) {
				rez[counter] = " ";
			}
			result += rez[counter];
			counter++;
		}

		return result;
	}
}
