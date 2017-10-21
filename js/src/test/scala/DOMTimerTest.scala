package com.kindone.timer

import org.scalatest.{ Matchers, FunSuite }

class DOMTimerTest extends FunSuite with org.scalamock.scalatest.MockFactory with Matchers {
  test("test DOM timer") {
      val a = List[Byte](1, 2)
      import scala.scalajs.js
      import scala.scalajs.js.timers.SetTimeoutHandle
      js.timers.setTimeout(1000) {
        println("hello")
      }
  }
}

