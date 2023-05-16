package com.sambeth.oclmv2.models.Gender

sealed trait Gender {
  def id: String
  def firstName: String
  def lastName: String
  def age: Option[Int] = None
  def pioneer: String
  def baptized: String
  def publisher: String
  def ministerialServant: String
  def elder: String
}

//trait Female extends Gender
//trait Male extends Gender

object Gender {
  case class Male(id: String,
                  firstName: String,
                  lastName: String,
                  pioneer: String,
                  baptized: String,
                  publisher: String,
                  ministerialServant: String,
                  elder: String) extends Gender

  case class Female(id: String,
                    firstName: String,
                    lastName: String,
                    pioneer: String,
                    baptized: String,
                    publisher: String,
                    ministerialServant: String,
                    elder: String) extends Gender

  def male(id: String,
           firstName: String,
           lastName: String,
           pioneer: String,
           baptized: String,
           publisher: String,
           ministerialServant: String,
           elder: String): Male = Male(id, firstName, lastName, pioneer, baptized, publisher, ministerialServant, elder)
  def female(id: String,
             firstName: String,
             lastName: String,
             pioneer: String,
             baptized: String,
             publisher: String,
             ministerialServant: String,
             elder: String): Female = Female(id, firstName, lastName, pioneer, baptized, publisher, ministerialServant, elder)
}
