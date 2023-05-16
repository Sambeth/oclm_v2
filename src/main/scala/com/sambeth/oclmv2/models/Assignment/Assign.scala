package com.sambeth.oclmv2.models.Assignment

import com.sambeth.oclmv2.models.Assignment.Assignment._
import com.sambeth.oclmv2.models.Gender.Gender
import com.sambeth.oclmv2.models.Gender.Gender.Male
import com.sambeth.oclmv2.models.Student.Student
import com.sambeth.oclmv2.models.Student.Student.{AppointedMan, Elder}

object Assign {

  implicit class chairmanAssignment(owner: Elder[Male]) {
    def assignChairman: Chairman = Chairman(owner)
  }

  implicit class openingPrayerAssignment(owner: AppointedMan[Male]) {
    def assignOpeningPrayer: OpeningPrayer = OpeningPrayer(owner)
  }

  implicit class closingPrayerAssignment(owner: AppointedMan[Male]) {
    def assignClosingPrayer: ClosingPrayer = ClosingPrayer(owner)
  }

  implicit class tenMinutesTalkAssignment(owner: AppointedMan[Male]) {
    def assignTenMinutesTalk: String => TenMinutesTalk = tenMinutesTalk(owner)
  }

  implicit class spiritualGemsAssignment(owner: AppointedMan[Male]) {
    def assignSpiritualGems: SpiritualGems = SpiritualGems(owner)
  }

  implicit class bibleReadingAssignment(owner: Student[Male]) {
    def assignBibleReading: BibleReading = BibleReading(owner)
  }

  implicit class initialCallVideoAssignment(owner: Elder[Male]) {
    def assignInitialCallVideo: InitialCallVideo = InitialCallVideo(owner)
  }

  implicit class returnVisitVideoAssignment(owner: Elder[Male]) {
    def assignReturnVisitVideo: ReturnVisitVideo = ReturnVisitVideo(owner)
  }

  // add checks to make sure we have the right pairings
  implicit class initialCallAssignment[G <: Gender](owner: Student[G]) {
    def assignInitialCall: Student[G] => InitialCall[G] = initialCall(owner)
  }

  implicit class returnVisitAssignment[G <: Gender](owner: Student[G]) {
    def assignReturnVisit: Student[G] => ReturnVisit[G] = returnVisit(owner)
  }

  implicit class bibleStudyAssignment[G <: Gender](owner: Student[G]) {
    def assignBibleStudy: Student[G] => BibleStudy[G] = bibleStudy(owner)
  }

  implicit class fiveMinutesTalkAssignment(owner: Student[Male]) {
    def assignFiveMinutesTalk: FiveMinutesTalk = FiveMinutesTalk(owner)
  }

  implicit class adhocAssignment(owner: AppointedMan[Male]) {
    def assignAdHoc: String => AdHoc = adHoc(owner)
  }

  implicit class congregationBibleStudyAssignment(owner: Elder[Male]) {
    def assignCongregationBibleStudy: CongregationBibleStudy = CongregationBibleStudy(owner)
  }

}
