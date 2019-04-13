public class Chicken extends Creature {

    public Chicken(String name, String description, Graph.Node currentRoom, Player player){
        super(name, description, currentRoom, player, "chicken");
    }

    public void move(){
        randomizeRoom();
    }
}
