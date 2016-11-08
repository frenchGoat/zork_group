/**
 * 
 */

/**
 * @author Jacques Troussard
 *
 */
public class WoundEvent extends Event {
	
	private int damage;
	
	/**
	 * @param damage int - Amount of damage to remove from target's health stat
	 */
	public WoundEvent(int damage) {
		this.damage = damage;
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
