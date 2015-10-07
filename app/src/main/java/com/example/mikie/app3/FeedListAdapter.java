package com.example.mikie.app3;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mikie on 07/10/2015.
 */
public class FeedListAdapter extends BaseAdapter {
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
        if(convertView == null)
            convertView = inflater.inflate(R.layout.activity_expense_item, null);

        TextView subject = (TextView) convertView.findViewById(R.id.tvExpenseSubject);
        TextView amount = (TextView) convertView.findViewById(R.id.tvExpenseAmount);
        TextView description = (TextView) convertView.findViewById(R.id.tvExpenseDescription);
        TextView status = (TextView) convertView.findViewById(R.id.tvExpenseStatus);

        ExpenseItem item = feedItems.get(position);

        subject.setText(item.Subject);
        amount.setText(item.Amount);
        description.setText(item.Description);
        status.setText(item.Status.toString());

        return convertView;
    }
}
