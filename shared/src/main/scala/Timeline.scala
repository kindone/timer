package com.kindone.timer

import java.util.UUID

/**
 * Created by kindone on 2017. 2. 18..
 */

trait Timeline {
  def setTimeout(timeoutMs: Long)(task: => Unit): UUID
  def clearTimeout(uuid: UUID): Unit
}

