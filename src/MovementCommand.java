class MovementCommand extends Command {

	/**
	 * Single character movement command
	 */
    private String dir;
                       

    /**
     * Command structure takes in single character command matched to list of viable 
     * directions, and assigns them to the instance variable.
     * 
     * @param dir single character movement command
     */
    MovementCommand(String dir) {
        this.dir = dir;
    }

    /**
     * When executed, the players current room status in GameState is updated with the
     * appropriate room and the room description is printed to the console if the 
     * following are true; 1) the exist exists 2) the exit is not locked. Otherwise a
     * message is printed to the console and the players location is not updated.
     * 
     * @return movement success/failure message
     */
    public String execute() {
        Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
        Room nextRoom = currentRoom.leaveBy(dir);
        if (nextRoom != null) {  // could try/catch here.
            GameState.instance().setAdventurersCurrentRoom(nextRoom);
            return "\n" + nextRoom.describe() + "\n";
        } else {
            return "You can't go " + dir + ".\n";
        }
    }
}
