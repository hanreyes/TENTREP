package ph.edu.apc.banayad.models;

/**
 * Created by Caranto on 25/08/2017.
 */

public class Item {
    private String mName;
    private String mPrice;
    private String mBarcode;

    public Item() {
        // ..
    }

    public Item(String name, String price, String quantity) {
        this.mName = name;
        this.mPrice = price;
        this.mBarcode = quantity;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public String getmBarcode() {
        return mBarcode;
    }

    public void setmBarcode(String mBarcode) {
        this.mBarcode = mBarcode;
    }
}
