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
   
      int currentHealth = GameState.instance().getPlayerHealth();
      String healthMessage = "";
      if(currentHealth > 50){
         healthMessage = "Healthy";
      }
      else if(currentHealth <= 50){
         healthMessage = "Not doing so great";
      }
      else if(currentHealth <= 25){
         healthMessage = "Warning: Critial conditions!";
      }
   
      /*
       * From the GameState, access the array of health messages and use an
       * if/else or switch mechanism to match the health int value with the
       * corresponding health message. return the appropriate health message.
       */
   
      return healthMessage + "\n";

   } 
}
