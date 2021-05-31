import java.util.HashMap;
import java.util.Map;

public class Item {
    private String name;
    private String description;
    private int connect_item;

    public Item() {}

    public Item(String name, String description, int connect_item){
        this.name = name;
        this.description = description;
        this.connect_item = connect_item;
    }

    public String getName() {return name;}
    public String getDescription() {return description;}
    public int getConnect_item() {return connect_item;}
}
