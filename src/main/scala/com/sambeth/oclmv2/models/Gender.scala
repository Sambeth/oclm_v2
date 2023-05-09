package com.sambeth.oclmv2.models

sealed trait Gender {
  def age: Option[Int] = None
}

object Gender {
  trait Female extends Gender
  trait Male extends Gender
}
