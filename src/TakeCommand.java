import java.util.Scanner;

public class TakeCommand implements Command {
    @Override
    public void execute(String command, Player player, Graph g, Scanner scanner) {
        String itemName = command.substring(5);
        Item item = player.getCurrentRoom().removeItem(itemName);
        if(item == null) System.out.println("Item not in room");
        else {
            player.addItem(item);
            System.out.println(itemName + " has been picked up from the " + player.getCurrentRoom().getName());
        }
    }
}
