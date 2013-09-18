package com.jungle.example.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.jungle.example.akka.actor.AccountActor;
import com.jungle.example.akka.actor.TransactionProcessorActor;
import com.jungle.example.akka.message.TransferMessage;

public class BankApplication {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("bankSystem");

        ActorRef processor = system.actorOf(Props.create(TransactionProcessorActor.class),"processor");

        ActorRef bobAccount = system.actorOf(Props.create(AccountActor.class,"Bob",processor,100));
        ActorRef aliceAccount = system.actorOf(Props.create(AccountActor.class,"Alice",processor,50));

        processor.tell(new TransferMessage(bobAccount,aliceAccount,10),processor);
        processor.tell(new TransferMessage(aliceAccount,bobAccount,3),processor);

        system.shutdown();
    }
}
