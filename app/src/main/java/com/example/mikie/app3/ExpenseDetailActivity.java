package com.example.mikie.app3;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ExpenseDetailActivity extends AppCompatActivity {
    private boolean isManager = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isManager) {
            setContentView(R.layout.activity_manager_expense_detail);
        }
        else
        {
            setContentView(R.layout.activity_expense_detail);
        }


        Intent intent = getIntent();
        String subject = intent.getStringExtra("subject");
        String amount = intent.getStringExtra("amount");
        String description = intent.getStringExtra("description");
        String status = intent.getStringExtra("status");
        String date = intent.getStringExtra("date");
        String owner = "";

        TextView tvStatus;
        Button btnApprove;
        Button btnFlag;
        if(isManager) {
            owner = intent.getStringExtra("owner");
            TextView tvOwner = (TextView)findViewById(R.id.owner_id);
            tvOwner.setText(owner);

            btnApprove = (Button)findViewById(R.id.approve_id);
            btnFlag = (Button)findViewById(R.id.flag_id);
            tvStatus = (TextView)findViewById(R.id.status_id);
            if(status.equals("Approved") || status.equals("Flagged"))
            {
                tvStatus.setText(status);
                btnApprove.setVisibility(View.GONE);
                btnFlag.setVisibility(View.GONE);
                tvStatus.setVisibility(View.VISIBLE);
            }
            else{
                btnApprove.setVisibility(View.VISIBLE);
                btnFlag.setVisibility(View.VISIBLE);
                tvStatus.setVisibility(View.GONE);
            }
        }
        else{
            tvStatus = (TextView)findViewById(R.id.status_id);
            tvStatus.setText(status);
        }

        TextView tvDate = (TextView)findViewById(R.id.date_id);
        TextView tvSubject = (TextView)findViewById(R.id.subject_id);
        TextView tvAmount = (TextView)findViewById(R.id.amount_id);
        TextView tvDescription = (TextView)findViewById(R.id.description_id);

        tvDate.setText(date);
        tvSubject.setText(subject);
        tvAmount.setText(amount);
        tvDescription.setText(description);

        tvStatus.setGravity(Gravity.CENTER);
        if(status.equals("Approved"))
        {
            tvStatus.setTextColor(Color.parseColor("#32cd32"));
        }
        else if(status.equals("Pending")){
            tvStatus.setTextColor(Color.parseColor("#000000"));
        }
        else{
            tvStatus.setTextColor(Color.parseColor("#FF0000"));
        }
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
