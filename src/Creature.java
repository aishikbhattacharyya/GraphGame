import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public abstract class Creature {
    private static Graph.Node currentRoom;
    protected static Player player;
    private static String name;
    private static String description;
    private static String type;

    public Creature(String name, String description, Graph.Node currentRoom, Player player, String type){
        this.name = name;
        this.description = description;
        this.currentRoom = currentRoom;
        this.player = player;
        this.type = type;
        currentRoom.addCreature(this);

    }

    public static Graph.Node getCurrentRoom() {
        return currentRoom;
    }

    public static void setCurrentRoom(Graph.Node currentRoom) {
        Creature.currentRoom = currentRoom;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Creature.name = name;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription(String description) {
        Creature.description = description;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        Creature.type = type;
    }

    public static void move(Graph.Node nextRoom){};

    public static void randomizeRoom(Graph g){
         ArrayList<Graph.Node> rooms = new ArrayList<>(currentRoom.getNeighbors().values());
         int rand = (int)(rooms.size()*Math.random());
         Graph.Node nextRoom = rooms.get(rand);

         currentRoom.transferCreature(currentRoom, nextRoom, name);

         setCurrentRoom(nextRoom);
    }


}
