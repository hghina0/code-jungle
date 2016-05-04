package com.jungle.example

import akka.actor.ActorLogging
import akka.persistence.{PersistentActor, RecoveryCompleted, RecoveryFailure, SnapshotOffer}

class StockPriceTracker(scriptName:String) extends PersistentActor with ActorLogging {

  override def persistenceId: String = s"StockPriceTracker-${scriptName}"

  var state = StockPriceState()

  protected val snapshotInterval = 3

  override def preStart(): Unit = {
    super.preStart() // DO NOT forget this
    log.info(s"StockPriceTracker created for ${scriptName}")
  }

  def updateState(event: Event): Unit = {
    state = state.updatePrice(event)
    saveSnapshotIfNeeded
  }

  override def receiveRecover: Receive = {
    case evt: Event =>
      log.info(s"${scriptName} OnPersist/OnRecover event $evt")
      updateState(evt)
    case RecoveryCompleted =>
      log.info(s"${scriptName} recovery completed")
    case SnapshotOffer(_, snapshot: StockPriceState) =>
      state = snapshot
      log.info(s"${scriptName} restored snapshot: $snapshot")
    case RecoveryFailure(e) =>
      e.printStackTrace()
  }

  override def receiveCommand: Receive = {
    case livePrice:LiveStockPrice =>
      log.info(s"Received LiveStockPrice ${livePrice} for script ${scriptName}")
      persist(PriceEvent(livePrice.price))(updateState)

    case "print" =>
      log.info(s"Current price for ${scriptName} script is ${state}")
  }

  def saveSnapshotIfNeeded() = {
    if (lastSequenceNr % snapshotInterval == 0) {
      saveSnapshot(state)
      log.info(s"saved snapshot: ${state}")
      List[String]()
    }
  }

}
