package com.sambeth.oclmv2


import com.sambeth.oclmv2.models.{Student, StudentSemigroup}
import models.Gender._
import models.StudentSemigroup._


object Main extends App {

  val maleStudent1 = Student.simpleStudent[Male]("Samuel")
  val maleStudent2 = Student.simpleStudent[Male]("Beth")
  val femaleStudent1 = Student.simpleStudent[Female]("Phyll")
  val femaleStudent2 = Student.simpleStudent[Female]("Ruth")

  println(maleStudent1 |+| maleStudent2)
  println(femaleStudent1 |+| femaleStudent2)
//  println(femaleStudent1 |+| maleStudent2)
//  println(maleStudent1 |+| femaleStudent2)
//  println(StudentSemigroup.apply.pair[Male](maleStudent1, femaleStudent2))

}
