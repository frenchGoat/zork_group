/**
 * University of Mary Washington
 * CPSC 240 Section 2
 * 
 */

/**
 * @author Michelle Booth
 * @author Jacques Troussard
 */
public class ScoreCommand extends Command {
   /**
   * Score of user
   */
   private int score;
    /**
     * Gives the ScoreCommand an int score
     * 
	 * @param score int
	 */
   ScoreCommand(int score){
      this.score = score;
   }
   /*
   * Gets the score and returns the rank
   * 
   * @return String The rank that corresponds to the score
   */
   String execute(){
   String scoreMessage = null;
   if(score >=25){
      scoreMessage = "Beginning Trainer";
   }
   else if(score >=50 && score < 25){
      scoreMessage = "Good Trainer";
      }
   else if(score >=75 && score < 50){
      scoreMessage = "Expert Trainer";
      }
   else if(score <=100 && score > 75){
      scoreMessage = "Master Trainer";
      }
      return "score message";
   } 
}
