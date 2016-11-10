import java.util.Scanner;

/**
 * The companion extends NPC and has an additional field that is an Item gift for the user.
 * 
 * @author Kaylee payne
 */
public class Companion extends NPC {
    /**
     * Gift Item that will be given to the player.
     */
    private Item gift;
    /**
    * Creates a Companion NPC with a Item gift.
    *
    * @param name A string name for the NPC.
    */
    Companion(Item gift) {
        this.gift = gift;
    }
    
    /**
    * Creates a Companion NPC with a Scanner that reads from a dungeon file.
    *
    * @param scanner The scanner of the dungeon file.
    */
    Companion(Scanner scanner) {
        
    }
    
    /**
     * Returns the gift of this Companion.
     * 
     * @return gift The gift Item of this Companion.
     */
    public Item getGift() {
        
    }
}
