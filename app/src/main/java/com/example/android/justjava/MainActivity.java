package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    public void increment(View view) {
        if (quantity == 100) {

            Toast.makeText(getBaseContext(), "Can not exceed the amount of 100 coffees per 0rder",
                    Toast.LENGTH_LONG).show();
            return;
        }

        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(getBaseContext(), "Can not order less than 1 cup of coffee",
                    Toast.LENGTH_LONG).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }


    /**
     * Calculates the price of the order.
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolateTopping) {
        int basePrice = 5;
        if (hasWhippedCream) {
            basePrice = basePrice + 1;
        }

        if (hasChocolateTopping) {
            basePrice = basePrice + 2;
        }
        int price = quantity * basePrice;
        return price;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int displayQuantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + displayQuantity);
    }

    private String createOrderSummary(int Price, boolean hasWhippedCream, boolean hasChocolateTopping, String hasName) {
        String displayMessage = "Name: " + hasName + "\n";
        displayMessage = displayMessage + "Added Whipped Cream? " + hasWhippedCream;
        displayMessage += "\nHas Cholocate? " + hasChocolateTopping;
        displayMessage = displayMessage + "\nQuantity: " + quantity;
        displayMessage = displayMessage + "\nTotal R: " + Price;
        displayMessage = displayMessage + "\nThank you ";
        return displayMessage;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox whippedCreamTopping = (CheckBox) findViewById(R.id.whippedCreamTopping);
        boolean hasWhippedCream = whippedCreamTopping.isChecked();

        CheckBox chocolateTopping = (CheckBox) findViewById(R.id.chocolateTopping);
        Boolean hasChocolateTopping = chocolateTopping.isChecked();

        EditText inputName = (EditText) findViewById(R.id.nameInput);
        String hasName = inputName.getText().toString();

        int Price = calculatePrice(hasWhippedCream, hasChocolateTopping);

        String priceMessage = createOrderSummary(Price, hasWhippedCream, hasChocolateTopping, hasName);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + hasName);
        intent.putExtra(intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

}