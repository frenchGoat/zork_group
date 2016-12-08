


import java.util.Scanner;
import java.util.regex.Pattern;
/**
 * The companion extends NPC and has an additional field that is an Item gift for the user.
 * 
 * @author Kaylee payne
 */
public class Companion extends NPC {
    /**
     * Gift Item that will be given to the player.
     */
    private int bonus;
    private int attack;
    private GameState instance = GameState.instance();
    /**
    * Creates a Companion NPC with a Item gift.
    *
    * @param name A string name for the NPC.
    */
    Companion(int bonus, String name) {
        super(name);
        this.bonus = bonus;
    }
    
    /**
    * Creates a Companion NPC with a Scanner that reads from a dungeon file.
    *
    * @param scanner The scanner of the dungeon file.
    */
    Companion(Scanner sc) {
        init();
        setName(sc.nextLine());
        setHealth(Integer.parseInt(sc.nextLine()));
        
        String[] messageLine = sc.nextLine().split(Pattern.quote(":"));
        for (String part : messageLine){
            if (!part.equals(MESSAGE_LEAD.substring(0, MESSAGE_LEAD.length()-1))){
                Dialogue incomingDiag = new Dialogue(part);
                getConversations().put(incomingDiag.getMessageKey(), incomingDiag);
            }
            
        }   
        this.attack = sc.nextInt();
        this.bonus = sc.nextInt();
        String bonusType = sc.nextLine();
        if (bonusType.equals("health")) {
            System.out.println("Current health: " + instance.getPlayerHealth());
            instance.setPlayerHealth(instance.getPlayerHealth() + bonus);
            System.out.println("After bonus health: " + instance.getPlayerHealth());
        } else if (bonusType.equals("score")) {
            System.out.println("Current score: " + instance.getPlayerScore());
            instance.setPlayerScore(instance.getPlayerScore() + bonus);
            System.out.println("After bonus score: " + instance.getPlayerScore());
        } else if (bonusType.equals("bank")) {
            System.out.println("Current bank: " + instance.getPlayerBank());
            instance.setPlayerBank(instance.getPlayerBank() + bonus);
            System.out.println("After bonus bank: " + instance.getPlayerBank());
        }
        //throw away delimiter
        sc.nextLine();

    }
    
    public int getBonus() {
       return bonus; 
    }
    
    //     /**
    //      * Returns the gift of this Companion.
    //      * 
    //      * @return gift The gift Item of this Companion.
    //      */
    //     public Item getGift() {
    //         return null;
    //     }
}
