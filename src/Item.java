import java.util.Scanner;
import java.util.Hashtable;

/**
 * @author Jacques Troussard
 *
 */
public class Item {

    @SuppressWarnings("serial")
	static class NoItemException extends Exception {}

    /**
     * Primary name of item, used in game as id, used in data structures as key.
     */
    private String primaryName;
    
    /**
     * Weight of item.
     */
    private int weight;
    
    /**
     * Hashtable used to store messages and events related to item.
     */
    private Hashtable<String,String> messages;
    
    /**
     * Takes w as a int parameter and assigns it to the instance variable, weight.
     * 
     * @param w amount of space modified when taken into inverntory.
     */
    Item(int w) {
    	this.weight = w;
    }


    Item(Scanner s) throws NoItemException,
        Dungeon.IllegalDungeonFormatException {

        messages = new Hashtable<String,String>();

        // Read item name.
        primaryName = s.nextLine();
        if (primaryName.equals(Dungeon.TOP_LEVEL_DELIM)) {
            throw new NoItemException();
        }

        // Read item weight.
        weight = Integer.valueOf(s.nextLine());

        // Read and parse verbs lines, as long as there are more.
        String verbLine = s.nextLine();
        while (!verbLine.equals(Dungeon.SECOND_LEVEL_DELIM)) {
            if (verbLine.equals(Dungeon.TOP_LEVEL_DELIM)) {
                throw new Dungeon.IllegalDungeonFormatException("No '" +
                    Dungeon.SECOND_LEVEL_DELIM + "' after item.");
            }
            String[] verbParts = verbLine.split(":");
            messages.put(verbParts[0],verbParts[1]);
            
            verbLine = s.nextLine();
        }
    }

    /**
     * Used by parser to confirm whether user command input of an item name is valid 
     * for an existing Item object.
     * 
     * @param name non-primary name string
     * @return boolean confirming whether non-primary name is a valid name for Item
     */
    boolean goesBy(String name) {
        // could have other aliases
        return this.primaryName.equals(name);
    }

    String getPrimaryName() { return primaryName; }

    public String getMessageForVerb(String verb) {
        return messages.get(verb);
    }

    /**
     * Simply returns Item object primary name
     */
    public String toString() {
        return primaryName;
    }
}
