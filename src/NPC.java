/**
 * 
 */

/**
 * @author Kaylee Payne
 * @author Jacques Troussard
 *
 */
public class NPC {
	
    @SuppressWarnings("serial")
	static class NoNPCException extends Exception {}
    
    /**
     * Name of NPC
     */
    private String name;
    
    NPC(String n) {
    	this.name = n;
    }

	public String getName() {
		return name;
	}
    
    

}
