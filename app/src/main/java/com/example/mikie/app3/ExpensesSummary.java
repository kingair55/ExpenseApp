package com.example.mikie.app3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ExpensesSummary extends AppCompatActivity {
    ListView listView;
    FeedListAdapter listAdapter;
    List<ExpenseItem> feedItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_summary);

        Spinner spinnerDate = (Spinner) findViewById(R.id.spinner_date);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.dates_array, android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDate.setAdapter(adapter1);

        Spinner spinnerStatus = (Spinner) findViewById(R.id.spinner_status);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.status_array, android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(adapter2);

        listView = (ListView)findViewById(R.id.lv_expenseList);
        feedItems = new ArrayList<ExpenseItem>();

        ExpenseItem exp1 = new ExpenseItem();
        exp1.Amount = "$46.20";
        exp1.Subject = "Lunch with client";
        exp1.Description = "Yadie yadie yada adaka nadsa ad akdba bda dad ab asbdba dsa aa ansabdbaa";
        exp1.Status = ExpenseStatus.Approved;

        ExpenseItem exp2 = new ExpenseItem();
        exp2.Amount = "$30.00";
        exp2.Subject = "Travel to airport";
        exp2.Description = "Yadie yadie yada adaka nadsa ad akdba bda dad ab asbdba dsa aa ansabdbaa";
        exp2.Status = ExpenseStatus.Pending;

        ExpenseItem exp3 = new ExpenseItem();
        exp3.Amount = "$22.50";
        exp3.Subject = "Breakfast off-site";
        exp3.Description = "Yadie yadie yada adaka nadsa ad akdba bda dad ab asbdba dsa aa ansabdbaa";
        exp3.Status = ExpenseStatus.Flagged;

        feedItems.add(exp1);
        feedItems.add(exp2);
        feedItems.add(exp3);

        listAdapter = new FeedListAdapter(this, feedItems);
        listView.setAdapter(listAdapter);
    }
}
