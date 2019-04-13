import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Graph g = constructGraph();

        Player player = new Player("Johnny", "Elite");
        player.setCurrentRoom(g.getNode("hall"));

        Chicken chicken = new Chicken("Randy", "fat", g.getNode("hall"), player);
        chicken.move();
        Wompus wompus = new Wompus("Charlie", "dompus", g.getNode("closet"), player);
        Popstar popstar = new Popstar("Shelly", "stalker", g.getNode("hall"), player);

        ArrayList<Creature> creatures = new ArrayList<>();
        creatures.add(chicken);
        creatures.add(wompus);
        creatures.add(popstar);

        String response = "";
        Scanner s = new Scanner(System.in);

        do {
            System.out.println("You are in the " + player.getCurrentRoom().getName());
            System.out.println("What do you want to do? >");
            response = s.nextLine();

            String command = parseResponse(response);

            executeCommand(response, command, player, g, s);

            moveCharacters(creatures);

            System.out.println("CCC" + wompus.getCurrentRoom().getName());

        } while (!response.equals("quit"));

    }

    private static String parseResponse(String response) {
        int space = response.indexOf(" ");
        String command = response;
        if(space != -1) command = response.substring(0, space);
        return command;
    }

    private static void moveCharacters(ArrayList<Creature> creatures) {
        for(Creature c: creatures){
            c.move();
        }
    }

    private static Graph constructGraph() {
        Graph g = new Graph();
        g.addNode("hall", "long and scary");
        g.addNode("closet", "small and stuffy");
        g.addNode("dungeon", "big and crowded");

        g.addDirectedEdge("hall", "dungeon");
        g.addUndirectedEdge("hall", "closet");

        g.addItem("hall", new Item("ball", "round and bouncy"));
        g.addItem("closet", new Item("teddy bear", "soft and fluffy"));

        return g;
    }


    private static void executeCommand(String response, String command, Player player, Graph g, Scanner s) {
        Command c = null;

        if (command.equals("go")) {
            c = new GoCommand();
        } else if (command.equals("look")) {
            c = new LookCommand();
        } else if (command.equals("add") && response.contains("room")) {
            c = new AddRoomCommand();
        }
        else if(command.equals("take")){
            c = new TakeCommand();
        }
        else if(command.equals("drop")){
            c = new DropCommand();
        }
        else {
            System.out.println("Commands: go <roomname>, look, add room <roomname> quit");
        }

        if(c != null) c.execute(response, player, g, s);
    }
}
