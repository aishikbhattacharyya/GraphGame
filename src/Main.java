import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Graph g = new Graph();
        g.addNode("hall", "long and scary");
        g.addNode("closet", "small and stuffy");
        g.addNode("dungeon", "big and crowded");

        g.addDirectedEdge("hall", "dungeon");
        g.addUndirectedEdge("hall", "closet");

        g.addItem("hall", new Item("ball", "round and bouncy"));
        g.addItem("closet", new Item("teddy bear", "soft and fluffy"));

        //Graph.Node current = g.getNode("hall");
        Player player = new Player("Johnny", "Elite");
        player.setCurrentRoom(g.getNode("hall"));

        Chicken chicken = new Chicken("Randy", "fat", g.getNode("hall"), player);
        chicken.move(g);
        Wompus wompus = new Wompus("Charlie", "dompus", g.getNode("closet"), player);


        String response = "";
        Scanner s = new Scanner(System.in);

        do {
            System.out.println("You are in the " + player.getCurrentRoom().getName());
            System.out.println("What do you want to do? >");
            response = s.nextLine();

            int space = response.indexOf(" ");
            String command = response;
            if(space != -1) command = response.substring(0, space);


            if (command.equals("go")) {
                String room = response.substring(3);
                if(g.getNode(room) != null) player.setCurrentRoom(g.getNode(room));
                else System.out.println("Room invalid");
                //current = g.getNode(room);
            } else if (command.equals("look")) {
                System.out.println("Current exits: " + player.getCurrentRoom().getNeighborNames());
                System.out.println("Current items: " + player.getCurrentRoom().displayItems());
                System.out.println(player.getCurrentRoom().getDescription());
                System.out.print("Current Creature(s): ");
                player.printCreatures();
                System.out.println();
            } else if (command.equals("add") && response.contains("room")) {
                String room = response.substring(9);
                System.out.println("Description: ");
                response = s.nextLine();
                g.addNode(room, response);
                g.addDirectedEdge(player.getCurrentRoom().getName(), room);
            } else if (command.equals("quit")) continue;
            else if(command.equals("take")){
                String itemName = response.substring(5);
                Item item = player.getCurrentRoom().removeItem(itemName);
                if(item == null) System.out.println("Item not in room");
                else {
                    player.addItem(item);
                    System.out.println(itemName + " has been picked up from the " + player.getCurrentRoom().getName());
                }
            }
            else if(command.equals("drop")){;
                String itemName = response.substring(5);
                Item item = player.removeItem(itemName);
                if(item == null) System.out.println("Item not in room");
                else {
                    player.getCurrentRoom().addItem(item);
                    System.out.println(itemName + " has been dropped off at the " + player.getCurrentRoom().getName());
                }
            }
            else {
                System.out.println("Commands: go <roomname>, look, add room <roomname> quit");
            }

            chicken.move(g);
            wompus.move(g);

        } while (!response.equals("quit"));

    }
}
