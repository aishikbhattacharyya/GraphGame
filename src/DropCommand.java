import java.util.Scanner;

public class DropCommand implements Command {
    @Override
    public void execute(String command, Player player, Graph g, Scanner scanner) {
        String itemName = command.substring(5);
        Item item = player.removeItem(itemName);
        if(item == null) System.out.println("Item not in room");
        else {
            player.getCurrentRoom().addItem(item);
            System.out.println(itemName + " has been dropped off at the " + player.getCurrentRoom().getName());
        }
    }
}
