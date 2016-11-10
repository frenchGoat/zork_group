
import java.util.Hashtable;
import java.util.Scanner;
/**
 * Every NPC type will extend this class that gives each NPC a name, health, 
 * conversation hashtable, and a current room that is read in from a file. Along with getters for each field.
 *
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
    /**	
     * Health of NPC
     */
    private int health;
    /**
     * Hashtable of NPC conversations
     */
    private Hashtable<String,String> conversation;
    /**
     * Current Room of NPC
     */
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
    * Sets the health of the NPC.
    */
    public viod setHealth() {
	this.health = health;
    }
	
    /**
    * Sets the current room of the NPC.
    */
    public void setCurrentRoom() {
	this.currentRoom = currentRoom;
    }
	
    /**
    * Returns the correct responce from the NPC's conversation Hashtable based off of a String prompt.
    * 
    * @param prompt The user's conversation prompt that will be a key for a response in conversation.
    * @return converse The string response with the key of prompt.
    */
    public String interact(String prompt) {
	    return null;
    }  
    

}
