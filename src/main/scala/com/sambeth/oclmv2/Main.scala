package com.sambeth.oclmv2


import models.{Assignment, Elder, Female, Gender, GenderSemigroup, Male, MinisterialServant, Student, StudentGenderSemigroup}
import models.GenderSemigroup._
import models.StudentGenderSemigroup._


object Main extends App {

  val male1 = Gender.male("Sam")
  val male2 = Gender.male("Beth")
  val male3 = Gender.male("Slim")
  val female1 = Gender.female("Phyll")

//  println(male1 + male2)
//  println(female1 + female1)
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


  println(StudentGenderSemigroup[Student[Male]].pair(elder1, elder2))
  println(StudentGenderSemigroup[Student[Male]].pair(elder1, ministerialServant))
//  println(StudentGenderSemigroup[Student[Male]].pair(elder1, simpleFemaleStudent))
  println(StudentGenderSemigroup[Student[Female]].pair(simpleFemaleStudent, simpleFemaleStudent))
  println(StudentGenderSemigroup[Student[Male]].pair(elder1, ministerialServant))
//  println(StudentGenderSemigroup[Student[Male]].pair(elder1, simpleFemaleStudent))


  println(elder1 |+| elder2)
  println(elder1 |+| ministerialServant)
  println(ministerialServant |+| ministerialServant)
  println(elder1 |+| pioneerMaleStudent)
  println(elder1 |+| baptizedMaleStudent)
  println(elder1 |+| unbaptizedMaleStudent)
  println(ministerialServant |+| pioneerMaleStudent)
  println(ministerialServant |+| baptizedMaleStudent)
  println(ministerialServant |+| unbaptizedMaleStudent)
  println(pioneerMaleStudent |+| pioneerMaleStudent)
  println(pioneerMaleStudent |+| baptizedMaleStudent)
  println(pioneerMaleStudent |+| unbaptizedMaleStudent)
  println(baptizedMaleStudent |+| baptizedMaleStudent)
  println(baptizedMaleStudent |+| unbaptizedMaleStudent)
  println(unbaptizedMaleStudent |+| unbaptizedMaleStudent)
//  baptizedMaleStudent.

  val elder: Student[Gender] = Student.elder(male1)
  println(elder)

}
