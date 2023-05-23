package com.sambeth.oclmv2

import cats.data.State
import cats.syntax.all._
import com.sambeth.oclmv2.models.Assignment.Assignment.{Chairman, ClosingPrayer, CongregationBibleStudy, OpeningPrayer, SpiritualGems, TenMinutesTalk}
import com.sambeth.oclmv2.models.Gender.Gender
import com.sambeth.oclmv2.models.Gender.Gender.Male
import com.sambeth.oclmv2.models.Student.Student
import com.sambeth.oclmv2.models.Student.Student.Elder
import com.sambeth.oclmv2.models.Student.StudentGroup.elders
import com.sambeth.oclmv2.models.utils.Utils._
import com.typesafe.config.{Config, ConfigFactory}

import scala.jdk.CollectionConverters.IterableHasAsScala





object Main extends App {

//  StudentGroup.simpleMaleStudents.show()
//  println(StudentGroup.simpleMaleStudents.collect().toList.map(s => s.assignBibleStudy))
//  StudentGroup.unbaptizedMalePublishers.show()
//  StudentGroup.baptizedMalePublishers.show()
//  StudentGroup.malePioneers.show()
//  StudentGroup.ministerialServants.show()
//  StudentGroup.elders.show()

//  val applicationConf: Config = ConfigFactory.load("application.conf")
//  println(applicationConf.getList("LivingAsChristians.AdHoc"))
//  println(getRandomStudent("elders").run(Db(scheduleConfig, studentsMap)))
//  println(getRandomStudent("elders").run(Db(scheduleConfig, studentsMap)))
//  println(List(1, 2, 3) ++ List(4, 5, 5) ++ List(7, 8, 9))
//  println(studentsMap("elders") ++ studentsMap("elders"))
//  println(studentsMap("elders") ++ studentsMap("ministerialServants"))
//  println(studentsGenderMap("males").toNel)
//  println(studentsGenderMap("females"))

//  val oclmSchedule = createOCLMSchedule.run(Db(scheduleConfig, allStudents))
//  println(oclmSchedule)
//  val (s, y) = selectAvailableElder[Elder[Male]](getRandomPosition).run(allStudents).value
//  println(y)
//  println(selectAvailableElder[Elder[Male]](getRandomPosition).run(allStudents).value)
//  val testConfig: Config = ConfigFactory.load("application.conf")
//  println(testConfig.getObject("ApplyYourselfToFieldMinistry").containsKey("PairedAssignments"))
//  println(testConfig.getObject("ApplyYourselfToFieldMinistry").containsKey("FiveMinutesTalk"))
  val scheduleConfig: Config = ConfigFactory.load("application.conf")
  println(scheduleConfig.getList("LivingAsChristians.AdHoc").asScala.toList.map(m => m.render()))
//  println(scheduleConfig.getList("LivingAsChristians.AdHoc").toArray.toList)

}
