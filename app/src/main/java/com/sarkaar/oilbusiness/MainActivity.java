package com.sarkaar.oilbusiness;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private LinearLayout content;
    private int blue = Color.rgb(20, 65, 110);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showDashboard();
    }

    private TextView text(String value, int size) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(Color.rgb(35, 45, 55));
        t.setPadding(12, 12, 12, 12);
        return t;
    }

    private Button button(String label, Runnable action) {
        Button b = new Button(this);
        b.setText(label);
        b.setAllCaps(false);
        b.setOnClickListener(v -> action.run());
        return b;
    }

    private void setup(String title) {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Color.rgb(245, 247, 250));

        TextView heading = text(title, 23);
        heading.setTextColor(Color.WHITE);
        heading.setBackgroundColor(blue);
        heading.setGravity(Gravity.CENTER_VERTICAL);
        root.addView(heading);

        ScrollView scroll = new ScrollView(this);
        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(12, 12, 12, 20);
        scroll.addView(content);
        root.addView(scroll);

        setContentView(root);
    }

    private void showDashboard() {
        setup("56 Sarkaar Oil Business");

        content.addView(text("Business Accounting Manager", 17));
        content.addView(text(
            "Business Start: 14 October 2026", 15));

        content.addView(button("Purchases / Khareed", 
            () -> showEntry("Purchase")));

        content.addView(button("Sales / Farokht",
            () -> showEntry("Sale")));

        content.addView(button("Stock Management",
            () -> showStock()));

        content.addView(button("Personal Car Oil",
            () -> showEntry("Personal Car Oil")));

        content.addView(button("Business Expenses",
            () -> showEntry("Expense")));

        content.addView(button("Cashbook",
            () -> showEntry("Cash Received")));

        content.addView(button("Customer Ledger",
            () -> showEntry("Customer Payment")));

        content.addView(button("Supplier Ledger",
            () -> showEntry("Supplier Payment")));

        content.addView(button("Daily Report",
            () -> showReport()));

        content.addView(button("Monthly Report",
            () -> showReport()));

        content.addView(text(
            "Initial record (provided):\n" +
            "Purchases: 4,465 litres\n" +
            "Sales: 4,060 litres\n" +
            "Expected stock: 405 litres\n" +
            "Physical stock: 286 litres\n" +
            "Unexplained difference: 119 litres\n\n" +
            "The difference is not automatically a loss.",
            16));
    }

    private void showEntry(String type) {
        setup(type);

        content.addView(text(type + " Entry", 20));

        EditText date = new EditText(this);
        date.setHint("Date (YYYY-MM-DD)");
        content.addView(date);

        EditText litres = new EditText(this);
        litres.setHint("Litres (if applicable)");
        litres.setInputType(8194);
        content.addView(litres);

        EditText rate = new EditText(this);
        rate.setHint("Rate per litre (if applicable)");
        rate.setInputType(8194);
        content.addView(rate);

        EditText amount = new EditText(this);
        amount.setHint("Total amount in PKR");
        amount.setInputType(8194);
        content.addView(amount);

        EditText party = new EditText(this);
        party.setHint("Customer / Supplier / Details");
        content.addView(party);

        content.addView(button("Calculate Total",
            () -> calculate(litres, rate, amount)));

        content.addView(button("Back to Dashboard",
            () -> showDashboard()));

        content.addView(text(
            "Note: This initial screen is a prototype. " +
            "Entries are not yet saved permanently.",
            14));
    }

    private void calculate(EditText litres, EditText rate,
                           EditText amount) {
        try {
            double l = Double.parseDouble(
                litres.getText().toString());
            double r = Double.parseDouble(
                rate.getText().toString());

            double total = l * r;
            amount.setText(String.format(
                java.util.Locale.US, "%.2f", total));

        } catch (Exception e) {
            Toast.makeText(this,
                "Please enter valid litres and rate",
                Toast.LENGTH_SHORT).show();
        }
    }

    private void showStock() {
        setup("Stock Management");

        content.addView(text(
            "Initial Stock Summary\n\n" +
            "Total Purchases: 4,465 L\n" +
            "Total Sales: 4,060 L\n" +
            "Expected Stock: 405 L\n" +
            "Physical Stock: 286 L\n" +
            "Difference to investigate: 119 L\n\n" +
            "Record separately:\n" +
            "• Business oil consumption\n" +
            "• Personal car consumption\n" +
            "• Stock adjustments\n" +
            "• Physical stock counts",
            17));

        content.addView(button("Back",
            () -> showDashboard()));
    }

    private void showReport() {
        setup("Reports");

        content.addView(text(
            "Daily and Monthly Reports\n\n" +
            "Reports will be calculated from saved " +
            "purchases, sales, expenses, payments, " +
            "and stock records in a later step.\n\n" +
            "Profit must account for oil cost, " +
            "expenses, and inventory changes.",
            17));

        content.addView(button("Back",
            () -> showDashboard()));
    }
}
