package com.sambeth.oclmv2.models.utils

import cats.data.{Reader, State}
import com.sambeth.oclmv2.models.Assignment.Assignment._
import com.sambeth.oclmv2.models.Assignment.Assign._
import com.sambeth.oclmv2.models.Assignment.Assignment
import com.sambeth.oclmv2.models.Assignment.AssignmentType.ApplyYourselfToFieldMinistry
import com.sambeth.oclmv2.models.Gender.Gender
import com.sambeth.oclmv2.models.Gender.Gender._
import com.sambeth.oclmv2.models.Student.{Student, StudentGroup}
import com.sambeth.oclmv2.models.Student.Student._
import com.typesafe.config.{Config, ConfigFactory}

import scala.jdk.CollectionConverters.CollectionHasAsScala
import scala.util.Random


object Utils {

  // appointed men assignments
//  val assignChairman: Reader[Elder[Male], Chairman] =
//    Reader((e: Elder[Male]) => e.assignChairman)
//
//  val assignOpeningPrayer: Reader[AppointedMan[Male], OpeningPrayer] =
//    Reader((e: AppointedMan[Male]) => e.assignOpeningPrayer)
//
//  val assignClosingPrayer: Reader[AppointedMan[Male], ClosingPrayer] =
//    Reader((e: AppointedMan[Male]) => e.assignClosingPrayer)
//
//  val assignTenMinutesTalk: Reader[AppointedMan[Male], String => TenMinutesTalk] =
//    Reader((e: AppointedMan[Male]) => e.assignTenMinutesTalk)
//
//  val assignInitialCallVideo: Reader[Elder[Male], InitialCallVideo] =
//    Reader((e: Elder[Male]) => e.assignInitialCallVideo)
//
//  val assignReturnVisitVideo: Reader[Elder[Male], ReturnVisitVideo] =
//    Reader((e: Elder[Male]) => e.assignReturnVisitVideo)
//
//  val assignAdHoc: Reader[AppointedMan[Male], String => AdHoc] =
//    Reader((e: AppointedMan[Male]) => e.assignAdHoc)
//
//  val assignCongregationBibleStudy: Reader[Elder[Male], CongregationBibleStudy] =
//    Reader((e: Elder[Male]) => e.assignCongregationBibleStudy)
//
//  // school assignments
//  def assignInitialCall[G <: Gender]: Reader[Student[G], Student[G] => InitialCall[G]] =
//    Reader((e: Student[G]) => e.assignInitialCall)
//
//  def assignReturnVisit[G <: Gender]: Reader[Student[G], Student[G] => ReturnVisit[G]] =
//    Reader((e: Student[G]) => e.assignReturnVisit)
//
//  def assignBibleStudy[G <: Gender]: Reader[Student[G], Student[G] => BibleStudy[G]] =
//    Reader((e: Student[G]) => e.assignBibleStudy)
//
//  val assignFiveMinutesTalk: Reader[Student[Male], String => FiveMinutesTalk] =
//    Reader((e: Student[Male]) => e.assignFiveMinutesTalk)

//  val studentsMap = Map(
//    "elders" -> StudentGroup.elders.collect().toList,
//    "ministerialServants" -> StudentGroup.ministerialServants.collect().toList,
//    "malePioneers" -> StudentGroup.malePioneers.collect().toList,
//    "baptizedMalePublishers" -> StudentGroup.baptizedMalePublishers.collect().toList,
//    "unbaptizedMalePublishers" -> StudentGroup.unbaptizedMalePublishers.collect().toList,
//    "simpleMaleStudents" -> StudentGroup.simpleMaleStudents.collect().toList,
//    "femalePioneers" -> StudentGroup.femalePioneers.collect().toList,
//    "baptizedFemalePublishers" -> StudentGroup.baptizedFemalePublishers.collect().toList,
//    "unbaptizedFemalePublishers" -> StudentGroup.unbaptizedFemalePublishers.collect().toList,
//    "simpleFemaleStudents" -> StudentGroup.simpleFemaleStudents.collect().toList,
//  )

  //  val studentsGenderMap: Map[String, List[Student[Gender]]] = Map(
  //    "males" -> (
  //      studentsMap("elders")
  //      ++ studentsMap("ministerialServants")
  //      ++ studentsMap("malePioneers")
  //      ++ studentsMap("baptizedMalePublishers")
  //      ++ studentsMap("unbaptizedMalePublishers")
  //      ++ studentsMap("simpleMaleStudents")
  //    ),
  //    "females" -> (
  //      studentsMap("femalePioneers")
  //      ++ studentsMap("baptizedFemalePublishers")
  //      ++ studentsMap("unbaptizedFemalePublishers")
  //      ++ studentsMap("simpleFemaleStudents")
  //    )
  //  )
  type AllStudentsTuple = Tuple10[
    List[Elder[Male]],
    List[MinisterialServant[Male]],
    List[Pioneer[Male]],
    List[SimpleBaptizedPublisher[Male]],
    List[UnbaptizedPublisher[Male]],
    List[SimpleStudent[Male]],
    List[Pioneer[Female]],
    List[SimpleBaptizedPublisher[Female]],
    List[UnbaptizedPublisher[Female]],
    List[SimpleStudent[Female]]
  ]

