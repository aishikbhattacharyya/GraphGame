import java.util.ArrayList;

public class Popstar extends Creature {

    public Popstar(String name, String description, Graph.Node currentRoom, Player player){
        super(name, description, currentRoom, player, "popstar");
    }

    public void move(){
        boolean shouldMove = checkToMove();
        if(!shouldMove) return;

        Graph.Node roomToMove = getRoomToPlayer();

        if(getCurrentRoom().equals(roomToMove)) return;
        getCurrentRoom().transferCreature(this.getCurrentRoom(), roomToMove, getName());
    }

    private Graph.Node getRoomToPlayer(){
        ArrayList<Graph.Node> playerNeighbors = new ArrayList(player.getCurrentRoom().getNeighbors().values());
        ArrayList<Graph.Node> myNeighbors = new ArrayList<>(getCurrentRoom().getNeighbors().values());

        ArrayList<Graph.Node> availableRooms = new ArrayList<>();

        if(myNeighbors.size() == 0) return getCurrentRoom();

        for(Graph.Node gn: playerNeighbors){
            if(myNeighbors.contains(gn)) availableRooms.add(gn);
        }

        if(availableRooms.size() == 0) return getCurrentRoom();

        int rand = (int)(availableRooms.size()*Math.random());
        return availableRooms.get(rand);
    }
}
