import java.util.Scanner;

/**
 * University of Mary Washington
 * CPSC 240 Section 2
 * 
 */

/**
 * @author Michelle Booth
 */
public class Keys extends Item {
   /**
   * The exit that needs to be unlocked
   */
   private Exit unlock;
    /**
	 * @param s
	 * @throws NoItemException
	 * @throws Dungeon.IllegalDungeonFormatException
	 */
   Keys(Scanner s) throws NoItemException, Dungeon.IllegalDungeonFormatException,
   		Item.NoItemException {
      super(s);
   
   }
   /*
   * Changes the locked status of the exit and returns a message
   * indicating that
   * @param unlock Exit the exit that needs to be unlocked
   * @return String unlocked
   */
   String unlockedExit(Exit unlock){
   return "unlocked";
   } 

}
