package com.sambeth.oclmv2.models.utils

import cats.data.Reader
import com.sambeth.oclmv2.models.Assignment.Assignment._
import com.sambeth.oclmv2.models.Assignment.Assign._
import com.sambeth.oclmv2.models.Gender.Gender
import com.sambeth.oclmv2.models.Gender.Gender._
import com.sambeth.oclmv2.models.Student.Student
import com.sambeth.oclmv2.models.Student.Student._


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
}
