/**
 * 
 */

/**
 * @author Jacques Troussard
 *
 */
public class RegenerateEvent extends Event {

	int healthBuff;
	int hungerBuff;
	
	/**
	 * This is a player status trigger. Event structure takes two parsed arguments from
	 * Event Factory and assigns them to their respective instance variables.
	 * 
	 * @param healthBuff amount to add to players health status
	 * @param hungerBuff amount to add to players hunger status
	 */
	public RegenerateEvent(int healthBuff, int hungerBuff) {
		this.healthBuff = healthBuff;
		this.hungerBuff = hungerBuff;
	}

	/**
	 * When triggered will update the players health and hunger status in GameState and
	 * return the appropriate messages to be printed to the console.
	 * 
	 * @return message Health and/or hunger update message.
	 */
	@Override
	String trigger() {
		// TODO Auto-generated method stub
		return message;
	}

}
