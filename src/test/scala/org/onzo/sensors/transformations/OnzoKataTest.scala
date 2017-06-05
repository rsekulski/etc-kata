package org.onzo.sensors.transformations

import org.scalatest.FlatSpec

/**
  * Created by david.lush on 05/06/2017.
  */
class OnzoKataTest extends FlatSpec {

  val testList = List(
    Consumption("7dd8f7f3-95e3-4b14-bd8c-1b9eb7056d7a", "2017-03-30T02:00:00Z", 54),
    Consumption("1a258617-2d1d-4dd8-9ad0-046ed2881b58", "2017-03-28T02:00:00Z", 50),
    Consumption("b08c6195-8cd9-43ab-b94d-e0b887dd73d2", "2017-03-27T02:00:00Z", 92),
    Consumption("7dd8f7f3-95e3-4b14-bd8c-1b9eb7056d7a", "2017-03-30T03:00:00Z", 50)
  )


  "A counter per day" should "resolve the consumption per day passing a list" in {

    assert(OnzoKata.getTotalperDay(testList, "2017-03-30") == 104)

  }



}
