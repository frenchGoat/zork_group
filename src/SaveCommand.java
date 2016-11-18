/**
 * 
 * @author Jacques Troussard
 *
 */
class SaveCommand extends Command {

	/**
	 * Header String for default file name when writing to a .sav file.
	 */
    private static String DEFAULT_SAVE_FILENAME = "bork";

    /**
     * .sav file name
     */
    private String saveFilename;

    /**
     * Assigns the saveFilename variable to either the passed argument or the default.
     * 
     * @param saveFilename is defaulted to null
     */
    SaveCommand(String saveFilename) {
        if (saveFilename == null || saveFilename.length() == 0) {
            this.saveFilename = DEFAULT_SAVE_FILENAME;
        } else {
            this.saveFilename = saveFilename;
        }
    }

    /**
     * Calls the store fucntion from GameState instance and passes the saveFilename
     * argument. If successful returns a message describing the file name and save 
     * success. If the store function fails, return a failure message.
     * 
     * @return success/failure message plus file name and extension to be printed to 
     * console for player
     */
    public String execute() {
        try {
            GameState.instance().store(saveFilename);
            return "Data saved to " + saveFilename +
                GameState.SAVE_FILE_EXTENSION + ".\n";
        } catch (Exception e) {
            System.err.println("Couldn't save!");
            e.printStackTrace();
            return "";
        }
    }
}
