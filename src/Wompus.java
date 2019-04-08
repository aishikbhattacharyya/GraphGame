import java.util.ArrayList;

public class Wompus extends Creature {

    public Wompus(String name, String description, Graph.Node currentRoom, Player player){
        super(name, description, currentRoom, player, "wompus");
    }

    public static void move(Graph g){
        Graph.Node playerRoom = player.getCurrentRoom();

        Graph.Node roomToMove = getRoomAwayFromPlayer(playerRoom, g);

        getCurrentRoom().transferCreature(getCurrentRoom(), roomToMove, getName());
    }

    private static Graph.Node getRoomAwayFromPlayer(Graph.Node playerRoom, Graph g) {
        ArrayList<Graph.Node> nonNeighborsToPlayer = playerRoom.getNonNeighbors();

        ArrayList<Graph.Node> myNeighbors = new ArrayList<>(getCurrentRoom().getNeighbors().values());

        if(myNeighbors.size() == 0) return getCurrentRoom();

        for(Graph.Node n: myNeighbors){
            if(!nonNeighborsToPlayer.contains(n)) nonNeighborsToPlayer.remove(n);
        }

        int rand = (int)(nonNeighborsToPlayer.size()*Math.random());
        return nonNeighborsToPlayer.get(rand);
    }
}
