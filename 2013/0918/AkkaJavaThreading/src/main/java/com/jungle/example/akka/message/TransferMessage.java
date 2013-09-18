package com.jungle.example.akka.message;

import akka.actor.ActorRef;

import java.io.Serializable;

public class TransferMessage implements Serializable {

    private ActorRef to;
    private ActorRef from;
    private int amount;

    public TransferMessage(ActorRef from,ActorRef to, int amount) {
        this.to = to;
        this.from = from;
        this.amount = amount;
    }

    public ActorRef getTo() {
        return to;
    }

    public void setTo(ActorRef to) {
        this.to = to;
    }

    public ActorRef getFrom() {
        return from;
    }

    public void setFrom(ActorRef from) {
        this.from = from;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
