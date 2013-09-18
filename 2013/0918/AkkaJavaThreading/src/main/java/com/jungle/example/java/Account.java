package com.jungle.example.java;

public class Account {
    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public synchronized void withdraw(int amount) {
        System.out.println("withdraw");
        balance = balance - amount;
    }
    public synchronized void deposit(int amount) {
        System.out.println("deposit");
        balance = balance + amount;
    }

    public synchronized void transferTo(Account to, int amount) {
        System.out.println("transferTo");
        this.withdraw(amount);
        to.deposit(amount);
        System.out.println("Remaining Balance :" + balance);
    }
}
