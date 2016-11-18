/**
 * University of Mary Washington
 * CPSC 240 Section 2
 */

/**
 * The Parent class for all sub class commands which will come in via user input
 * and be parsed in the CommandFactory class.
 * 
 * @author Jacques Troussard
 *
 */
abstract class Command {

    abstract String execute();

}
