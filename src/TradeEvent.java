import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

public class TradeEvent extends Event {

	NPC dealer;
	int yourBank = GameState.instance().getPlayerBank();
	ArrayList<Item> inv = new ArrayList<Item>();
	String name;
	String menu = "";

	public TradeEvent(String name) {
		this.name = name;
	}

	/**
	 * Helper method used within the openDialogue method. Prompts user for
	 * single character String input which represents a key used to access
	 * Dialogue objects in conversationsdata structure.
	 * 
	 * @param commandLine
	 *            Scanner to read userinput.
	 * @return User input. Should only be a single character String.
	 */
	@SuppressWarnings("unused")
	private static String promptUser(Scanner commandLine) {
		System.out.print("> ");
		return commandLine.nextLine();
	}

	@Override
	void trigger() throws Item.NoItemException {
		dealer = GameState.instance().getDungeon().getNpcs().get(name);
		if (dealer.getConversations().isEmpty()) {
			// silent npc
			String soldOut = " \"Sorry buddy. I'm all out of stock. Try back later.\" ";
			System.out.print(dealer.createSpeechBubble(soldOut));
			System.out.println("  " + dealer.getName().toUpperCase() + "\n");
		} else {
			for (String itemName : dealer.getStore()) {
				Item itemInStore = GameState.instance().getDungeon().getItem(itemName);
				inv.add(itemInStore);
			}

			int listNum = 1;
			String[] lineBuilder = new String[40];
			String[] priceBuilder = new String[6];
			String itemLine = "";
			int counter = 0;

			for (Item i : inv) {
				lineBuilder[counter] = String.valueOf(listNum);
				listNum++;
				counter++;
				lineBuilder[counter] = ")";
				counter++;
				lineBuilder[counter] = " ";
				counter++;
				char[] name = i.getPrimaryName().toCharArray();

				for (char c : name) {
					lineBuilder[counter] = Character.toString(c);
					counter++;
				}

				if (counter % 2 != 0 && counter < lineBuilder.length) {
					lineBuilder[counter] = "";
					counter++;
				}

				while (counter < lineBuilder.length) {
					lineBuilder[counter] = ".";
					counter++;
				}

				counter = 0;
				char[] price = String.valueOf((int) (i.getValue() * 2.5)).toCharArray();
				for (char c : price) {
					priceBuilder[counter] = Character.toString(c);
					counter++;
				}

				while (counter < priceBuilder.length) {
					priceBuilder[counter] = " ";
					counter++;
				}

				for (String x : lineBuilder) {
					itemLine += x;
				}
				for (String x : priceBuilder) {
					itemLine += x;
				}

				menu += itemLine + "\n";
				itemLine = "";
				counter = 0;

			}

			Scanner option = new Scanner(System.in);
			String command = "new";
			LinkedList<String> options = new LinkedList<String>();
			String selection = "";
			listNum = listNum - 1;
			while (listNum > 0) {
				options.add(String.valueOf(listNum));
				listNum--;
			}

			String buyLine =
					"What do you want to buy? Enter the line number or 'x' to leave trading";
			while (!command.equals("x")) {
				System.out.println(menu);
				System.out.println(buyLine);
				command = promptUser(option);
				boolean flag = false;
				for (String s : options) {
					if (s.equals(command)) {
						flag = true;
					}
				}
				if (!command.equals("x")) {
					selection = String.valueOf(Integer.valueOf(command) - 1);
				}

				if (flag) {
					Item itemToBuy = inv.get(Integer.valueOf(selection));
					int cost;
					int newTotal;
					if (GameState.instance().getPlayerBank() >= itemToBuy.getValue()
							* 2.5) {
						cost = (int) (itemToBuy.getValue() * 2.5);
						System.out.println(NPC.createSpeechBubble("SOLD!"));
						System.out.println("  " + Room.ANSI_CYAN + this.name.toUpperCase() + Room.ANSI_RESET + "\n");
						GameState.instance().addToInventory(itemToBuy);
						newTotal = yourBank - cost;
						GameState.instance().setPlayerBank(newTotal);
						command = "x";
					} else {
						System.out.println(NPC.createSpeechBubble(
								"Sorry Bub. Come back when you have some more scratch."));
						System.out.println("  " + Room.ANSI_CYAN + this.name.toUpperCase() + Room.ANSI_RESET + "\n");
						command = "x";
					}
				} else {
					if (command.equals("x")) {
						System.out.println(NPC.createSpeechBubble(
								"Come find me if you need anything."));
						System.out.println("  " + Room.ANSI_CYAN + this.name.toUpperCase() + Room.ANSI_RESET + "\n");
					} else {
						System.out.println(NPC.createSpeechBubble(
								"Just stick to the menu, alright buddy?"));
						System.out.println("  " + Room.ANSI_CYAN + this.name.toUpperCase() + Room.ANSI_RESET + "\n");
					}
				}
			}

		}
	}
}
