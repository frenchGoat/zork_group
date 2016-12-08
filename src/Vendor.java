
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * The Vendor extends NPC and has an additional field for the currency it
 * carries. It will be able to trade items with the player for set amounts of
 * currency.
 * 
 * @author Kaylee payne
 */
public class Vendor extends NPC {
	static String INV_LEADER = "inventory:";

	/**
	 * Money int that determines the amount of money the vendor possesses.
	 */
	private int money;

	/**
	 * Data structure to hold vendors inventory
	 */
	private LinkedList<String> store;

	/**
	 * Creates a Vendor NPC with an int money.
	 *
	 * @param money
	 *            A int value of money owned.
	 */
	Vendor(String name, int money) {
		super(name);
		this.money = money;
	}

	/**
	 * Creates a Vendor NPC with a Scanner that reads from a dungeon file.
	 *
	 * @param sc
	 *            The scanner of the dungeon file.
	 */
	Vendor(Scanner sc) {
		init();
		store = new LinkedList<String>();
		setName(sc.nextLine());
		setHealth(Integer.parseInt(sc.nextLine()));

		String[] messageLine = sc.nextLine().split(Pattern.quote(":"));
		for (String part : messageLine) {
			if (!part.equals(MESSAGE_LEAD.substring(0, MESSAGE_LEAD.length() - 1))) {
				Dialogue incomingDiag = new Dialogue(part);
				getConversations().put(incomingDiag.getMessageKey(), incomingDiag);
			}

		}

		String[] inventoryLine = sc.nextLine().substring(INV_LEADER.length()).split(Pattern.quote(","));
		for (String part : inventoryLine) {
			store.add(part);
		}
		// throw away delimiter
		sc.nextLine();

	}

	public Vendor(String s) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns the amount of money this Vendor has.
	 * 
	 * @return money The int value of money this Vendor has.
	 */
	public int getMoney() {
		return money;
	}

	public LinkedList<String> getStore() {
		return store;
	}

	/**
	 * Updates the vendor's money
	 *
	 * @param amount
	 *            The amount money will change.
	 */
	public void setMoney(int amount) {

	}

	/**
	 * Will return the Item purchased when the correct amount of money is given.
	 * 
	 * @param price
	 *            The value of the Item the player is buying.
	 * @return itemPurchased The Item from the Vendor's inventory that was
	 *         purchased.
	 */
	public Item trade(int price) {
		return new Item();
	}

	/**
	 * Will refund the player the money equal to the value of the Item given.
	 * 
	 * @param offer
	 *            The Item the player is giving to the Vendor.
	 * @return refund The int amount of money that the offer Item is worth.
	 */
	public int trade(Item offer) {
		return 0;
	}
}
