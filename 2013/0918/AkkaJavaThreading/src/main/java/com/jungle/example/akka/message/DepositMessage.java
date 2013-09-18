package com.jungle.example.akka.message;

import java.io.Serializable;

public class DepositMessage implements Serializable {
    private int amount;

    public DepositMessage(int amt) {
        amount = amt;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
