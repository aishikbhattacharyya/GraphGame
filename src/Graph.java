import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Graph {
    //private List<Node> nodes;
    private HashMap<String, Node> nodes;

    public Graph(){
        nodes = new HashMap<>();
    }

    public void addNode(String name, String description) {
        Node n = new Node(name, description);
        nodes.put(name, n);
    }

    public void addDirectedEdge(String name1, String name2) {
        Node n1 = getNode(name1);
        Node n2 = getNode(name2);
        n1.addNeighbor(n2);
    }

    public void addUndirectedEdge(String name1, String name2) {
        Node n1 = getNode(name1);
        Node n2 = getNode(name2);
        n2.addNeighbor(n1);
        n1.addNeighbor(n2);
    }

    public Node getNode(String name){
        return nodes.get(name);
    }

    public void addItem(String name, Item item){
        Node n = nodes.get(name);
        n.addItem(item);
    }

    public HashMap<String, Node> getallRooms(){ return nodes; }

    public class Node{
        private String name;
        private HashMap<String, Node> neighbors;
        private String description;
        private ArrayList<Item> items;

        private ArrayList<Creature> creatures;

        public Node(String name, String description) {
            neighbors = new HashMap<>();
            this.name = name;
            this.description = description;
            items = new ArrayList<>();
            creatures = new ArrayList<>();
        }

        public HashMap<String, Node> getNeighbors() { return neighbors; }

        public void addNeighbor(Node n){
            neighbors.put(n.getName(), n);
        }

        public String getNeighborNames(){
            String s = "";

            for(String n: neighbors.keySet()){
                s += n + " ";
            }
            return s;
        }

        public Node getNeighbor(String name){
            return neighbors.get(name);
        }

        public String getName(){
            return name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public ArrayList<Item> getItems(){
            return items;
        }

        public void addItem(Item item){
            items.add(item);
        }

        public Item removeItem(String name){
            for(int i = 0; i < items.size(); i++){
                if(items.get(i).getName().equals(name)){
                    Item item = items.get(i);
                    items.remove(i);
                    return item;
                }
            }
            return null;
        }

        public String displayItems() {
            String output = "";
            for(Item i: items){
                output += i.getName() + " ";
            }
            return output;
        }

        public boolean isNeighbor(Node n){
            String[] neighbors = (String[])(getNeighbors().keySet().toArray());

            for(String s: neighbors){
                if(s.equals(n.getName())) return true;
            }
            return false;
        }

        public ArrayList<Creature> getCreatures() {
            return creatures;
        }

        public void setCreatures(ArrayList<Creature> creatures) {
            this.creatures = creatures;
        }

        public void addCreature(Creature c){
            creatures.add(c);
        }

        public void removeCreature(String c){
            int i = 0;
            for(Creature cr: creatures){
                if(cr.getName().equals(c)) creatures.remove(i);
                i++;
            }
        }

        public void transferCreature(Node n1, Node n2, String name){
            ArrayList<Creature> list1 = n1.getCreatures();
            ArrayList<Creature> list2 = n2.getCreatures();
            Creature c = null;

            for(int i = 0; i < list1.size(); i++){
                Creature curr = list1.get(i);
                if(curr.getName().equals(name)){
                    c = curr;
                    list1.remove(c);
                    break;
                }
            }
            list2.add(c);
            n1.setCreatures(list1);
            n2.setCreatures(list2);
        }

        public ArrayList<Node> getNonNeighbors() {
            ArrayList<Graph.Node> allRoomNames = new ArrayList<>(nodes.values());
            ArrayList<Graph.Node> currRoomNeighbors = new ArrayList<>(neighbors.values());

            ArrayList<Graph.Node> nonNeighbors = new ArrayList<>();

            for(Graph.Node n: allRoomNames){
                if(!currRoomNeighbors.contains(n)) nonNeighbors.add(n);
            }
            return nonNeighbors;
        }
    }
}


