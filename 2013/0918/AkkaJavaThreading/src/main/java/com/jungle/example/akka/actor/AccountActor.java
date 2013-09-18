package com.jungle.example.akka.actor;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.jungle.example.akka.message.DepositMessage;
import com.jungle.example.akka.message.ResultMessage;
import com.jungle.example.akka.message.WithdrawMessage;

public class AccountActor extends UntypedActor {

    private LoggingAdapter LOG = Logging.getLogger(getContext().system(), this);

    private String owner;

    private int balance;

    private ActorRef transactionProcessor;

    public AccountActor(String name, ActorRef transactionProcessor, int bal) {
        this.owner = name;
        this.transactionProcessor = transactionProcessor;
        this.balance = bal;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof WithdrawMessage){
            LOG.info("withdraw");
            balance -= ((WithdrawMessage) message).getAmount();
            getSender().tell(new DepositMessage(((WithdrawMessage) message).getAmount()), getSelf());
        } else if(message instanceof DepositMessage){
            LOG.info("deposit");
            balance += ((DepositMessage) message).getAmount();
            transactionProcessor.tell(new ResultMessage(owner,balance), getSelf());
        } else {
            unhandled(message);
        }
    }
}
