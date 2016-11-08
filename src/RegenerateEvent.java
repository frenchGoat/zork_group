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
	 * @param healthBuff int - Amount to add to players health status
	 * @param hungerBuff int - Amount to add to players hunger status
	 */
	public RegenerateEvent(int healthBuff, int hungerBuff) {
		this.healthBuff = healthBuff;
		this.hungerBuff = hungerBuff;
	}

	/* (non-Javadoc)
	 * @see Event#trigger()
	 */
	@Override
	String trigger() {
		// TODO Auto-generated method stub
		return null;
	}

}
