import java.util.Scanner;
/**
 * The Mob extends NPC and has an additional field that is the int cmount of attack.
 * 
 * @author Kaylee payne
 */
public class Mob extends NPC {
    /**
     * Attack level int that determines the amount of damage it can deal.
     */
    private int attack;
    /**
    * Creates a Mob NPC with an int attack.
    *
    * @param attack A int value of damage it can deal.
    */
    Mob(String name, int attack) {
        super(name);
        this.attack = attack;
    }
    
    /**
    * Creates a Mob NPC with a Scanner that reads from a dungeon file.
    *
    * @param scanner The scanner of the dungeon file.
    */
    Mob(Scanner scanner) {
        super(scanner);
    }
    
    /**
     * Returns the attack of this Mob.
     * 
     * @return attack The int value of damage this mob can deal.
     */
    public int getAttack() {
        
    }
}
