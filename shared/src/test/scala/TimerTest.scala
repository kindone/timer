package com.kindone.timer

import org.scalatest._

class TimerTest extends FlatSpec with Matchers{
  "Timer" should "work as expected" in {
    val timeline = new SimulatedTimeline
    val timer1 = new Timer(timeline)
    val timer2 = new Timer(timeline)
    val timer3 = new Timer(timeline)

    var (fired1, fired2, fired3, fired4) = (false, false, false, false)

    timer1.setTimeout(1000) {
      fired1 = true
    }

    timer2.setTimeout(500) {
      fired2 = true
    }

    timer3.setTimeout(600) {
      fired3 = true
    }

    timeline.advance(500)
    fired2 should be (true)
    fired1 should be (false)
    fired3 should be (false)

    timer3.clear()

    timeline.advance(500)
    fired1 should be (true)
    fired3 should be (false)

  }
}
