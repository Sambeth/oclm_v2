package com.sambeth.oclmv2

import cats.data.State
import cats.syntax.all._
import com.sambeth.oclmv2.models.Assignment.Assignment.{Chairman, ClosingPrayer, CongregationBibleStudy, FiveMinutesTalk, OpeningPrayer, SpiritualGems, TenMinutesTalk}
import com.sambeth.oclmv2.models.Gender.Gender
import com.sambeth.oclmv2.models.Gender.Gender.{Female, Male}
import com.sambeth.oclmv2.models.Student.Student
import com.sambeth.oclmv2.models.Student.Student.{AppointedMan, Elder, MinisterialServant, Pioneer, SimpleBaptizedPublisher, SimpleStudent, UnbaptizedPublisher}
import com.sambeth.oclmv2.models.Student.StudentGroup.elders
import com.sambeth.oclmv2.models.utils.Utils._
import com.typesafe.config.{Config, ConfigFactory}

import scala.jdk.CollectionConverters.IterableHasAsScala





object Main extends App {

//  val students: List[Student[Male]] = List(
//    SimpleBaptizedPublisher(Female("75","Daniella","Vizah","No","No","Yes","No","No")),
//    MinisterialServant(Male("1","Jeho","Vizah","No","No","Yes","No","No")),
//  ).asInstanceOf[List[Student[Male]]]
//
//  val male: Student[Male] = SimpleBaptizedPublisher(Male("75","Daniella","Vizah","No","No","Yes","No","No"))
//
//  val persons: Student[Gender] = List(
//    SimpleBaptizedPublisher(Female("75", "Daniella", "Vizah", "No", "No", "Yes", "No", "No")),
//    MinisterialServant(Male("1", "Jeho", "Vizah", "No", "No", "Yes", "No", "No")),
//  )
//
//  println(persons.filter {
////    case _: AppointedMan[Male] => true
////    case _: Elder[Male] => true
////    case _: MinisterialServant[Male] => true
//    case _: Pioneer[Male] => true
//    case _: UnbaptizedPublisher[Male] => true
//    case _: SimpleBaptizedPublisher[Male] => true
////    case _: SimpleStudent[Male] => true
//    case _: Student[Female] => false
//  })

//  println(FiveMinutesTalk("", SimpleBaptizedPublisher(Female("75","Daniella","Vizah","No","No","Yes","No","No")).asInstanceOf[Student[Male]]))

  val oclmSchedule = createOCLMSchedule.run(Db(scheduleConfig, studentMapTupled))
  println(oclmSchedule)
//  val (s, y) = selectAvailableElder[Elder[Male]](getRandomPosition).run(allStudents).value
//  println(y)
//  println(selectAvailableElder[Elder[Male]](getRandomPosition).run(allStudents).value)
//  val testConfig: Config = ConfigFactory.load("application.conf")
//  println(testConfig.getObject("ApplyYourselfToFieldMinistry").containsKey("PairedAssignments"))
//  println(testConfig.getObject("ApplyYourselfToFieldMinistry").containsKey("FiveMinutesTalk"))
//  val scheduleConfig: Config = ConfigFactory.load("application.conf")
//  println(scheduleConfig.getList("LivingAsChristians.AdHoc").asScala.toList.map(m => m.render()))
//  println(scheduleConfig.getList("LivingAsChristians.AdHoc").toArray.toList)

}
