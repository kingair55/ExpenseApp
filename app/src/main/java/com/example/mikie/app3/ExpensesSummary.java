package com.example.mikie.app3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ExpensesSummary extends AppCompatActivity {
    private boolean isManager = true;
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
        exp1.Date = "9 / 25 / 2015";
        exp1.Owner = "Mary Jane";

        ExpenseItem exp2 = new ExpenseItem();
        exp2.Amount = "$30.00";
        exp2.Subject = "Travel to airport";
        exp2.Description = "Yadie yadie yada adaka nadsa ad akdba bda dad ab asbdba dsa aa ansabdbaa";
        exp2.Status = ExpenseStatus.Pending;
        exp2.Date = "9 / 1 / 2015";
        exp2.Owner = "Joe Bloggs";

        ExpenseItem exp3 = new ExpenseItem();
        exp3.Amount = "$22.50";
        exp3.Subject = "Breakfast off-site";
        exp3.Description = "Yadie yadie yada adaka nadsa ad akdba bda dad ab asbdba dsa aa ansabdbaa";
        exp3.Status = ExpenseStatus.Flagged;
        exp3.Date = "9 / 9 / 2015";
        exp3.Owner = "Dirk Nowitzki";

        feedItems.add(exp1);
        feedItems.add(exp2);
        feedItems.add(exp3);

        listAdapter = new FeedListAdapter(this, feedItems);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent intent = new Intent(arg0.getContext(), ExpenseDetailActivity.class);
                intent.putExtra("subject", ((TextView)arg1.findViewById(R.id.tvExpenseSubject)).getText());
                intent.putExtra("amount", ((TextView)arg1.findViewById(R.id.tvExpenseAmount)).getText());
                intent.putExtra("description", ((TextView)arg1.findViewById(R.id.tvExpenseDescription)).getText());
                intent.putExtra("status", ((TextView)arg1.findViewById(R.id.tvExpenseStatus)).getText());
                intent.putExtra("date", ((TextView)arg1.findViewById(R.id.tvDate)).getText());
                if(isManager)
                    intent.putExtra("owner", ((TextView)arg1.findViewById(R.id.tvExpenseOwner)).getText());

                startActivity(intent);
            }
        });
    }
}
