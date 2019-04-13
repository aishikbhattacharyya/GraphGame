import java.util.Scanner;

public class AddRoomCommand implements Command {
    @Override
    public void execute(String command, Player player, Graph g, Scanner scanner) {
        String room = command.substring(9);
        System.out.println("Description: ");
        command = scanner.nextLine();
        g.addNode(room, command);
        g.addDirectedEdge(player.getCurrentRoom().getName(), room);
    }
}
