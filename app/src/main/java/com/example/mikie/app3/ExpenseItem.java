package com.example.mikie.app3;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mikie on 07/10/2015.
 */


public class ExpenseItem implements Parcelable{
    public String Subject;
    public String Amount;
    public String Description;
    public ExpenseStatus Status;
    public String Date;
    public String Owner;

    ExpenseItem(String subject, String amount, String description, ExpenseStatus status, String date, String owner){
        Subject = subject;
        Amount = amount;
        Description = description;
        Status = status;
        Date = date;
        Owner = owner;
    }

    public ExpenseItem(Parcel in){
        String[] data = new String[6];
        in.readStringArray(data);
        this.Subject = data[0];
        this.Amount = data[1];
        this.Description = data[2];
        this.Status = ExpenseStatus.valueOf(data[3]);
        this.Date = data[4];
        this.Owner = data[5];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.Subject, this.Amount, this.Description, this.Status.toString(), this.Date, this.Owner});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public ExpenseItem createFromParcel(Parcel in){
            return new ExpenseItem(in);
        }

        public ExpenseItem[] newArray(int size){
           return new ExpenseItem[size];
        }
    };
}


