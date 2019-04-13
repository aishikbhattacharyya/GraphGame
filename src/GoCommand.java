import java.util.HashMap;
import java.util.Scanner;

public class GoCommand implements Command{

    public void execute(String command, Player player, Graph g, Scanner scanner) {
        String room = command.substring(3);
        HashMap<String, Graph.Node> map = player.getCurrentRoom().getNeighbors();
        if(map.get(room) != null) player.setCurrentRoom(g.getNode(room));
        else System.out.println("Room invalid");
    }
}
