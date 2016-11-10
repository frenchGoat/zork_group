import java.util.Scanner;

/**
 * University of Mary Washington
 * CPSC 240 Section 2
 * 
 */

/**
 * @author Michelle Booth
 * @author Jacques Troussard
 */
public class Keys extends Item {
   /**
   * The exit that needs to be unlocked.
   */
   private Exit unlock;
    /**
    * @param s The scanner of the dungeon file.
    * @throws NoItemException
    * @throws Dungeon.IllegalDungeonFormatException
    */
   Keys(Scanner s) throws NoItemException, Dungeon.IllegalDungeonFormatException,
   		Item.NoItemException {
      super(s);
   
   }
   /**
   * Changes the locked status of the exit and returns a message.
   *
   * @param unlock The exit that needs to be unlocked
   * @return unlocked The string "unlocked" indicated the exit is open.
   */
   String unlockedExit(Exit unlock){
   return "unlocked";
   }
}
