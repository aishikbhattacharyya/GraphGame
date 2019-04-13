import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public abstract class Creature {
    private Graph.Node currentRoom;
    protected Player player;
    private String name;
    private String description;
    private String type;

    public Creature(String name, String description, Graph.Node currentRoom, Player player, String type){
        this.name = name;
        this.description = description;
        this.currentRoom = currentRoom;
        this.player = player;
        this.type = type;
        currentRoom.addCreature(this);

    }

    public Graph.Node getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Graph.Node currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void move(){};

    public void randomizeRoom(){
         ArrayList<Graph.Node> rooms = new ArrayList<>(currentRoom.getNeighbors().values());
         if(rooms.size() == 0) return;
         int rand = (int)(rooms.size()*Math.random());
         Graph.Node nextRoom = rooms.get(rand);

         if(currentRoom.equals(nextRoom)) return;
         currentRoom.transferCreature(currentRoom, nextRoom, name);

         setCurrentRoom(nextRoom);
    }

    protected boolean checkToMove() {
        ArrayList<Graph.Node> myNeighbors = new ArrayList<>(getCurrentRoom().getNeighbors().values());
        ArrayList<Graph.Node> theNeighborsOfMyNeighbors = new ArrayList<>();

        Graph.Node playerRoom = player.getCurrentRoom();

        for(Graph.Node gn: myNeighbors){
            if(gn.equals(playerRoom)) return true;
            ArrayList<Graph.Node> currNeighbors = new ArrayList<>(gn.getNeighbors().values());
            for(Graph.Node gn1: currNeighbors){
                if(gn1.equals(playerRoom)) return true;
            }
        }

        return false;
    }


}
