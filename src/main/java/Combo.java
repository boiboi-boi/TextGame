public class Combo {

    private int connect_item;
    private int new_connect_item;
    private Item result;
    private String message;

    public Combo( ){ }

    public Combo(int connect_item, int new_connect_item, Item result, String message) {
        this.connect_item = connect_item;
        this.new_connect_item = new_connect_item;
        this.result = result;
        this.message = message;
    }

    public Item getResult() { return result;}
    public String getMessage() {return message;}
    public int getConnectItem() {return connect_item;}
    public int getNew_connect_item() {return  new_connect_item;}
}