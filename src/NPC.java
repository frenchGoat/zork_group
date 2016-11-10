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
     * Health of NPC
     * Hashtable of NPC conversations
     * Current Room of NPC
     */
    private String name;
    private int health;
    private Hashtable conversation;
    private Room currentRoom;
    
    /**
    * Creates an NPC with a string name.
    *
    * @param name A string name for the NPC.
    */
    NPC(String n) {
    	this.name = n;
    }

    /**
    * Creates an NPC with a scanner that reads from the dungeon file.
    *
    * @param scanner The scanner of the Dungeon file.
    */
    NPC(Scanner scanner) {
	    
    }

    /**
    * Returns the name of the NPC.
    *
    * @return name The name of the NPC.
    */
    public String getName() {
	return name;
    }
	
    /**
    * Returns the health of the NPC.
    *
    * @return health The name of the NPC.
    */
    public int getHealth() {
	return health;
    }
	
    /**
    * Returns the current room of the NPC.
    *
    * @return currentRoom The current room of the NPC.
    */
    public Room getCurrentRoom() {
	return currentRoom;
    }
	
    /**
    * Returns the correct responce from the NPC's conversation Hashtable based off of a String prompt.
    * 
    * @param prompt The user's conversation prompt that will be a key for a response in conversation.
    * @return converse The string response with the key of prompt.
    */
    public String getConversation(String prompt) {
	    
    }  
    

}
