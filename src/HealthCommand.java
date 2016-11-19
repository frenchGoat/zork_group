import java.util.Scanner;

/**
 * University of Mary Washington
 * CPSC 240 Section 2
 * 
 */

/**
 * A simple single character command, when invoke will check the players 
 * numerical health status and match it to a corresponding 'fuzzy' message
 * indicating the health condition of the player.
 * 
 * @author Michelle Booth
 * @author Jacques Troussard
 */
class HealthCommand extends Command {
    /**
    * Single character command which when executed alerts the player of their 
    * current health status.
	* 
	*/
   HealthCommand() {}
   
   /**
    * Gets the health status (GameState) and returns the health message
    * @return health status message
    */
   String execute(){
	   int currentHealth = GameState.getPlayerHealth();
	   String healthMessage = "";
	   
	   /*
	    * From the GameState, access the array of health messages and use an
	    * if/else or switch mechanism to match the health int value with the
	    * corresponding health message. return the appropriate health message.
	    */
	   
	   //temp return value used for testing - JT
      return Integer.toString(currentHealth) + "\n";
   } 

}
