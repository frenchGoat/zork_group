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
	 */
	ScoreCommand() {
		this.score = GameState.instance().getPlayerScore();
	}

	/*
	 * Gets the score and returns the rank
	 * 
	 * @return String The rank that corresponds to the score
	 */
	String execute() {
		String scoreMessage = null;
		if (score <= 25) {
			scoreMessage = "Beginning Trainer\nScore:" +score;
		} else if (score > 25 && score <= 50) {
			scoreMessage = "Good Trainer\nScore:" +score;
		} else if (score > 50 && score <= 75) {
			scoreMessage = "Expert Trainer\nScore:" +score;
		} else if (score > 75) {
			scoreMessage = "Master Trainer";
		}
		return scoreMessage + "\n";
	}
}
