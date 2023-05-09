package com.sambeth.oclmv2.models


trait Student[+G] {
  def age: Option[Int] = None
  def recency: Option[Double] = None
  def available: Boolean = true
}

final case class SimpleStudent[G](name: String) extends Student[G]
trait Publisher[G] extends Student[G]
trait BaptizedPublisher[G] extends Publisher[G]
final case class SimpleBaptizedPublisher[G](name: String) extends Publisher[G]
final case class UnbaptizedPublisher[G](name: String) extends Publisher[G]
final case class Pioneer[G](name: String) extends BaptizedPublisher[G]
final case class MinisterialServant[G](name: String) extends BaptizedPublisher[G]
final case class Elder[G](val name: String) extends BaptizedPublisher[G]

object Student {
  def simpleStudent[G](name: String): Student[G] = SimpleStudent(name)
  def baptizedPublisher[G](name: String): Student[G] = SimpleBaptizedPublisher(name)
  def unbaptizedPublisher[G](name: String): Student[G] = UnbaptizedPublisher(name)
  def pioneer[G](name: String): Student[G] = Pioneer(name)
  def ministerialServant[G](name: String): Student[G] = MinisterialServant(name)
  def elder[G](name: String): Student[G] = Elder(name)
}
