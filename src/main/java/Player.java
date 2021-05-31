import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Player {
    private static Logger log = Logger.getLogger(Main.class.getName());
    private Location location;
    private Inventory inventory;

    public Location getLocation() {return location;}
    public Inventory getInventory() {return inventory;}

    public void lookAround() {
        System.out.println("Локация: " + location.getName());
        System.out.println(location.getDescription());
        System.out.println("\nВокруг себя вы видите: ");
        location.getInventory().show();
    }

    public void setLocation(Location location){this.location = location;}
    public void setInventory(Inventory Inventory){this.inventory = Inventory;}

    public void go (String inputDirection) {

        if(inputDirection == null || inputDirection.isEmpty()){
            System.out.println("В этом направлении тупик!");
            return;
        }
        Direction playerdir;
        playerdir = Direction.valueOf(inputDirection);
        Location loc = location.find(playerdir);
        if(loc != null) {
            location = loc;
        }
        else{
            System.out.println("В этом направлении тупик!");
        }

    }

    public void take (String inputItem) {
        Item item = location.getInventory().find(inputItem);
        if (item == null){
            System.out.println("Предмет отсутствует!");
        }
        else {
            inventory.add(item);
            location.getInventory().remove(item);
            System.out.println("Вы положили " + item.getName() + " рюкзак");
        }
    }

    public void use (String item1, String item2, List<Combo> combos) {
        Item tmp1 = inventory.find(item1);
        if (tmp1 == null){  tmp1 = location.getInventory().find(item1); }
        Item tmp2 = inventory.find(item2);
        if (tmp2 == null) { tmp2 = location.getInventory().find(item2);}
        if (tmp1 == null){  System.out.println(item1 + " - такого объекта нет ни в инвентаре, ни рядом с вами"); return;}
        if (tmp2 == null){  System.out.println(item2 + " - такого объекта нет ни в инвентаре, ни рядом с вами"); return;}

        if (combos == null)
            return ;

        if ((tmp2.getConnect_item())==(tmp1.getConnect_item())) {
            for (Combo combo: combos) {
                if ((combo.getConnectItem()) == (tmp2.getConnect_item())) {
                    inventory.add(combo.getResult());
                    inventory.remove(tmp1);
                    inventory.remove(tmp2);
                    if (combo.getConnectItem()==2){
                        System.out.println(combo.getMessage());
                        System.out.println("ВЫ ВЫИГРАЛИ!");
                        log.info("Game ending");
                        System.exit(0);
                    }else {
                        System.out.println(combo.getMessage());
                        return;
                    }
                }
            }
        }
        else
        {
            log.info("Incorrect selection of objects");
            System.out.println("Объекты не могут взаимодействовать!");
            return;
        }
    }


    void inventory(){
        System.out.println("В ВАШЕМ РЮКЗАКЕ НАХОДИТСЯ: ");
        inventory.show();
    }



}
