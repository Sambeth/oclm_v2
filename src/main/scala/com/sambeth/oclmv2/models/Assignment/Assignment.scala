package com.sambeth.oclmv2.models.Assignment

import com.sambeth.oclmv2.models.Assignment.AssignmentType._
import com.sambeth.oclmv2.models.Gender.Gender._
import com.sambeth.oclmv2.models.Student.Student
import com.sambeth.oclmv2.models.Student.Student._

trait Assignment[+T]

object Assignment {
  case class Chairman(owner: Elder[Male]) extends Assignment[Others]

  case class OpeningPrayer(owner: AppointedMan[Male]) extends Assignment[Others]

  case class ClosingPrayer(owner: AppointedMan[Male]) extends Assignment[Others]

  case class TenMinutesTalk(title: String, owner: AppointedMan[Male]) extends Assignment[TreasuresFromGodsWord]

  case class SpiritualGems(owner: AppointedMan[Male]) extends Assignment[TreasuresFromGodsWord]

  case class BibleReading(owner: Student[Male]) extends Assignment[TreasuresFromGodsWord]

  case class InitialCallVideo(owner: Elder[Male]) extends Assignment[ApplyYourselfToFieldMinistry]

  case class ReturnVisitVideo(owner: Elder[Male]) extends Assignment[ApplyYourselfToFieldMinistry]

  case class InitialCall[G](owner: Student[G], support: Student[G]) extends Assignment[ApplyYourselfToFieldMinistry]

  case class ReturnVisit[G](owner: Student[G], support: Student[G]) extends Assignment[ApplyYourselfToFieldMinistry]

  case class BibleStudy[G](owner: Student[G], support: Student[G]) extends Assignment[ApplyYourselfToFieldMinistry]

  case class FiveMinutesTalk(title: String, owner: Student[Male]) extends Assignment[ApplyYourselfToFieldMinistry]

  case class AdHoc(title: String, owner: AppointedMan[Male]) extends Assignment[LivingAsChristians]

  case class CongregationBibleStudy(owner: Elder[Male]) extends Assignment[LivingAsChristians]

  def initialCall[G](owner: Student[G])(support: Student[G]): InitialCall[G] = InitialCall(owner, support)
  def returnVisit[G](owner: Student[G])(support: Student[G]): ReturnVisit[G] = ReturnVisit(owner, support)
  def bibleStudy[G](owner: Student[G])(support: Student[G]): BibleStudy[G] = BibleStudy(owner, support)

  def tenMinutesTalk(owner: AppointedMan[Male])(title: String): TenMinutesTalk = TenMinutesTalk(title, owner)

  def fiveMinutesTalk(owner: Student[Male])(title: String): FiveMinutesTalk = FiveMinutesTalk(title, owner)
  def adHoc(owner: AppointedMan[Male])(title: String): AdHoc = AdHoc(title, owner)
}
