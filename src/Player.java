/**
 * Player.java
 *
 * Created by Calvin Wong and Catherine Huang on 12/02/2014
 *
 */
public class Player {

    private String playerName;
    private int playerID;
    private Room currentRoom;
    private boolean hasMoved;

    /**
     * Default Constructor
     */
    public Player() {
        this.playerName = "";
        this.playerID = 0;
        this.currentRoom = null;
        this.hasMoved = false;
    }

    /**
     * Constructor
     * @param name String
     * @param number int
     * @param currentRoom Room
     */
    public Player(String name, int number, Room currentRoom) {
        this.playerName = name;
        this.playerID = number;
        this.currentRoom = currentRoom; 
        hasMoved = false; 
    }

    /**
     * Return the name of the player
     * @return String
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Set the name of the player
     * @param playerName String
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Return the player's ID number
     * @return int
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     * Set the player's ID number
     * @param playerID int
     */
    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    /**
     * Return player's current location
     * @return Room
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Set the player's current location
     * @param currentRoom
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Check if the player has moved
     * @return boolean
     */
    public boolean isHasMoved() {
        return hasMoved;
    }

    /**
     * Set the player to moved or not
     * @param hasMoved boolean
     */
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    /**
     * Implementation of hashCode for Player
     * calculates with the player's name and player's ID
     * @return int
     */
    @Override
    public int hashCode() {
        int result = playerName != null ? playerName.hashCode() : 0;
        result = 31 * result + playerID;
        return result;
    }

    /**
     * Implementation of toString method of Player
     * return the HashCode of Player
     * @return String
     */
    @Override
    public String toString() {
        return "Player: " + playerName.hashCode();
    }
} // end of class