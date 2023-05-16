package com.sambeth.oclmv2.models.utils

import cats.data.Kleisli
import com.sambeth.oclmv2.models.Assignment.Assignment._
import com.sambeth.oclmv2.models.Assignment.Assign._
import com.sambeth.oclmv2.models.Gender.Gender
import com.sambeth.oclmv2.models.Gender.Gender._
import com.sambeth.oclmv2.models.Student.Student
import com.sambeth.oclmv2.models.Student.Student._


object Utils {

  // appointed men assignments
  val assignChairman: Kleisli[Option, Elder[Male], Chairman] =
    Kleisli((e: Elder[Male]) => Option(e.assignChairman))

  val assignOpeningPrayer: Kleisli[Option, AppointedMan[Male], OpeningPrayer] =
    Kleisli((e: AppointedMan[Male]) => Option(e.assignOpeningPrayer))

  val assignClosingPrayer: Kleisli[Option, AppointedMan[Male], ClosingPrayer] =
    Kleisli((e: AppointedMan[Male]) => Option(e.assignClosingPrayer))

  val assignTenMinutesTalk: Kleisli[Option, AppointedMan[Male], String => TenMinutesTalk] =
    Kleisli((e: AppointedMan[Male]) => Option(e.assignTenMinutesTalk))

  val assignInitialCallVideo: Kleisli[Option, Elder[Male], InitialCallVideo] =
    Kleisli((e: Elder[Male]) => Option(e.assignInitialCallVideo))

  val assignReturnVisitVideo: Kleisli[Option, Elder[Male], ReturnVisitVideo] =
    Kleisli((e: Elder[Male]) => Option(e.assignReturnVisitVideo))

  val assignAdHoc: Kleisli[Option, AppointedMan[Male], AdHoc] =
    Kleisli((e: AppointedMan[Male]) => Option(e.assignAdHoc("")))

  val assignCongregationBibleStudy: Kleisli[Option, Elder[Male], CongregationBibleStudy] =
    Kleisli((e: Elder[Male]) => Option(e.assignCongregationBibleStudy))

  // school assignments
  def assignInitialCall[G <: Gender]: Kleisli[Option, Student[G], Student[G] => InitialCall[G]] =
    Kleisli((e: Student[G]) => Option(e.assignInitialCall))

  def assignReturnVisit[G <: Gender]: Kleisli[Option, Student[G], Student[G] => ReturnVisit[G]] =
    Kleisli((e: Student[G]) => Option(e.assignReturnVisit))

  def assignBibleStudy[G <: Gender]: Kleisli[Option, Student[G], Student[G] => BibleStudy[G]] =
    Kleisli((e: Student[G]) => Option(e.assignBibleStudy))

  val assignFiveMinutesTalk: Kleisli[Option, Student[Male], FiveMinutesTalk] =
    Kleisli((e: Student[Male]) => Option(e.assignFiveMinutesTalk))
}
