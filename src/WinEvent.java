import java.io.*;
/**
 * University of Mary Washington
 * CPSC 240 Section 2
 * DONE
 */

/**
 * @author Kaylee Payne
 *
 */
public class WinEvent extends Event {

    /**
     * End of game message.
     */
    private String winMessage = "You have won! Your game has been saved to ";
    /**
     * This is an end game trigger, in which the player has won.
     * WinEvent will be caused when the player reaches a certain score.
     * 
     * @param w win message to print to console
     */
    public WinEvent() {
    }

    /**
     * Activates the WinEvent.
     * 
     * @return message the win game message
     */
    @Override
    void trigger() {
        GameState instance = GameState.instance();
        try {
            instance.store(instance.DEFAULT_SAVE_FILE);
        } catch (IOException e) {}
        System.out.println(winMessage + instance.DEFAULT_SAVE_FILE);
    }
}
