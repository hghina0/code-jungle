package com.jungle.example.akka.message;

import java.io.Serializable;

public class ResultMessage implements Serializable {
    private String name;
    private int balance;

    public ResultMessage(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
