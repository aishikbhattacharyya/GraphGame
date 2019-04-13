import java.util.Scanner;

public interface Command {
    public void execute(String command, Player player, Graph g, Scanner scanner);
}
