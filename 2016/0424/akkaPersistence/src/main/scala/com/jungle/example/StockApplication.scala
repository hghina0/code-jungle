package com.jungle.example

import akka.actor.{ActorRef, ActorSystem, Props}
import scala.util.Random._

//Start local mongo on port 27017 before running this application
object StockApplication extends App {

  val CONSTANT = 30.34
  val SEND_ACTION = true

  val system = ActorSystem()

  val googlePriceTracker : ActorRef = system.actorOf(Props(classOf[StockPriceTracker],"Google Inc."))
  if(SEND_ACTION)
    sendLivePrice(googlePriceTracker)
  else
    googlePriceTracker ! "print"

  val fbPriceTracker : ActorRef = system.actorOf(Props(classOf[StockPriceTracker],"Facebook Inc."))
  if(SEND_ACTION)
    sendLivePrice(fbPriceTracker)
  else
    fbPriceTracker ! "print"

  Thread.sleep(5000)

  system.shutdown()
  system.awaitTermination()

  def sendLivePrice(priceTracker: ActorRef) = {
    priceTracker ! LiveStockPrice(roundPrice(CONSTANT * nextDouble))
    priceTracker ! LiveStockPrice(roundPrice(CONSTANT * nextDouble))
  }

  def roundPrice(price:Double) : Double = {
    BigDecimal(price).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
  }
}
