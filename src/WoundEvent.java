/**
 * University of Mary Washington
 * CPSC 240 Section 2 
 * DONE
 */

/**
 * @author Jacques Troussard
 *
 */
public class WoundEvent extends Event {
	
	/**
	 * Amount of damage received.
	 */
	private int damage;
	
	/**
	 * Takes int parameter and assigns it to the instance variable, damage.
	 * 
	 * @param damage amount of damage to remove from target's health status
	 */
	public WoundEvent(int damage) {
		this.damage = damage;
	}

	/**
	 * This is a player status modifier. Updates the playerHealth variable (GameState)
	 * by subtracting the damage variable from it.
	 * 
	 * @return message alerting player how much damage was taken
	 */
	@Override
	public void trigger() {
		// TODO Auto-generated method stub
		int playerHealth = GameState.instance().getPlayerHealth();
		GameState.instance().setPlayerHealth(playerHealth - damage);
		
		
	}

}
