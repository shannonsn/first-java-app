package com.example.android.justjava;



import android.os.Bundle;
import java.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * this the the global quantity variable
     */
    int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view){
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
        public void decrement(View view){
        quantity = quantity -1;
        displayQuantity(quantity);
        }


    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice() {
        int price = quantity * 5;
        return price;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int displayQuantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + displayQuantity);
    }

    private String createOrderSummery(int Price){
        String displayMessage = "Name: Shannon \n";
        displayMessage = displayMessage + "Quantity: " + quantity;
        displayMessage = displayMessage + "\nTotal R: " + Price;
        displayMessage = displayMessage + "\nThank you ";
        return displayMessage;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int Price = calculatePrice();
        String priceMessage = createOrderSummery(Price);
        displayMessage(priceMessage);

    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}