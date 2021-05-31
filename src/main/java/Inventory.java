import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;


    public Inventory() {items = new ArrayList<Item>();}

    public List<Item> getItems() {return items;}

    void add (Item item) {items.add(item);}

    void remove(Item item) {items.remove(item);}

    void show () {
        int i = 0;
        for (Item item : items){
            System.out.print(item.getName() + " (" +item.getDescription() + ")");
            if ((items.size() -1) != i)
                System.out.println("; ");
            i++;
        }
        System.out.println("");
    }

    public Item find (String inputItem)
    {
        if (items == null)
            return null;
        for (Item item: items) {
            if ((item.getName()).equals(inputItem)) {
                return item;
            }
        }
        return null;
    }

}
