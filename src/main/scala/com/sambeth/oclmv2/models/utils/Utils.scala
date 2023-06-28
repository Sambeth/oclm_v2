package com.sambeth.oclmv2.models.utils

import cats.data.{Reader, State}
import com.sambeth.oclmv2.models.Assignment.Assignment._
import com.sambeth.oclmv2.models.Assignment.Assign._
import com.sambeth.oclmv2.models.Assignment.{Assignment, AssignmentType}
import com.sambeth.oclmv2.models.Assignment.AssignmentType.ApplyYourselfToFieldMinistry
import com.sambeth.oclmv2.models.Gender.Gender
import com.sambeth.oclmv2.models.Gender.Gender._
import com.sambeth.oclmv2.models.Student.{Student, StudentGroup}
import com.sambeth.oclmv2.models.Student.Student._
import com.typesafe.config.{Config, ConfigFactory}

import scala.annotation.tailrec
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
  type AllStudentsTuple = (
    List[Elder[Male]],
      List[AppointedMan[Male]],
      List[Student[Male]],
      //    List[SimpleBaptizedPublisher[Male]],
      //    List[UnbaptizedPublisher[Male]],
      //    List[SimpleStudent[Male]],
      List[Student[Female]]
//      List[Pioneer[Female]],
//      List[SimpleBaptizedPublisher[Female]],
//      List[UnbaptizedPublisher[Female]],
//      List[SimpleStudent[Female]]
    )

  val studentMapTupled: AllStudentsTuple = (
    StudentGroup.elders.collect().toList,
    StudentGroup.ministerialServants.collect().toList ++ StudentGroup.elders.collect().toList,
    StudentGroup.malePioneers.collect().toList
      ++ StudentGroup.baptizedMalePublishers.collect().toList
      ++ StudentGroup.unbaptizedMalePublishers.collect().toList
      ++ StudentGroup.simpleMaleStudents.collect().toList,
    StudentGroup.femalePioneers.collect().toList
      ++ StudentGroup.baptizedFemalePublishers.collect().toList
      ++ StudentGroup.unbaptizedFemalePublishers.collect().toList
      ++ StudentGroup.simpleFemaleStudents.collect().toList
  )

  val scheduleConfig: Config = ConfigFactory.load("application.conf")

  final case class Db(config: Config, students: AllStudentsTuple)

  type DbReader[A] = Reader[Db, A]

  final case class MidWeekMeeting(
                               chairman: Chairman,
                               openingPrayer: OpeningPrayer,
                               closingPrayer: ClosingPrayer,
                               tenMinutesTalk: TenMinutesTalk,
                               spiritualGems: SpiritualGems,
                               bibleReading: BibleReading,
                               initialCallVideo: Option[InitialCallVideo] = None,
                               returnVisitVideo: Option[ReturnVisitVideo] = None,
                               initialCall: Option[InitialCall[_]] = None,
                               returnVisit: Option[ReturnVisit[_]] = None,
                               bibleStudy: Option[BibleStudy[_]] = None,
                               fiveMinutesTalk: Option[FiveMinutesTalk] = None,
                               adHoc: List[AdHoc] = List.empty,
                               congregationBibleStudy: CongregationBibleStudy
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
        val apppointedMen = students._2
        val position = func(elders.length)
        val elder = elders(position)
        (students.copy(_1 = elders.filterNot(_ == elder), _2 = apppointedMen.filterNot(_ == elder)), elder)
      }
    )

  def selectAvailableAppointedMan(title: String, func: Int => Int): State[AllStudentsTuple, Option[(String, AppointedMan[Male])]]
    = State(students => {
        val elders = students._1
        val appointedMen = students._2
        val position = func(appointedMen.length)
        val appointedMan = appointedMen(position)
        (students.copy(_1 = elders.filterNot(_ == appointedMan), _2 = appointedMen.filterNot(_ == appointedMan)), Option((title, appointedMan)))
      }
  )

  def selectAvailableStudent(func: Int => Int): State[AllStudentsTuple, Option[Student[Gender]]]
    = State(students => {
      val males = students._3
      val females = students._4
      val allStudents = males ++ females
      val allStudentsShuffled = Random.shuffle(allStudents)
      val position = func(allStudentsShuffled.length)
      val selected = allStudentsShuffled(position)
      (students.copy(_3 = males.filterNot(_ == selected), _4 = females.filterNot(_ == selected)), Option(selected))
    }
  )

  def selectAvailableMaleStudent(title: String, func: Int => Int): State[AllStudentsTuple, Option[(String, Student[Male])]]
    = State(students => {
      val maleStudents = students._3
      val position = func(maleStudents.length)
      val maleStudent = maleStudents(position)
      (students.copy(_3 = maleStudents.filterNot(_ == maleStudent)), Option((title, maleStudent)))
    }
  )

  def selectAvailableFemaleStudent(title: String, func: Int => Int): State[AllStudentsTuple, Option[(String, Student[Female])]]
    = State(students => {
      val femaleStudents = students._4
      val position = func(femaleStudents.length)
      val femaleStudent = femaleStudents(position)
      (students.copy(_4 = femaleStudents.filterNot(_ == femaleStudent)), Option((title, femaleStudent)))
    }
  )

  def assignFiveMinutesTalkAssignmentIfExists(config: Config, assignmentName: String, func: Int => Int): State[AllStudentsTuple, Option[(String, Student[Male])]] =
    State(students => {
      if (config.getObject("ApplyYourselfToFieldMinistry").containsKey(assignmentName)) {
        (selectAvailableMaleStudent(config.getString("ApplyYourselfToFieldMinistry.FiveMinutesTalk"), func).run(students).value)
      } else {
        (students, None)
      }
    }
  )
  def assignVideoAssignmentIfExists(config: Config, assignmentName: String, chairman: Elder[Male]): State[AllStudentsTuple, Option[Elder[Male]]] =
    State(students => {
        if (config.getObject("ApplyYourselfToFieldMinistry").containsKey("VideoAssignments")) {
          val assignmentList: List[String] = config.getList("ApplyYourselfToFieldMinistry.VideoAssignments").asScala.toList.map(_.render.stripPrefix("\"").stripSuffix("\""))
          if (assignmentList.contains(assignmentName)) (students, Option(chairman)) else (students, None)
        } else {
          (students, None)
        }
      }
    )

  @tailrec
  def assignMultipleAssignments[A <: Assignment[AssignmentType], S <: Student[Gender]]
                                (items: List[String],
                                func: Int => Int,
                                students: AllStudentsTuple,
                                assignments: List[A],
                                assignmentFunc: S => String => A,
                                stateFunc: (String, Int => Int) => State[AllStudentsTuple, Option[(String, S)]]
                               ): (AllStudentsTuple, List[A]) =
    items match {
      case x :: xs => {
        val (newState, selected) = stateFunc(x, func).run(students).value
        assignMultipleAssignments(xs,
          func,
          newState,
          assignmentFunc(selected.get._2)(x) :: assignments,
          assignmentFunc,
          stateFunc
        )
      }
      case Nil => (students, assignments)
    }

  def assignAdhocAssignmentIfExists(config: Config, func: Int => Int): State[AllStudentsTuple, List[AdHoc]] =
    State(students => {
        if (config.getObject("LivingAsChristians").containsKey("AdHoc")) {
          val assignmentList: List[String] = config.getList("LivingAsChristians.AdHoc").asScala.toList.map(_.render.stripPrefix("\"").stripSuffix("\""))
          assignMultipleAssignments[AdHoc, AppointedMan[Male]](assignmentList,
            func,
            students,
            List.empty,
            adHoc,
            selectAvailableAppointedMan)
        } else {
          (students, List.empty)
        }
      }
    )

  def assignPairedAssignmentIfExists(config: Config,  assignmentName: String, func: Int => Int): State[AllStudentsTuple, Option[Student[Gender]]] =
    State(students => {
        if (config.getObject("ApplyYourselfToFieldMinistry").containsKey("PairedAssignments")) {
          val assignmentList: List[String] = config.getList("ApplyYourselfToFieldMinistry.PairedAssignments").asScala.toList.map(_.render.stripPrefix("\"").stripSuffix("\""))
          if (assignmentList.contains(assignmentName)) {
            (selectAvailableStudent(func).run(students).value)
          } else (students, None)
        } else {
          (students, None)
        }
      }
    )


  def createOCLMSchedule: DbReader[OCLMSchedule] =
    Reader(db => {

      val createMidWeekMeeting: State[AllStudentsTuple, MidWeekMeeting] =
        for {
          selectedChairman <- selectAvailableElder(getRandomPosition)
          selectedTenMinutesTalkSpeaker <- selectAvailableAppointedMan(db.config.getString("TreasuresFromGodsWord.TenMinutesTalk"), getRandomPosition)
          selectedSpiritualGemsSpeaker <- selectAvailableAppointedMan("", getRandomPosition)
          selectedBibleReader <- selectAvailableMaleStudent("", getRandomPosition)
          selectedInitialCallVideoHandler <- assignVideoAssignmentIfExists(db.config, "InitialCallVideo", selectedChairman)
          selectedReturnVisitVideoHandler <- assignVideoAssignmentIfExists(db.config, "ReturnVisitVideo", selectedChairman)

          selectedInitialCallPublisher <- assignPairedAssignmentIfExists(db.config, "InitialCall", getRandomPosition)
//          selectInitialCallSupport <- assignPairedAssignmentIfExists(db.config, "InitialCall", getRandomPosition)
          selectedReturnVisitPublisher <- assignPairedAssignmentIfExists(db.config, "ReturnVisit", getRandomPosition)
//          selectReturnVisitSupport <- assignPairedAssignmentIfExists(db.config, "ReturnVisit", getRandomPosition)
          selectedBibleStudyPublisher <- assignPairedAssignmentIfExists(db.config, "BibleStudy", getRandomPosition)
//          selectBibleStudySupport <- assignPairedAssignmentIfExists(db.config, "BibleStudy", getRandomPosition)

          selectedFiveMinutesTalkSpeaker <- assignFiveMinutesTalkAssignmentIfExists(db.config, "FiveMinutesTalk", getRandomPosition)
          selectedAdhocHandlers <- assignAdhocAssignmentIfExists(db.config, getRandomPosition)
          selectCongregationBibleStudySpeaker <- selectAvailableElder(getRandomPosition)
          selectedOpeningPrayerSpeaker <- selectAvailableAppointedMan("", getRandomPosition)
          selectedClosingPrayerSpeaker <- selectAvailableAppointedMan("", getRandomPosition)
        } yield MidWeekMeeting(
          chairman = Chairman(selectedChairman),
          openingPrayer = OpeningPrayer(selectedOpeningPrayerSpeaker.get._2),
          closingPrayer = ClosingPrayer(selectedClosingPrayerSpeaker.get._2),
          tenMinutesTalk = TenMinutesTalk(selectedTenMinutesTalkSpeaker.get._1, selectedTenMinutesTalkSpeaker.get._2),
          spiritualGems = SpiritualGems(selectedSpiritualGemsSpeaker.get._2),
          bibleReading = BibleReading(selectedBibleReader.get._2),
          initialCallVideo = if (selectedInitialCallVideoHandler.isEmpty) None else Some(InitialCallVideo(selectedInitialCallVideoHandler.get)),
          returnVisitVideo = if (selectedReturnVisitVideoHandler.isEmpty) None else Some(ReturnVisitVideo(selectedReturnVisitVideoHandler.get)),
          initialCall = Option(Assignment.initialCall[Gender](selectedInitialCallPublisher.get)(selectedChairman)),
          returnVisit = Option(returnVisit[Gender](selectedReturnVisitPublisher.get)(selectedChairman)),
          bibleStudy = Option(bibleStudy[Gender](selectedBibleStudyPublisher.get)(selectedChairman)),
          fiveMinutesTalk = Option(FiveMinutesTalk(selectedFiveMinutesTalkSpeaker.get._1, selectedFiveMinutesTalkSpeaker.get._2)),
          adHoc = selectedAdhocHandlers,
          congregationBibleStudy = CongregationBibleStudy(selectCongregationBibleStudySpeaker)
        )

      val (_, midWeekMeeting) = createMidWeekMeeting.run(db.students).value

      List(midWeekMeeting)
    })
}
