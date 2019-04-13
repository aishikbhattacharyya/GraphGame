import java.util.Scanner;

public class LookCommand implements Command {
    @Override
    public void execute(String command, Player player, Graph g, Scanner scanner) {
        System.out.println("Current exits: " + player.getCurrentRoom().getNeighborNames());
        System.out.println("Current items: " + player.getCurrentRoom().displayItems());
        System.out.println("Description of current room: " + player.getCurrentRoom().getDescription());
        System.out.print("Current Creature(s): ");
        Graph.printCreatures(player.getCurrentRoom());
        System.out.println();
    }
}
