package com.sambeth.oclmv2.models

sealed trait Gender {
  def age: Option[Int] = None
}

//trait Female extends Gender
//trait Male extends Gender
case class Male(name: String) extends Gender
case class Female(name: String) extends Gender

object Gender {
  def male(name: String): Male = Male(name)
  def female(name: String): Female = Female(name)
}
