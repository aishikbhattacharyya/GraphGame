import java.util.ArrayList;

public class Wompus extends Creature {

    public Wompus(String name, String description, Graph.Node currentRoom, Player player){
        super(name, description, currentRoom, player, "wompus");
    }

    public void move(Graph g){
        boolean shouldMove = checkToMove();
        if(!shouldMove) return;

        Graph.Node playerRoom = player.getCurrentRoom();
        Graph.Node roomToMove = getRoomAwayFromPlayer(playerRoom);

        if(getCurrentRoom().equals(roomToMove)) return;
        getCurrentRoom().transferCreature(this.getCurrentRoom(), roomToMove, getName());
    }


    private Graph.Node getRoomAwayFromPlayer(Graph.Node playerRoom) {
        ArrayList<Graph.Node> roomsAwayFromPlayer = playerRoom.getRoomsAwayFromMe();
        ArrayList<Graph.Node> myNeighbors = new ArrayList<>(getCurrentRoom().getNeighbors().values());

        ArrayList<Graph.Node> availableRooms = new ArrayList<>();

        if(myNeighbors.size() == 0) return getCurrentRoom();

        for(Graph.Node n: myNeighbors){
            if(roomsAwayFromPlayer.contains(n)) availableRooms.add(n);
        }

        if(availableRooms.size() == 0) return getCurrentRoom();

        int rand = (int)(availableRooms.size()*Math.random());
        return availableRooms.get(rand);
    }
}
