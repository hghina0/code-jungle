package com.jungle.example.akka.message;

import java.io.Serializable;

public class WithdrawMessage implements Serializable {
    private int amount;

    public WithdrawMessage(int amt) {
        amount = amt;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
