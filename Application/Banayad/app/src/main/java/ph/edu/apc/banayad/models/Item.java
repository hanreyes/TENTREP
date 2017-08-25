package ph.edu.apc.banayad.models;

/**
 * Created by Caranto on 25/08/2017.
 */

public class Item {
    private String itemName;
    private String itemPrice;
    private String itemBarcode;

    public Item() {
        // ..
    }

    public Item(String itemName, String itemPrice, String itemQuantity) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemBarcode = itemQuantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemBarcode() {
        return itemBarcode;
    }

    public void setItemBarcode(String itemBarcode) {
        this.itemBarcode = itemBarcode;
    }
}
