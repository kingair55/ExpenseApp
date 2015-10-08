package com.example.mikie.app3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mikie on 07/10/2015.
 */
public class FeedListAdapter extends BaseAdapter {
    private boolean isManager = true;
    private Activity activity;
    private LayoutInflater inflater;
    private List<ExpenseItem> feedItems;

    public FeedListAdapter(Activity activity, List<ExpenseItem> feedItems){
        this.activity = activity;
        this.feedItems = feedItems;
    }

    @Override
    public int getCount() {
        return feedItems.size();
    }

    @Override
    public Object getItem(int position) {
        return feedItems.get(position);
    }

    @Override
    public long getItemId(int id) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ExpenseItem item = feedItems.get(position);

        if(convertView == null) {
            if(!isManager)
                convertView = inflater.inflate(R.layout.activity_expense_item, null);
            else {
                convertView = inflater.inflate(R.layout.activity_manager_expense_item, null);
                TextView owner = (TextView) convertView.findViewById(R.id.tvExpenseOwner);
                Button btnApprove = (Button) convertView.findViewById(R.id.btn_Approve);
                Button btnFlag = (Button) convertView.findViewById(R.id.btn_Flag);
                TextView status = (TextView) convertView.findViewById(R.id.tvExpenseStatus);

                if(item.Status == ExpenseStatus.Approved || item.Status == ExpenseStatus.Flagged)
                {
                    btnApprove.setVisibility(View.GONE);
                    btnFlag.setVisibility(View.GONE);
                    status.setVisibility(View.VISIBLE);
                }
                else
                {
                    btnApprove.setVisibility(View.VISIBLE);
                    btnFlag.setVisibility(View.VISIBLE);
                    status.setVisibility(View.GONE);
                }
                owner.setText(item.Owner);
            }
        }

        TextView subject = (TextView) convertView.findViewById(R.id.tvExpenseSubject);
        TextView amount = (TextView) convertView.findViewById(R.id.tvExpenseAmount);
        TextView description = (TextView) convertView.findViewById(R.id.tvExpenseDescription);
        TextView status = (TextView) convertView.findViewById(R.id.tvExpenseStatus);
        TextView date = (TextView) convertView.findViewById(R.id.tvDate);

        subject.setText(item.Subject);
        amount.setText(item.Amount);
        description.setText(item.Description);
        status.setText(item.Status.toString());
        date.setText(item.Date);

        status.setGravity(Gravity.CENTER);
        if(item.Status.toString().equals("Approved"))
        {
            status.setTextColor(Color.parseColor("#32cd32"));
        }
        else if(item.Status.toString().equals("Pending")){
            status.setTextColor(Color.parseColor("#000000"));
        }
        else{
            status.setTextColor(Color.parseColor("#FF0000"));
        }

        return convertView;
    }
}
