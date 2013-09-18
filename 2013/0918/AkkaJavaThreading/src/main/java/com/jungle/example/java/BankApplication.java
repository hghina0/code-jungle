package com.jungle.example.java;

public class BankApplication {
    public static void main(String[] args) {
        Account alice = new Account(100);
        Account bob = new Account(50);

        new Thread(new TransactionProcessor(alice,bob,10)).start();
        new Thread(new TransactionProcessor(bob,alice,3)).start();
    }
}
