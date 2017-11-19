package com.kindone.timer

import java.util.UUID

class Timer(timeline:Timeline) {
  private var uuid: Option[UUID] = None

  def setTimeout(timeoutMS:Long)(task: => Unit): Unit = {
    clear()
    uuid = Some(timeline.setTimeout(timeoutMS) {
      task
    })
  }

  def clear() = {
    uuid.foreach(uuid => timeline.clearTimeout(uuid))
    uuid = None
  }
}
