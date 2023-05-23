package com.sambeth.oclmv2.models.utils

import cats.data.{Reader, State}
import com.sambeth.oclmv2.models.Assignment.Assignment._
import com.sambeth.oclmv2.models.Assignment.Assign._
import com.sambeth.oclmv2.models.Gender.Gender
import com.sambeth.oclmv2.models.Gender.Gender._
import com.sambeth.oclmv2.models.Student.{Student, StudentGroup}
import com.sambeth.oclmv2.models.Student.Student._
import com.typesafe.config.{Config, ConfigFactory}

import scala.util.Random


object Utils {

  // appointed men assignments
  val assignChairman: Reader[Elder[Male], Chairman] =
    Reader((e: Elder[Male]) => e.assignChairman)

  val assignOpeningPrayer: Reader[AppointedMan[Male], OpeningPrayer] =
    Reader((e: AppointedMan[Male]) => e.assignOpeningPrayer)

  val assignClosingPrayer: Reader[AppointedMan[Male], ClosingPrayer] =
    Reader((e: AppointedMan[Male]) => e.assignClosingPrayer)

  val assignTenMinutesTalk: Reader[AppointedMan[Male], String => TenMinutesTalk] =
    Reader((e: AppointedMan[Male]) => e.assignTenMinutesTalk)

  val assignInitialCallVideo: Reader[Elder[Male], InitialCallVideo] =
    Reader((e: Elder[Male]) => e.assignInitialCallVideo)

  val assignReturnVisitVideo: Reader[Elder[Male], ReturnVisitVideo] =
    Reader((e: Elder[Male]) => e.assignReturnVisitVideo)

  val assignAdHoc: Reader[AppointedMan[Male], String => AdHoc] =
    Reader((e: AppointedMan[Male]) => e.assignAdHoc)

  val assignCongregationBibleStudy: Reader[Elder[Male], CongregationBibleStudy] =
    Reader((e: Elder[Male]) => e.assignCongregationBibleStudy)

  // school assignments
  def assignInitialCall[G <: Gender]: Reader[Student[G], Student[G] => InitialCall[G]] =
    Reader((e: Student[G]) => e.assignInitialCall)

  def assignReturnVisit[G <: Gender]: Reader[Student[G], Student[G] => ReturnVisit[G]] =
    Reader((e: Student[G]) => e.assignReturnVisit)

  def assignBibleStudy[G <: Gender]: Reader[Student[G], Student[G] => BibleStudy[G]] =
    Reader((e: Student[G]) => e.assignBibleStudy)

  val assignFiveMinutesTalk: Reader[Student[Male], FiveMinutesTalk] =
    Reader((e: Student[Male]) => e.assignFiveMinutesTalk)

  val studentsMap: Map[String, List[Student[Gender]]] = Map(
    "elders" -> StudentGroup.elders.collect().toList,
    "ministerialServants" -> StudentGroup.ministerialServants.collect().toList,
    "malePioneers" -> StudentGroup.malePioneers.collect().toList,
    "baptizedMalePublishers" -> StudentGroup.baptizedMalePublishers.collect().toList,
    "unbaptizedMalePublishers" -> StudentGroup.unbaptizedMalePublishers.collect().toList,
    "simpleMaleStudents" -> StudentGroup.simpleMaleStudents.collect().toList,
    "femalePioneers" -> StudentGroup.femalePioneers.collect().toList,
    "baptizedFemalePublishers" -> StudentGroup.baptizedFemalePublishers.collect().toList,
    "unbaptizedFemalePublishers" -> StudentGroup.unbaptizedFemalePublishers.collect().toList,
    "simpleFemaleStudents" -> StudentGroup.simpleFemaleStudents.collect().toList,
  )

  val studentsGenderMap: Map[String, List[Student[Gender]]] = Map(
    "males" -> (
      studentsMap("elders")
      ++ studentsMap("ministerialServants")
      ++ studentsMap("malePioneers")
      ++ studentsMap("baptizedMalePublishers")
      ++ studentsMap("unbaptizedMalePublishers")
      ++ studentsMap("simpleMaleStudents")
    ),
    "females" -> (
      studentsMap("femalePioneers")
      ++ studentsMap("baptizedFemalePublishers")
      ++ studentsMap("unbaptizedFemalePublishers")
      ++ studentsMap("simpleFemaleStudents")
    )
  )