  val studentMapTupled: AllStudentsTuple = (
    StudentGroup.elders.collect().toList,
    StudentGroup.ministerialServants.collect().toList,
    StudentGroup.malePioneers.collect().toList,
    StudentGroup.baptizedMalePublishers.collect().toList,
    StudentGroup.unbaptizedMalePublishers.collect().toList,
    StudentGroup.simpleMaleStudents.collect().toList,
    StudentGroup.femalePioneers.collect().toList,
    StudentGroup.baptizedFemalePublishers.collect().toList,
    StudentGroup.unbaptizedFemalePublishers.collect().toList,
    StudentGroup.simpleFemaleStudents.collect().toList
  )

//  val allStudents: List[Student[Gender]] = (studentsGenderMap("males") ++ studentsGenderMap("females"))

  val scheduleConfig: Config = ConfigFactory.load("application.conf")

  final case class Db(config: Config, students: AllStudentsTuple)

  type DbReader[A] = Reader[Db, A]

  final case class MidWeekMeeting(
                               chairman: Chairman,
//                               openingPrayer: OpeningPrayer,
//                               closingPrayer: ClosingPrayer,
//                               tenMinutesTalk: TenMinutesTalk,
//                               spiritualGems: SpiritualGems,
//                               bibleReading: BibleReading,
//                               initialCallVideo: Option[InitialCallVideo] = None,
//                               returnVisitVideo: Option[ReturnVisitVideo] = None,
//                               initialCall: Option[InitialCall[_]] = None,
//                               returnVisit: Option[ReturnVisit[_]] = None,
//                               bibleStudy: Option[BibleStudy[_]] = None,
//                               fiveMinutesTalk: Option[FiveMinutesTalk] = None,
//                               adHoc: List[AdHoc] = List.empty,
//                               congregationBibleStudy: CongregationBibleStudy
                               )

  type OCLMSchedule = List[MidWeekMeeting]

  def getRandomPosition(studentSize: Int): Int = {
    val seed = scheduleConfig.getLong("Seed.Long")
    val random = new Random(seed)
    val randomIndex = random.nextInt(studentSize)
    randomIndex
  }

  def selectAvailableElder(func: Int => Int): State[AllStudentsTuple, Elder[Male]]
    = State(students => {
        val elders = students._1
        val position = func(elders.length)
        val elder = elders(position)
        (students.copy(_1 = elders.filterNot(_ == elder)), elder)
      }
    )

//  def selectAvailableAppointedMan(title: String, func: Int => Int): State[List[Student[Gender]], Option[(String, AppointedMan[Male])]]
//    = State(students => {
//        val appointedMan = students.filter {
//          case _: AppointedMan[Male] => true
//          case _ => false
//        }.asInstanceOf[List[AppointedMan[Male]]]
//        val position = func(appointedMan.length)
//        (students.filterNot(_ == appointedMan(position)), Option((title, appointedMan(position))))
//      }
//  )
//
//  def selectAvailableStudent(func: Int => Int): State[List[Student[Gender]], Option[Student[Gender]]]
//    = State(students => {
//      val person = students.filter {
//        case s: Student[Gender] => true
//        case _ => false
//      }
//      val position = func(person.length)
//      (students.filterNot(_ == person(position)), Option(person(position)))
//    }
//  )
//
//  def selectAvailableMaleStudent(title: String, func: Int => Int): State[List[Student[Gender]], Option[(String, Student[Male])]]
//    = State(students => {
//      val persons = students.filter {
//        case _: AppointedMan[Male] => true
//        case _: Pioneer[Male] => true
//        case _: UnbaptizedPublisher[Male] => true
//        case _: SimpleBaptizedPublisher[Male] => true
//        case _: SimpleStudent[Male] => true
//        case _ => false
//      }.asInstanceOf[List[Student[Male]]]
////      println(persons)
//      val position = func(persons.length)
//      (students.filterNot(_ == persons(position)), Option(title, persons(position)))
//    }
//  )
//
//  def assignPairedAssignmentIfExists(config: Config, assignmentName: String, func: Int => Int): State[List[Student[Gender]], Option[Student[Gender]]] =
//    State(students => {
//        if (config.getObject("ApplyYourselfToFieldMinistry").containsKey("PairedAssignments")) {
//          val assignmentList = config.getList("ApplyYourselfToFieldMinistry.PairedAssignments").asScala.toList
//          if (assignmentList.contains(assignmentName)) selectAvailableStudent(func).run(students).value else (students, None)
//        } else {
//          (students, None)
//        }
//      }
//    )
//
//  def assignVideoAssignmentIfExists(config: Config, assignmentName: String, chairman: Elder[Male]): State[List[Student[Gender]], Option[Elder[Male]]] =
//    State(students => {
//        if (config.getObject("ApplyYourselfToFieldMinistry").containsKey("VideoAssignments")) {
//          val assignmentList = config.getList("ApplyYourselfToFieldMinistry.VideoAssignments").asScala.toList
//          if (assignmentList.contains(assignmentName)) (students, Option(chairman)) else (students, None)
//        } else {
//          (students, None)
//        }
//      }
//    )
//
//  def assignAdhocAssignmentIfExists(config: Config, func: Int => Int): State[List[Student[Gender]], Option[List[AdHoc]]] =
//    State(students => {
//      if (config.getObject("LivingAsChristians").containsKey("AdHoc")) {
//        val assignmentList = config.getList("LivingAsChristians.AdHoc").asScala.toList
//        val studentsWithAdhocAssignments = assignmentList.map(adhoc => selectAvailableAppointedMan(adhoc.render, func).run(students).value)
//          .map(assignees => assignees._2).map(assignment => assignment.get._2.assignAdHoc(assignment.get._1))
//        (students, Option(studentsWithAdhocAssignments))
//      } else {
//        (students, None)
//      }
//    }
//    )
//
//  def assignFiveMinutesTalkAssignmentIfExists(config: Config, assignmentName: String, func: Int => Int): State[List[Student[Gender]], Option[(String, Student[Male])]] =
//    State(students => {
//      if (config.getObject("ApplyYourselfToFieldMinistry").containsKey(assignmentName)) {
//        (selectAvailableMaleStudent(config.getString("ApplyYourselfToFieldMinistry.FiveMinutesTalk"), func).run(students).value)
//      } else {
//        (students, None)
//      }
//    }
//    )

