import Item.NoItemException;

/**
 * University of Mary Washington
 * CPSC 240 Section 2
 */

/**
 * @author Jacques Troussard
 *
 */
abstract class Event {
	/**
	 * All classes that extend event will return a string when triggered.
	 *
	 * @return message A string message corresponding to the specific event.
	 * @throws Item.NoItemException 
	 */
	abstract void trigger() throws Item.NoItemException;
	
}
