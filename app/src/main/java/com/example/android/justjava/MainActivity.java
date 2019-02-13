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

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        String priceMessage = createOrderSummary(price);

    }


    /**
     * increment function code to increment the number of coffes
     */
    public void incrementOrder(View view) {
        if (quantity > 19) {
            //display in short period of time
            Toast.makeText(getApplicationContext(), "Cant order more than 20 coffe in one go.",
                    Toast.LENGTH_SHORT).show();
            return;
        } else
            quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * decrement method to decrease the number of cups
     */
    public void decrementOrder(View view) {
        if (quantity < 1) {
            //display in short period of time
            Toast.makeText(getApplicationContext(), "Cant order less than 0 coffe in one go.",
                    Toast.LENGTH_SHORT).show();
            return;
        } else
            quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice() {
        int price = quantity * 5;
        boolean isCheckedWhippedCream = false;
        boolean isCheckedChocolate = false;
        CheckBox hasWhippedCream = (CheckBox) findViewById(R.id.checkbox_cream_choice_Whipped_Cream);
        CheckBox hasChocolate = (CheckBox) findViewById(R.id.checkbox_cream_choice_Chocolate);
        if (hasWhippedCream.isChecked()) {
            isCheckedWhippedCream = true;
            price = price + 1;
        }
        if (hasChocolate.isChecked()) {
            isCheckedChocolate = true;
            price = price + 2;
        }
        return price;

    }

    private String createOrderSummary(int price) {
        boolean isCheckedWhippedCream = false;
        boolean isCheckedChocolate = false;
        String priceMessage;
        String name;
        String email  ;
        email = "hawnkmichell@gmail.com";
        CheckBox hasWhippedCream = (CheckBox) findViewById(R.id.checkbox_cream_choice_Whipped_Cream);
        CheckBox hasChocolate = (CheckBox) findViewById(R.id.checkbox_cream_choice_Chocolate);
        EditText Name = (EditText) findViewById(R.id.Name);
        name = Name.getText().toString();
        if (hasWhippedCream.isChecked())
            isCheckedWhippedCream = true;
        if (hasChocolate.isChecked())
            isCheckedChocolate = true;

        if (isCheckedWhippedCream == true && isCheckedChocolate == true) {
            priceMessage = getString(R.string.thanks) + name + "\n "+getString(R.string.with_both)  +"\n"+getString(R.string.the_price_is)+ price;
            displayMessage(priceMessage);
            return priceMessage;
        } else if (isCheckedWhippedCream == true) {
            priceMessage = getString(R.string.thanks)  + name + "\n "+getString(R.string.with_whipped_cream) + "\n"+getString(R.string.the_price_is)+ price ;
            composeEmail(email,priceMessage);
            return priceMessage;
        } else if (isCheckedChocolate == true) {
            priceMessage = getString(R.string.thanks)  + name + "\n "+getString(R.string.with_chocolate) + "\n"+getString(R.string.the_price_is)+ price ;
            composeEmail(email,priceMessage);
            return priceMessage;
        } else {
            priceMessage = getString(R.string.thanks)  + name + "\n "+getString(R.string.with_none) +"\n"+getString(R.string.the_price_is)+ price ;
            composeEmail(email,priceMessage);
            return priceMessage;
        }

    }
    //To send summary to mail
    public void composeEmail(String addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    }
