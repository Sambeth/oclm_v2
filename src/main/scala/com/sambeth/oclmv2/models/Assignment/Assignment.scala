package com.sambeth.oclmv2.models.Assignment

import com.sambeth.oclmv2.models.Assignment.AssignmentType._
import com.sambeth.oclmv2.models.Gender.Male
import com.sambeth.oclmv2.models.Student.{Elder, Student}

trait Assignment[T]

object Assignment {
  case class Chairman(owner: Student[Male]) extends Assignment[Others]

  case class OpeningPrayer(owner: Student[Male]) extends Assignment[Others]

  case class ClosingPrayer(owner: Student[Male]) extends Assignment[Others]

  case class TenMinutesTalk(owner: Student[Male]) extends Assignment[TreasuresFromGodsWord]

  case class SpiritualGems(owner: Student[Male]) extends Assignment[TreasuresFromGodsWord]

  case class BibleReading(owner: Student[Male]) extends Assignment[TreasuresFromGodsWord]

  case class InitialCallVideo(owner: Student[Male]) extends Assignment[ApplyYourselfToFieldMinistry]

  case class ReturnVisitVideo(owner: Student[Male]) extends Assignment[ApplyYourselfToFieldMinistry]

  case class InitialCall[G](owner: Student[G], support: Student[G]) extends Assignment[ApplyYourselfToFieldMinistry]

  case class ReturnVisit[G](owner: Student[G], support: Student[G]) extends Assignment[ApplyYourselfToFieldMinistry]

  case class BibleStudy[G](owner: Student[G], support: Student[G]) extends Assignment[ApplyYourselfToFieldMinistry]

  case class FiveMinutesTalk(owner: Student[Male]) extends Assignment[ApplyYourselfToFieldMinistry]

  case class AdHoc(title: String, owner: Student[Male]) extends Assignment[LivingAsChristians]

  case class CongregationBibleStudy(owner: Elder[Male]) extends Assignment[LivingAsChristians]
}
