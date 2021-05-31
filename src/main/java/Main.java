import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.logging.Logger;


public class Main {
    private static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {


        String house_description = "Вы находитесь в охотничьем доме, в углу стоит ружье\n" +
                "Неподалеку лестница, ведущая вниз в подвал\n " +
                "На западе дверь, ведущая в лес";
        String forest_description = "Вы находитесь в лесу, в кустах притаился тетерев\n" +
                "На востоке дверь, ведущая в дом охотника";
        String basement_description = "Вы находитесь в подвале, на полке лежат патроны для ружья\n" +
                "Рядом лестница, ведущая вверх в дом охотника";

        Item gun = new Item("Ружье", "Ружье не заряжено", 1);
        Item ammo = new Item("Патроны", "Их можно зарядить в ружье", 1);
        Item bird = new Item("Тетерев", "Оличная добыча для охотника", 2);
        Item loaded_shotgun = new Item("Заряженное ружье", "С ним можно охотиться на дичь", 2);
        Item dead_bird = new Item("Добыча", "", 3);
        Item passport = new Item("Паспорт", "Удостоверение личности", 0);

        Inventory spaw_inventory = new Inventory();
        Inventory house_items = new Inventory();
        Inventory forest_items = new Inventory();
        Inventory basement_items = new Inventory();

        List <Combo> combos = new ArrayList<>();
        Combo gun_N_ammo = new Combo(1,2, loaded_shotgun, "Ружье заряжено");
        Combo gun_N_bird = new Combo(2,3,dead_bird,"Добыча поймана");
        combos.add(gun_N_ammo);
        combos.add(gun_N_bird);

        house_items.add(gun);
        forest_items.add(bird);
        basement_items.add(ammo);
        spaw_inventory.add(passport);

        Location house = new Location("Дом охотника", house_description, house_items);
        Location forest = new Location("Лес", forest_description, forest_items);
        Location basement = new Location("Подвал", basement_description, basement_items);

        house.setInventory(house_items);
        forest.setInventory(forest_items);
        basement.setInventory(basement_items);

        //House Directions//
        Map<Direction, Location> house_directions = new HashMap<Direction, Location>();
        house_directions.put(Direction.DOWN, basement);
        house_directions.put(Direction.WEST, forest);
        house.setDirections(house_directions);

        //Forest Directions//
        Map<Direction, Location> forest_directions = new HashMap<Direction, Location>();
        forest_directions.put(Direction.EAST, house);
        forest.setDirections(forest_directions);

        //Basement Directions//
        Map<Direction, Location> basement_directions = new HashMap<Direction, Location>();
        basement_directions.put(Direction.UP, house);
        basement.setDirections(basement_directions);

        List <Location> locations = new ArrayList<>(Arrays.asList(house, forest, basement));
        log.info("Game is running");
        System.out.println(" Игра \"Охотник\" ");
        Player player = new Player();
        player.setLocation(house);
        player.setInventory(spaw_inventory);

        while (true){
            System.out.println("1. Осмотреться вокруг\n"+ "2. Рюкзак\n" + "3. Перемещаться \n" +
                    "4. Взять объект\n"  + "5. Использовать объекты");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                int i = Integer.parseInt(reader.readLine());
                switch (i) {
                    case (1):
                        player.lookAround();
                        break;
                    case (2):
                        System.out.println("\nВ вашем рюкзаке: ");
                        player.getInventory().show();
                        break;
                    case (3):
                        System.out.println("Идти на ... (Восток, Запад, Север, Юг, Вверх, Вниз)");
                        String temp = reader.readLine();
                        switch (temp) {
                            case ("Восток"):
                                temp = "EAST";
                                break;
                            case ("Запад"):
                                temp = "WEST";
                                break;
                            case ("Юг"):
                                temp = "SOUTH";
                                break;
                            case ("Север"):
                                temp = "NORTH";
                                break;
                            case ("Вверх"):
                                temp = "UP";
                                break;
                            case ("Вниз"):
                                temp = "DOWN";
                                break;
                        }
                        player.go(temp);
                        break;
                    case (4):
                        System.out.println("Название объекта: ");
                        String object = reader.readLine();
                        player.take(object);
                        break;
                    case (5):
                        System.out.println("Название объекта: ");
                        String object1 = reader.readLine();
                        System.out.println("Название субъекта: ");
                        String object2 = reader.readLine();
                        player.use(object1, object2, combos);
                        break;
                }
            }
            catch (NumberFormatException e){
                log.info("Invalid value");
                System.out.println("Введите число от 1 до 5");
            }
            catch (Exception e){
                System.out.println("Попробуй снова");
            }
        }

    }
}
