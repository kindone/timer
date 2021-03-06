package com.kindone.timer

import java.util.UUID


class SimulatedTimeline extends Timeline {
  case class TimerEntry(task: () => Unit, timestampMs: Long)

  var scheduledEntries: Map[UUID, TimerEntry] = Map()
  var currentTimestampMs: Long = 0

  override def setTimeout(timeoutMs: Long)(task: => Unit): UUID = {
    val uuid = UUID.randomUUID()
    // convert by-name param to a function
    println("timer " + uuid + " scheduled at: " + (timeoutMs + currentTimestampMs) + " (currentTime:" + currentTimestampMs + ")")
    scheduledEntries += (uuid -> TimerEntry(() => task, currentTimestampMs + timeoutMs))
    uuid
  }

  override def clearTimeout(uuid: UUID): Unit = {

    scheduledEntries.get(uuid).foreach { entry =>
      // cancel?
      println("timer " + uuid + " cleared scheduled at: " + entry.timestampMs + " (currentTime:" + currentTimestampMs + ")")
      canceledEntries :+= entry
    }

    scheduledEntries -= uuid
  }

  def advance(timeMs: Long) = {
    println("timer advancing to: " + (currentTimestampMs + timeMs))

    val startTimeMs = currentTimestampMs
    val endTimeMs = currentTimestampMs + timeMs

    var next:Option[(UUID, TimerEntry)] = None

    while({
        next = nextToBeFired(endTimeMs)
        next.isDefined
    }) {
      for(entry <- next) {
        println("timer " + entry._1 + " firing at: " + entry._2.timestampMs + " (time flowing from: " + startTimeMs + " to: " + endTimeMs + ")")
        currentTimestampMs = entry._2.timestampMs
        scheduledEntries -= entry._1
        entry._2.task()
        firedEntries :+= entry._2
      }
    }

    currentTimestampMs = endTimeMs
  }

  private def nextToBeFired(endTimeMs:Long):Option[(UUID, TimerEntry)] = {
    if(scheduledEntries.isEmpty)
      None
    else
    {
      val next = scheduledEntries.minBy(entry => entry._2.timestampMs)
      if(next._2.timestampMs <= endTimeMs)
        Some(next)
      else
        None
    }
  }

  var firedEntries: List[TimerEntry] = List()
  var canceledEntries: List[TimerEntry] = List()
}

