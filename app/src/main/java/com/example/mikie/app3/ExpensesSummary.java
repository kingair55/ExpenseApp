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

        Intent intent = getIntent();

        Bundle data = getIntent().getExtras();
        ExpenseItem exp1 = data.getParcelable("expenseItem1");
        ExpenseItem exp2 = data.getParcelable("expenseItem2");
        ExpenseItem exp3 = data.getParcelable("expenseItem3");

        feedItems.add(exp1);
        feedItems.add(exp2);
        feedItems.add(exp3);

        listAdapter = new FeedListAdapter(this, feedItems);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent intent = new Intent(arg0.getContext(), ExpenseDetailActivity.class);
                intent.putExtra("subject", ((TextView) arg1.findViewById(R.id.tvExpenseSubject)).getText());
                intent.putExtra("amount", ((TextView) arg1.findViewById(R.id.tvExpenseAmount)).getText());
                intent.putExtra("description", ((TextView) arg1.findViewById(R.id.tvExpenseDescription)).getText());
                intent.putExtra("status", ((TextView) arg1.findViewById(R.id.tvExpenseStatus)).getText());
                intent.putExtra("date", ((TextView) arg1.findViewById(R.id.tvDate)).getText());
                if (isManager)
                    intent.putExtra("owner", ((TextView) arg1.findViewById(R.id.tvExpenseOwner)).getText());

                startActivity(intent);
            }
        });
    }
    public void approveExpense(View view){
        Intent intent = new Intent(this, ExpensesSummary.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        ExpenseItem exp1 = new ExpenseItem("$46.20", "Lunch with client", "Yadie yadie yada adaka nadsa ad akdba bda dad ab asbdba dsa aa ansabdbaa", ExpenseStatus.Approved, "9 / 25 / 2015", "Mary Jane");
        ExpenseItem exp2 = new ExpenseItem("$30.00", "Travel to airport", "Yadie yadie yada adaka nadsa ad akdba bda dad ab asbdba dsa aa ansabdbaa", ExpenseStatus.Approved, "9 / 1 / 2015", "Joe Bloggs");
        ExpenseItem exp3 = new ExpenseItem("$22.50", "Breakfast off-site", "Yadie yadie yada adaka nadsa ad akdba bda dad ab asbdba dsa aa ansabdbaa", ExpenseStatus.Flagged, "9 / 9 / 2015", "Dirk Nowitzki");

        intent.putExtra("expenseItem1", exp1);
        intent.putExtra("expenseItem2", exp2);
        intent.putExtra("expenseItem3", exp3);

        startActivity(intent);
    }

    public void flagExpense(View view){
        Intent intent = new Intent(this, ExpensesSummary.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        ExpenseItem exp1 = new ExpenseItem("$46.20", "Lunch with client", "Yadie yadie yada adaka nadsa ad akdba bda dad ab asbdba dsa aa ansabdbaa", ExpenseStatus.Approved, "9 / 25 / 2015", "Mary Jane");
        ExpenseItem exp2 = new ExpenseItem("$30.00", "Travel to airport", "Yadie yadie yada adaka nadsa ad akdba bda dad ab asbdba dsa aa ansabdbaa", ExpenseStatus.Flagged, "9 / 1 / 2015", "Joe Bloggs");
        ExpenseItem exp3 = new ExpenseItem("$22.50", "Breakfast off-site", "Yadie yadie yada adaka nadsa ad akdba bda dad ab asbdba dsa aa ansabdbaa", ExpenseStatus.Flagged, "9 / 9 / 2015", "Dirk Nowitzki");

        intent.putExtra("expenseItem1", exp1);
        intent.putExtra("expenseItem2", exp2);
        intent.putExtra("expenseItem3", exp3);

        MyApplication.getContext().startActivity(intent);
    }
}
