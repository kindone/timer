package com.kindone.timer

import org.scalatest._


/**
 * Created by kindone on 2017. 2. 19..
 */
class SimulatedTimelineTest extends FlatSpec with Matchers{
  "SimulatedTimeline" should "work as expected" in {
    val timeline = new SimulatedTimeline

    var (fired1, fired2, fired3, fired4) = (false, false, false, false)

    timeline.setTimeout(1000) {
      fired2 = true
      println("fired2")
    }

    timeline.setTimeout(500) {
      fired1 = true
      println("fired1")
    }

    timeline.setTimeout(2000) {
      fired3 = true
      println("fired3")
    }

    timeline.advance(1000)
    fired1 should be (true)
    fired2 should be (true)
    fired3 should be (false)

    timeline.setTimeout(500) {
      fired4 = true
      println("fired4")
    }

    timeline.advance(1000)
    fired3 should be (true)
    fired4 should be (true)

  }


  "SimulatedTimeline" should "cancel as expected" in {
    val timeline = new SimulatedTimeline

    var (fired1, fired2, fired3, fired4) = (false, false, false, false)

    val t2 = timeline.setTimeout(1000) {
      fired2 = true
      println("fired2")
    }

    val t1 = timeline.setTimeout(500) {
      fired1 = true
      println("fired1")
    }

    val t3 = timeline.setTimeout(2000) {
      fired3 = true
      println("fired3")
    }

    timeline.clearTimeout(t1)
    timeline.advance(1000)
    fired1 should be (false)
    fired2 should be (true)
    fired3 should be (false)

    timeline.setTimeout(500) {
      fired4 = true
      println("fired4")
    }

    timeline.advance(1000)
    fired3 should be (true)
    fired4 should be (true)

  }
}
