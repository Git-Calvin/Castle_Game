/**
 * Game_Controller.java
 *
 * Created by Calvin Wong
 *
 * Edit by Catherine Huang
 *
 * Controls the game
 *
 */
import java.util.Random;

public class Game_Controller {

    private Random rand;
    private Room room;
    private boolean check; // check if game is won

    /**
     * Return the Players in each room
     * @return String
     */
    public String showPlayers() {
        //System.out.println(stronghold.displayRooms());
        return stronghold.displayRooms();
    }

    public String showRoom(String name) {
        Room temp = stronghold.getRoom(name);
        return temp.showPlayers();
    }

    private Stronghold stronghold;
    private Room initRoom;

    /**
     * Check if any player meet the condition to win the game
     * @return boolean
     */
    public boolean wasGameWon() {
        return check;
    }

    /**
     * Set check
     */
    public void setCheck(boolean b) {
        this.check = b;
    }

    /**
     * Creates players to enter the castle
     * @param max int
     */
    public void newPlayers(int max) { // takes in number of players

        Player player = null;
        String name = "PLAYER";
        int numberPlayers = rand.nextInt(max) + 1;
        for (int i = 0; i < numberPlayers; i++) { // adds player to treasury room
            name = "PLAYER[" + i + "]";
            player = new Player(name, i, initRoom);
            initRoom.addPlayer(player);
        }
    }

    /**
     * Constructor
     */
    public Game_Controller() {
        rand = new Random();
        room = new Room("");
        stronghold = new Stronghold();
        check = false;
        addRooms();
    }

    /**
     * Move the players from room to room
     * @return
     */
    public String movePlayer() {

        String move = "NEXT MOVE\n";

        stronghold.reset();

        for (String key : stronghold.keys()) {

            room = stronghold.map().get(key);

            if (!room.isEmpty()) {
                while ((room.getList().size() > 0) && !room.getList().get(0).isHasMoved()) {

                    Player player = room.getList().get(0);
                    int num = rand.nextInt(2);

                    if (room.getRightRoom() == null) {
                        room.deletePlayer(player);
                        room.addPlayerToLeft(player);
                        player.setHasMoved(true);
                        move += player.getPlayerName()
                                + " entered the " + room.getLeftName() + "\n";
                    }


                    else if (room.getLeftRoom() == null) {
                        room.deletePlayer(player);
                        room.addPlayerToRight(player);
                        player.setHasMoved(true);
                        move += player.getPlayerName()
                                + " entered the " + room.getRightName() + "\n";
                    }

                    else if (room.aWall()) {
                        return "hit a wall";
                    }

                    else if (num == 0) {
                        room.deletePlayer(player);
                        room.addPlayerToLeft(player);
                        player.setHasMoved(true);
                        move += player.getPlayerName()
                                + " entered the " + room.getLeftName() + "\n";
                    }

                    else {
                        room.deletePlayer(player);
                        room.addPlayerToRight(player);
                        player.setHasMoved(true);
                        move += player.getPlayerName()
                                + " entered the " + room.getRightName() + "\n";
                    }

                    if (!stronghold.map().get("Prison").isEmpty()) {
                        move += player.getPlayerName()
                                + " was attacked by a CRAZY prisoner and died. =(\n";
                        stronghold.map().get("Prison").deletePlayer(player); // delete player
                    }
                                                                    //edit: check if the player is at alter
                    if (!stronghold.map().get("Altar").isEmpty() && player.getCurrentRoom() == stronghold.map().get("Altar")) {
                        move += player.getPlayerName()
                                + " made it to the Altar and killed the King. Glorious Victory! =)\n";
                        check = true; // game winner
                    }
                }
            }
        }
        return move;
    }

    /**
     * Creates the rooms with names, and connecting the rooms.
     */
    public void addRooms() {

        Room academy = new Room("Academy");
        Room altar = new Room("Altar");
        Room prison = new Room("Prison");
        Room market = new Room("Market");
        Room embassy = new Room("Embassy");
        // starting room
        initRoom = new Room("Treasury");
        // set paths
        initRoom.setRooms(academy, market);
        academy.setRooms(prison, market);
        altar.setRooms(market, prison);
        market.setRooms(embassy, prison);
        embassy.setRooms(altar, prison);
        // add rooms
        stronghold.addRoom("Treasury", initRoom);
        stronghold.addRoom("Academy", academy);
        stronghold.addRoom("Altar", altar);
        stronghold.addRoom("Prison", prison);
        stronghold.addRoom("Market", market);
        stronghold.addRoom("Embassy", embassy);
    }
} // end of class