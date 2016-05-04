package com.jungle

package object example {

  trait State

  trait Event

  @SerialVersionUID(1461793975)
  case class PriceEvent(price:Double) extends Event

  @SerialVersionUID(1461793986)
  case class StockPriceState(price:Double = 0.0) extends State {
    def updatePrice(event: Event): StockPriceState = {
      event match {
        case p:PriceEvent =>
          copy(price = p.price)
      }
    }
  }

  case class LiveStockPrice(price:Double)
}
