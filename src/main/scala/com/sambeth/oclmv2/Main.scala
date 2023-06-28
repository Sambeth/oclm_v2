package com.sambeth.oclmv2

import cats.data.State
import cats.syntax.all._
import com.sambeth.oclmv2.models.Assignment.Assignment.{Chairman, ClosingPrayer, CongregationBibleStudy, FiveMinutesTalk, OpeningPrayer, SpiritualGems, TenMinutesTalk, adHoc}
import com.sambeth.oclmv2.models.Gender.Gender
import com.sambeth.oclmv2.models.Gender.Gender.{Female, Male}
import com.sambeth.oclmv2.models.Student.Student
import com.sambeth.oclmv2.models.Student.Student.{AppointedMan, Elder, MinisterialServant, Pioneer, SimpleBaptizedPublisher, SimpleStudent, UnbaptizedPublisher}
import com.sambeth.oclmv2.models.Student.StudentGroup.elders
import com.sambeth.oclmv2.models.utils.Utils._
import com.typesafe.config.{Config, ConfigFactory}

import scala.jdk.CollectionConverters.IterableHasAsScala





object Main extends App {

  val oclmSchedule = createOCLMSchedule.run(Db(scheduleConfig, studentMapTupled))
  println(oclmSchedule)

}
