package apc.lee.kyle.testpayment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView m_response;
    static Cart m_cart;
    Intent m_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout list = (LinearLayout) findViewById(R.id.list);

        m_response = (TextView) findViewById(R.id.response);

        m_cart = new Cart();

        Product products[] = {
                new Product("Red Apples", 20.75),
                new Product("Banana", 16.99),
                new Product("Ponkan", 25.75),
                new Product("Orange", 22.25),
                new Product("Green Apples", 37.25),
        };

        for(int i = 0; i<products.length; i++){
            Button button = new Button(this);
            button.setText(products[i].getM_name() + "P" + products[i].getM_value());
            button.setTag(products[i]);

            button.setTextSize(20);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    200, Gravity.CENTER);
            layoutParams.setMargins(20, 50, 20, 50);
            button.setLayoutParams(layoutParams);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button button = (Button) view;
                    Product product = (Product) button.getTag();

                    m_cart.addToCart(product);
                    m_response.setText("Total cart value = " + "P" + String.format("%.2f", m_cart.getM_value()));
                }
            });

            list.addView(button);
        }
    }

    void viewCart(View view){
        Intent i = new Intent(this, ViewCart.class);
        m_cart = m_cart;
        startActivity(i);
    }

    void reset(View view){
        m_response.setText("Total cart value = P0");
        m_cart.empty();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
