package ph.edu.apc.banayad.other;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ph.edu.apc.banayad.R;

/**
 * Created by Caranto on 25/08/2017.
 */

public class CartHolder extends RecyclerView.ViewHolder {
    private final TextView itemBarcode;
    private final TextView itemName;
    private final TextView itemPrice;

    public CartHolder(View itemView) {
        super(itemView);
        itemBarcode = (TextView) itemView.findViewById(R.id.textViewQuantity);
        itemName = (TextView) itemView.findViewById(R.id.textViewItemName);
        itemPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
    }

    public void setItemBarcode(String quantity) {
        itemBarcode.setText(quantity);
    }

    public void setItemName(String name) {
        itemName.setText(name);
    }

    public void setItemPrice(String price) {
        itemPrice.setText(price);
    }
}