  val allStudents: List[Student[Gender]] = (studentsGenderMap("males") ++ studentsGenderMap("females"))

  val scheduleConfig: Config = ConfigFactory.load("application.conf")

  final case class Db(config: Config, students: List[Student[Gender]])

  type DbReader[A] = Reader[Db, A]

  final case class MidWeekMeeting(
                               chairman: Chairman,
                               openingPrayer: OpeningPrayer,
                               closingPrayer: ClosingPrayer,
                               tenMinutesTalk: TenMinutesTalk,
                               spiritualGems: SpiritualGems,
                               bibleReading: Option[BibleReading] = None,
                               initialCallVideo: Option[InitialCallVideo] = None,
                               returnVisitVideo: Option[ReturnVisitVideo] = None,
                               initialCall: Option[InitialCall[_]] = None,
                               returnVisit: Option[ReturnVisit[_]] = None,
                               bibleStudy: Option[BibleStudy[_]] = None,
                               fiveMinutesTalk: Option[FiveMinutesTalk] = None,
                               adHoc: Option[List[AdHoc]] = None,
                               congregationBibleStudy: CongregationBibleStudy
                               )

  type OCLMSchedule = List[MidWeekMeeting]

  /* selecting appointed men parts first
  1. select chairman = done
  2. select opening prayer = done
  3. select closing prayer = done
  4. select ten minutes talk = done
  5. select spiritual gems
  6. select adhocs
  7. select bible study
   */
  def getRandomPosition(studentSize: Int): Int = {
    val seed = scheduleConfig.getLong("Seed.Long")
    val random = new Random(seed)
    val randomIndex = random.nextInt(studentSize)
    randomIndex
  }

  def selectAvailableElder(func: Int => Int): State[List[Student[Gender]], Elder[Male]]
    = State(students => {
        val elders = students.filter {
          case s: Elder[Male] => true
          case _ => false
        }.asInstanceOf[List[Elder[Male]]]
        val position = func(elders.length)
        (students.filterNot(_ == elders(position)), elders(position))
      }
    )

  def selectAvailableAppointedMan(func: Int => Int): State[List[Student[Gender]], AppointedMan[Male]]
    = State(students => {
        val appointedMan = students.filter {
          case s: AppointedMan[Male] => true
          case _ => false
        }.asInstanceOf[List[AppointedMan[Male]]]
        val position = func(appointedMan.length)
        (students.filterNot(_ == appointedMan(position)), appointedMan(position))
      }
  )

  def createOCLMSchedule: DbReader[OCLMSchedule] =
    Reader(db => {

      val createMidWeekMeeting: State[List[Student[Gender]], MidWeekMeeting] =
        for {
          selectChairman <- selectAvailableElder(getRandomPosition)
          selectTenMinutesTalk <- selectAvailableAppointedMan(getRandomPosition)
          selectSpiritualGems <- selectAvailableAppointedMan(getRandomPosition)
          selectCongregationBibleStudy <- selectAvailableElder(getRandomPosition)
          selectOpeningPrayer <- selectAvailableAppointedMan(getRandomPosition)
          selectClosingPrayer <- selectAvailableAppointedMan(getRandomPosition)
        } yield MidWeekMeeting(
          chairman = Chairman(selectChairman),
          openingPrayer = OpeningPrayer(selectOpeningPrayer),
          closingPrayer = ClosingPrayer(selectClosingPrayer),
          tenMinutesTalk = TenMinutesTalk(db.config.getString("TreasuresFromGodsWord.TenMinutesTalk"), selectTenMinutesTalk),
          spiritualGems = SpiritualGems(selectSpiritualGems),
          congregationBibleStudy = CongregationBibleStudy(selectCongregationBibleStudy)
        )

      val (_, midWeekMeeting) = createMidWeekMeeting.run(db.students).value

      List(midWeekMeeting)
    })
}
