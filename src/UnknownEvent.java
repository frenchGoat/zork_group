/**
 * 
 */

/**
 * @author Jacques Troussard
 *
 */
public class UnknownEvent extends Event {

	private String unknownEventDesc;
	
	/**
	 * @param unknownEventDesc String - Event description String which was parsed and not match to any specific event class.
	 */
	public UnknownEvent(String unknownEventDesc) {
		this.unknownEventDesc = unknownEventDesc;
	}

	/* (non-Javadoc)
	 * @see Event#trigger()
	 */
	@Override
	String trigger() {
		return null;
	}

}
