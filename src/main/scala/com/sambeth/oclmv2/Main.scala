package com.sambeth.oclmv2


import com.sambeth.oclmv2.models.Assignment.Assignment._
import com.sambeth.oclmv2.models.Gender.{Female, Gender, GenderMatch, Male}
import com.sambeth.oclmv2.models.Gender.GenderMatch._
import com.sambeth.oclmv2.models.Student.{Elder, MinisterialServant, Student, StudentGender}
import com.sambeth.oclmv2.models.Student.StudentGender._


object Main extends App {

  val male1 = Gender.male("Sam")
  val male2 = Gender.male("Beth")
  val male3 = Gender.male("Slim")
  val female1 = Gender.female("Phyll")

  println()
  val elder1 = Student.elder(male1)
  val elder2 = Student.elder(male2)
  val ministerialServant = Student.ministerialServant(male3)
  val simpleMaleStudent = Student.simpleStudent(male1)
  val pioneerMaleStudent = Student.pioneer(male1)
  val baptizedMaleStudent = Student.baptizedPublisher(male1)
  val unbaptizedMaleStudent = Student.unbaptizedPublisher(male1)

  // females
  val simpleFemaleStudent = Student.simpleStudent(female1)
  val pioneerFemaleStudent = Student.pioneer(female1)
  val baptizedFemaleStudent = Student.baptizedPublisher(female1)
  val unbaptizedFemaleStudent = Student.unbaptizedPublisher(female1)


//  println(StudentAssignment[Student[Male], OpeningPrayer].assign(elder1))
//  println(StudentAssignment[Student[Male], OpeningPrayer].assign(ministerialServant))
//  println(StudentAssignment[Student[Male], OpeningPrayer].assign(simpleMaleStudent))

  println(ministerialServant |+| ministerialServant)
  println(elder1.assignOpeningPrayer)
  println(ministerialServant.assignOpeningPrayer)
  println(ministerialServant.assignClosingPrayer)
  println(simpleMaleStudent.assignInitialCall(simpleMaleStudent))
//  println(simpleMaleStudent.assignInitialCall(simpleFemaleStudent))


}
