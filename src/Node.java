import java.util.ArrayList;

public class Node {
    private String name;
    private ArrayList<Node> neighbors;

    public Node(String name) {
        neighbors = new ArrayList<>();
        this.name = name;
    }

    public void addNeighbor(Node n){
        neighbors.add(n);
    }

    public String getNeighborNames(){
        String s = "";

        for(Node n: neighbors){
            s += n.name + " ";
        }
        return s;
    }

    public Node getNeighbor(String name){
        for(Node n: neighbors){
            if(n.name.equals(name)) return n;
        }
        return null;
    }

    public String getName(){
        return name;
    }




}