  def createOCLMSchedule: DbReader[OCLMSchedule] =
    Reader(db => {

      val createMidWeekMeeting: State[AllStudentsTuple, MidWeekMeeting] =
        for {
          selectChairman <- selectAvailableElder(getRandomPosition)
//          selectTenMinutesTalk <- selectAvailableAppointedMan(db.config.getString("TreasuresFromGodsWord.TenMinutesTalk"), getRandomPosition)
//          selectSpiritualGems <- selectAvailableAppointedMan("", getRandomPosition)
//
//          selectBibleReading <- selectAvailableMaleStudent("", getRandomPosition)
//
//          selectInitialCallVideo <- assignVideoAssignmentIfExists(db.config, "InitialCallVideo", selectChairman.get)
//          selectReturnVisitVideo <- assignVideoAssignmentIfExists(db.config, "ReturnVisitVideo", selectChairman.get)

//          selectInitialCallPublisher <- assignPairedAssignmentIfExists(db.config, "InitialCall", getRandomPosition)
//          selectInitialCallSupport <- assignPairedAssignmentIfExists(db.config, "InitialCall", getRandomPosition)
//          selectReturnVisitPublisher <- assignPairedAssignmentIfExists(db.config, "ReturnVisit", getRandomPosition)
//          selectReturnVisitSupport <- assignPairedAssignmentIfExists(db.config, "ReturnVisit", getRandomPosition)
//          selectBibleStudyPublisher <- assignPairedAssignmentIfExists(db.config, "BibleStudy", getRandomPosition)
//          selectBibleStudySupport <- assignPairedAssignmentIfExists(db.config, "BibleStudy", getRandomPosition)

//          selectFiveMinutesTalk <- assignFiveMinutesTalkAssignmentIfExists(db.config, "FiveMinutesTalk", getRandomPosition)
//          selectAdhocs <- assignAdhocAssignmentIfExists(db.config, getRandomPosition)
//          selectCongregationBibleStudy <- selectAvailableElder(getRandomPosition)
//          selectOpeningPrayer <- selectAvailableAppointedMan("", getRandomPosition)
//          selectClosingPrayer <- selectAvailableAppointedMan("", getRandomPosition)
        } yield MidWeekMeeting(
          chairman = Chairman(selectChairman),
//          openingPrayer = OpeningPrayer(selectOpeningPrayer.get._2),
//          closingPrayer = ClosingPrayer(selectClosingPrayer.get._2),
//          tenMinutesTalk = TenMinutesTalk(selectTenMinutesTalk.get._1, selectTenMinutesTalk.get._2),
//          spiritualGems = SpiritualGems(selectSpiritualGems.get._2),
//          bibleReading = BibleReading(selectBibleReading.get._2),
//          initialCallVideo = Option(InitialCallVideo(selectInitialCallVideo.get)),
//          returnVisitVideo = Option(ReturnVisitVideo(selectReturnVisitVideo.get)),
//          initialCall = Option(Assignment.initialCall[Gender](selectInitialCall.get)),
//          returnVisit = Option(returnVisit[Gender](selectReturnVisit.get)),
//          bibleStudy = Option(bibleStudy[Gender](selectBibleStudy.get)),
//          fiveMinutesTalk = Option(FiveMinutesTalk(selectFiveMinutesTalk.get._1, selectFiveMinutesTalk.get._2)),
//          adHoc = selectAdhocs.get,
//          congregationBibleStudy = CongregationBibleStudy(selectCongregationBibleStudy.get)
        )

      val (_, midWeekMeeting) = createMidWeekMeeting.run(db.students).value

      List(midWeekMeeting)
    })
}
