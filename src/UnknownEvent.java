/**
 * 
 */

/**
 * @author Jacques Troussard
 *
 */
public class UnknownEvent extends Event {

	/**
	 * Event description String which was parsed and not match to any specific event 
	 * class.
	 */
	private String unknownEventDesc;
	
	/**
	 * Takes unknownEventDesc in as argument and assigns to instance variable, 
	 * unknownEventDesc
	 * 
	 * @param unknownEventDesc event description string 
	 */
	public UnknownEvent(String unknownEventDesc) {
		this.unknownEventDesc = unknownEventDesc;
	}

	/**
	 * This is a special case trigger. No modifications are performed. Returns unknown 
	 * event error.
	 * 
	 * @return unknown event message
	 */
	@Override
	void trigger() {
		GameState.logEntry = "Unsuccessful Event trigger: " + this.unknownEventDesc;
		GameState.instance().logAction(GameState.logEntry);
	}

}
