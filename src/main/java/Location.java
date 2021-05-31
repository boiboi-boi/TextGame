import java.util.Map;

public class Location {

    private String name;
    private String description;
    private Inventory Inventory;
    private Map <Direction, Location> directions;

    public Location() {}
    public Location(String name, String description, Inventory Inventory) {

        this.name = name;
        this.description = description;
        this.Inventory = Inventory;

    }

    public String getName() {return name;}

    public String getDescription() {return description;}

    public Map<Direction, Location> getDirections() {return directions;}

    public Inventory getInventory() {return Inventory;}

    public void setDirections(Map<Direction,Location> directions){
        this.directions = directions;
    }

    public void setInventory(Inventory inventory){ this.Inventory = inventory;}

    public Location find(Direction direction){
        return directions.get(direction);
    }

}
