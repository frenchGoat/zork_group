
import java.util.Scanner;
/**
 * The Vendor extends NPC and has an additional field for the currency it carries.
 * It will be able to trade items with the player for set amounts of currency.
 * 
 * @author Kaylee payne
 */
public class Vendor extends NPC {
    /**
     * Money int that determines the amount of money thr vendor possesses.
     */
    private int money;
    /**
    * Creates a Vendor NPC with an int money.
    *
    * @param money A int value of money owned.
    */
    Vendor(String name, int money) {
        super(name);
        this.money = money;
    }
    
    /**
    * Creates a Vendor NPC with a Scanner that reads from a dungeon file.
    *
    * @param scanner The scanner of the dungeon file.
    */
    Vendor(Scanner scanner) {
        super(scanner);
    }
    
    /**
     * Returns the amount of money this Vendor has.
     * 
     * @return money The int value of money this Vendor has.
     */
    public int getMoney() {
        
    }
    
    /**
    * Updates the vendor's money
    *
    * @param amount The amount money will change.
    */
    public void setMoney(int amount) {
        
    }
    
    /**
     * Will return the Item purchesed whent he correct amount of money is given.
     * 
     * @param price The value of the Item the player is buying.
     * @return itemPurchased The Item from the Vendor's inventory that was purchssed.
     */
    public Item trade(int price) {
        
    }
    
    /**
     * Will refund the player the money equal to the value of the Item given.
     * 
     * @param offer The Item the player is giving to the Vendor.
     * @return refund The int amount of money that the offer Item is worth.
     */    
    public int trade(Item offer) {
        
    }
}
