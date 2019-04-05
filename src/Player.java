import javax.xml.bind.SchemaOutputResolver;
import java.util.ArrayList;

public class Player {
    private String name;
    private String description;

    private ArrayList<Item> items;
    private Graph.Node currentRoom;

    public Player(String name, String description){
        this.name = name;
        this.description = description;
        items = new ArrayList<>();
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

    public ArrayList<Item> getItems(){
        return items;
    }

    public Graph.Node getCurrentRoom(){
        return currentRoom;
    }

    public void setCurrentRoom(Graph.Node newRoom){
        currentRoom = newRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void printCreatures() {
        ArrayList<Creature> list = currentRoom.getCreatures();
        if(list.size() == 0) {
            System.out.print("None");
        }
        else{
            for(Creature c: list){
                System.out.print(c.getName() + " the " + c.getType() + ", ");
            }
        }
    }

}
