/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Gravity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    int quantity = 2;

    private String getName ()
    {
        EditText name = (EditText) findViewById(R.id.name_edit_text);
        String customerName = name.getText().toString();
        return customerName;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox whippedCream = findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolate = findViewById(R.id.chocolate_checkbox);
        boolean hasWhippedCream = whippedCream.isChecked();
        boolean hasChocolate = chocolate.isChecked();
        int price = calculatePrice(hasWhippedCream,hasChocolate);
        String customerName = getName();
        createOrderSummary(price,hasWhippedCream,hasChocolate,customerName);

    }
    public void increment(View view)

    {
        if(quantity<100) {
            quantity = quantity + 1;
            displayQuantity(quantity);
        }
        else
        {
            Toast.makeText(this,"You cannot have more than 100 cups of coffee" , Toast.LENGTH_SHORT).show();


        }
    }
    public void decrement(View view)

    {
        if(quantity>0)
        {quantity=quantity-1;
        displayQuantity(quantity);}
        else
        {
            Toast.makeText(this,"You cannot have less than 1 cup of coffee" , Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }




    private int calculatePrice (boolean hasWhippedCream , boolean hasChocolate)
    {
        int basePrice = 5;
        if (hasWhippedCream)
        {
            basePrice = basePrice+1;
        }

        if (hasChocolate)
        {
            basePrice = basePrice +2;
        }

        int price = quantity*(basePrice);
        return price;
    }

    private void createOrderSummary(int price, boolean hasWhippedCream , boolean hasChocolate, String customerName)

    {
        String summary = "Name: "+customerName+" \n Add Whipped cream? "+ hasWhippedCream +"\n Add chocolate? "+ hasChocolate+ "\n Quantity: "+ quantity+"\n Total: Rs "+price+"\n Thank You!";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order Summary for"+ customerName);
        intent.putExtra(Intent.EXTRA_TEXT, summary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }



}