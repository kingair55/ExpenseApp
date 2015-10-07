package com.example.mikie.app3;

/**
 * Created by mikie on 07/10/2015.
 */
public enum ExpenseStatus {
    Approved(1), Pending(2), Flagged(3);

    private final int mask;
    private ExpenseStatus(int mask){
        this.mask = mask;
    }

    public int getMask(){
        return mask;
    }
}
