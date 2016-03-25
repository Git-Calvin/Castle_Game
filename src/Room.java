/**
 * Room.java
 *
 * Created by Calvin Wong on 12/02/2014
 *
 */
import java.util.ArrayList;

public class Room {

    private ArrayList <Player> list;
    private String room;
    private Room roomLeft;
    private Room roomRight;

    /**
     * Constructor
     * @param room String
     */
    public Room(String room) {
        this.list = new ArrayList<Player>();
        this.room = room;
        roomLeft = null;
        roomRight = null;
    }

    //Getters and setters
    /**
     * Return a list of players in this room
     * @return List of Player
     */
    public ArrayList<Player> getList() {
        return list;
    }

    /**
     * Set players to the room
     * @param list list of players
     */
    public void setList(ArrayList<Player> list) {
        this.list = list;
    }

    /**
     * Return the name of the current room
     * @return String
     */
    public String getRoom() {
        return room;
    }

    /**
     * Set the name of the current room
     * @param room String
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * Return the name of the room connecting to left
     * @return String
     */
    public String getLeftName() {
        return roomLeft.getRoom();
    }

    /**
     * Return the name of the room connecting to the right
     * @return String
     */
    public String getRightName() {
        return roomRight.getRoom();
    }

    /**
     * Return the room left to the current room
     * @return Room
     */
    public Room getLeftRoom() {
        return roomLeft;
    }

    /**
     * Return the room right to the current room
     * @return Room
     */
    public Room getRightRoom() {
        return roomRight;
    }

    /**
     * Add player to the current room
     * @param player Player
     */
    public void addPlayer(Player player) {
        list.add(player);
        player.setCurrentRoom(this);
    }

    /**
     * Add player to the room to the left of the current room
     * @param player Player
     */
    public void addPlayerToLeft(Player player) {
        roomLeft.addPlayer(player);
        player.setCurrentRoom(roomLeft);
    }

    /**
     * Add player to the room to the right of the current room
     * @param player Player
     */
    public void addPlayerToRight(Player player) {
        roomRight.addPlayer(player);
        player.setCurrentRoom(roomRight);
    }

    // removes a player

    /**
     * Remove a player from the current room
     * by looking for the player object
     * @param player Player
     * @return Player
     */
    public Player deletePlayer(Player player) {
        if (!list.isEmpty()) {
            return list.remove(list.indexOf(player));
        }
        return null;
    }

    /**
     * Remove a player from the current room
     * by entering player's name and player's ID
     * @param playerName String
     * @param playerId int
     * @return Player
     */

    public Player deletePlayer(String playerName, int playerId) {
        Player player = null;
        for (int i = 0; i < list.size(); i++) {
            player = list.get(i);
            if (player.getPlayerName().equals(playerName) && player.getPlayerID() == playerId) {
                list.remove(player);
                break;
            }
            else {
                player = null;
            }
        }
        return player;
    }

    /**
     * Return the players in the current room
     * @return String list of players
     */
    public String showPlayers() {
        String aList = "Player in " + this.getRoom() + ":: \n";
        for (int i = 0; i < list.size(); i++) {
            aList += list.get(i).getPlayerName() + " \n";
        }
        return aList;
    }

    /**
     * Set the Room that are to the left and right
     * of the current room
     * @param leftChoice Room
     * @param rightChoice Room
     */
    public void setRooms(Room leftChoice, Room rightChoice) {
        roomLeft = leftChoice;
        roomRight = rightChoice;
    }

    /**
     * Check if the player has reach a dead end
     * @return boolean
     */
    public boolean aWall() { // hits a wall
        return (roomLeft == null) && (roomRight == null);
    }

    /**
     * Check if there is any player in the room
     * @return boolean
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Override of equals method
     * @param o Object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room room1 = (Room) o;
        if (list != null ? !list.equals(room1.list) : room1.list != null) {
            return false;
        }
        if (room != null ? !room.equals(room1.room) : room1.room != null) {
            return false;
        }
        if (roomLeft != null ? !roomLeft.equals(room1.roomLeft) : room1.roomLeft != null) {
            return false;
        }
        if (roomRight != null ? !roomRight.equals(room1.roomRight) : room1.roomRight != null) {
            return false;
        }

        return true;
    }

    /**
     * Calculate the HashCode for the Room
     * @return int
     */
    @Override
    public int hashCode() {
        int result = list != null ? list.hashCode() : 0;
        result = 31 * result + (room != null ? room.hashCode() : 0);
        result = 31 * result + (roomLeft != null ? roomLeft.hashCode() : 0);
        result = 31 * result + (roomRight != null ? roomRight.hashCode() : 0);
        return result;
    }
} // end of class