/**
 * Stronghold.java
 *
 * Created by Calvin Wong
 *
 * A class that uses HashMap to hold rooms of castle using names as keys
 */
import java.util.HashMap;
import java.util.Set;

public class Stronghold {

    private HashMap<String, Room> rooms;

    public Stronghold() {
        this.rooms = new HashMap<String, Room>();
    }

    /**
     * Add Room to the Castle
     * @param roomName String
     * @param roomObject Room
     */
    public void addRoom(String roomName, Room roomObject) {
        rooms.put(roomName, roomObject);
    }

    /**
     * Return a Room through the name of the Room
     * @param roomName String
     * @return Room
     */
    public Room getRoom(String roomName) {
        return rooms.get(roomName); 
    }

    public HashMap<String, Room> map() {
        return rooms; 
    }

    public Set<String> keys() {
        return rooms.keySet();
    }

    /**
     * Return the name of each Room
     * with the players in it
     * @return String
     */
    public String displayRooms() {
        String print = "";
        for (String key: keys()) {
            print += rooms.get(key).showPlayers() + "\n";
        }
        return print;
    }

    /**
     * Setup rooms in the castle by passing in a map
     * @param rooms HashMap<String, Room>
     */
    public void setRooms(HashMap<String, Room> rooms) {
        this.rooms = rooms;
    }

    // method to reset players

    /**
     * Reset player's movement to false
     */
    public void reset() {
        for (String key: keys()) {
            for (int i = 0; i < rooms.get(key).getList().size(); i++) {
                rooms.get(key).getList().get(i).setHasMoved(false);
            }
        }
    }

    /**
     * Override of equals method
     * @param o Object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stronghold stronghold = (Stronghold) o;

        if (rooms != null ? !rooms.equals(stronghold.rooms) : stronghold.rooms != null) return false;

        return true;
    }

    /**
     * Calculates the HashCode of the room
     * @return int
     */
    @Override
    public int hashCode() {
        return rooms != null ? rooms.hashCode() : 0;
    }

    /**
     * Override the toString method
     * Return the layout of the Castle with the rooms
     * @return String
     */
    @Override
    public String toString() {
        return "Stronghold{" +
                "rooms=" + rooms +
                '}';
    }
} // end of class