package com.sambeth.oclmv2


import com.sambeth.oclmv2.models.Assignment.Assignment._
import com.sambeth.oclmv2.models.Assignment.Assign._
import com.sambeth.oclmv2.models.Gender.GenderMatch._
import com.sambeth.oclmv2.models.Student.Student._
import com.sambeth.oclmv2.models.Student.StudentPairing._
import com.sambeth.oclmv2.models.Student.StudentGroup
import com.typesafe.config.{Config, ConfigFactory}


object Main extends App {

//  val male1 = Gender.male("Sam")
//  val male2 = Gender.male("Beth")
//  val male3 = Gender.male("Slim")
//  val female1 = Gender.female("Phyll")
//
//  println()
//  val elder1 = Student.elder(male1)
//  val elder2 = Student.elder(male2)
//  val ministerialServant = Student.ministerialServant(male3)
//  val simpleMaleStudent = Student.simpleStudent(male1)
//  val pioneerMaleStudent = Student.pioneer(male1)
//  val baptizedMaleStudent = Student.baptizedPublisher(male1)
//  val unbaptizedMaleStudent = Student.unbaptizedPublisher(male1)
//
//  // females
//  val simpleFemaleStudent = Student.simpleStudent(female1)
//  val pioneerFemaleStudent = Student.pioneer(female1)
//  val baptizedFemaleStudent = Student.baptizedPublisher(female1)
//  val unbaptizedFemaleStudent = Student.unbaptizedPublisher(female1)


//  println(StudentAssignment[Student[Male], OpeningPrayer].assign(elder1))
//  println(StudentAssignment[Student[Male], OpeningPrayer].assign(ministerialServant))
//  println(StudentAssignment[Student[Male], OpeningPrayer].assign(simpleMaleStudent))

//  println(ministerialServant |+| ministerialServant)
//  println(elder1.assignOpeningPrayer)
//  println(ministerialServant.assignOpeningPrayer)
//  println(ministerialServant.assignClosingPrayer)
//  println(simpleMaleStudent.assignInitialCall(simpleMaleStudent))
//  StudentGroup.simpleFemaleStudents.show()
//  StudentGroup.unbaptizedFemalePublishers.show()
//  StudentGroup.baptizedFemalePublishers.show()
//  StudentGroup.femalePioneers.show()
//
//  StudentGroup.simpleMaleStudents.show()
  println(StudentGroup.simpleMaleStudents.collect().toList.map(s => s.assignBibleStudy))
//  StudentGroup.unbaptizedMalePublishers.show()
//  StudentGroup.baptizedMalePublishers.show()
//  StudentGroup.malePioneers.show()
//  StudentGroup.ministerialServants.show()
//  StudentGroup.elders.show()

  val applicationConf: Config = ConfigFactory.load("application.conf")
  println(applicationConf.getList("LivingAsChristians.AdHoc"))




}
