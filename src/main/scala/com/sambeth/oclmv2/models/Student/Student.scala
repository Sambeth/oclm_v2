package com.sambeth.oclmv2.models.Student

import com.sambeth.oclmv2.models.Gender.Male


trait Student[+G] {
  def age: Option[Int] = None
  def recency: Option[Double] = None
  def available: Boolean = true
}

final case class SimpleStudent[G](person: G) extends Student[G]
trait Publisher[G] extends Student[G]
trait BaptizedPublisher[G] extends Publisher[G]
final case class SimpleBaptizedPublisher[G](person: G) extends Publisher[G]
final case class UnbaptizedPublisher[G](person: G) extends Publisher[G]
final case class Pioneer[G](person: G) extends BaptizedPublisher[G]
final case class MinisterialServant[Male](male: Male) extends BaptizedPublisher[Male]
final case class Elder[Male](val male: Male) extends BaptizedPublisher[Male]

object Student {
  def simpleStudent[G](person: G): SimpleStudent[G] = SimpleStudent(person)
  def baptizedPublisher[G](person: G): SimpleBaptizedPublisher[G] = SimpleBaptizedPublisher(person)
  def unbaptizedPublisher[G](person: G): UnbaptizedPublisher[G] = UnbaptizedPublisher(person)
  def pioneer[G](person: G): Pioneer[G] = Pioneer(person)
  def ministerialServant(person: Male): MinisterialServant[Male] = MinisterialServant(person)
  def elder(person: Male): Elder[Male] = Elder(person)
}
