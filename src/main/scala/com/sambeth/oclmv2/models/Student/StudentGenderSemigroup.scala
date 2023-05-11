package com.sambeth.oclmv2.models.Student

import com.sambeth.oclmv2.models.Assignment.Assignment._
import com.sambeth.oclmv2.models.Gender.{Gender, Male}


trait StudentGenderSemigroup[S] {
  def pair(x: S, y: S): (S, S)
}

object StudentGenderSemigroup {
  def apply[S](implicit s: StudentGenderSemigroup[S]): StudentGenderSemigroup[S] = s

  implicit def studentGenderSemigroup[G <: Gender]: StudentGenderSemigroup[Student[G]] = new StudentGenderSemigroup[Student[G]] {
    def pair(x: Student[G], y: Student[G]): (Student[G], Student[G]) = {
      (x, y) match {
        // valid elder pairings
        case (Elder(_), Elder(_)) => (x, y)
        case (Elder(_), MinisterialServant(_)) => (x, y)
        case (Elder(_), Pioneer(_)) => (x, y)
        case (Elder(_), SimpleBaptizedPublisher(_)) => (x, y)
        case (Elder(_), UnbaptizedPublisher(_)) => (x, y)
        case (Elder(_), SimpleStudent(_)) => (x, y)

        // valid ministerial servant pairings
        case (MinisterialServant(_), MinisterialServant(_)) => (x, y)
        case (MinisterialServant(_), Pioneer(_)) => (x, y)
        case (MinisterialServant(_), SimpleBaptizedPublisher(_)) => (x, y)
        case (MinisterialServant(_), UnbaptizedPublisher(_)) => (x, y)
        case (MinisterialServant(_), SimpleStudent(_)) => (x, y)

        // valid pioneer pairings
        case (Pioneer(_), MinisterialServant(_)) => (x, y)
        case (Pioneer(_), Pioneer(_)) => (x, y)
        case (Pioneer(_), SimpleBaptizedPublisher(_)) => (x, y)
        case (Pioneer(_), UnbaptizedPublisher(_)) => (x, y)
        case (Pioneer(_), SimpleStudent(_)) => (x, y)

        // valid simple baptized publisher pairings
        case (SimpleBaptizedPublisher(_), SimpleBaptizedPublisher(_)) => (x, y)
        case (SimpleBaptizedPublisher(_), UnbaptizedPublisher(_)) => (x, y)
        case (SimpleBaptizedPublisher(_), SimpleStudent(_)) => (x, y)

        // valid unbaptized publisher pairings
        case (UnbaptizedPublisher(_), UnbaptizedPublisher(_)) => (x, y)
        case (UnbaptizedPublisher(_), SimpleStudent(_)) => (x, y)

        // valid simple student pairings
        case (SimpleStudent(_), SimpleStudent(_)) => (x, y)

        // invalid pairings
        // Make it functional at a point
        case _ => throw new Exception("Invalid pairing")
      }
    }
  }

  implicit class studentGenderSemigroupOps[G <: Gender](x: Student[G]) {
    def |+|(y: Student[G])(implicit s: StudentGenderSemigroup[Student[G]]): (Student[G], Student[G]) = s.pair(x, y)
  }

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
    def assignTenMinutesTalk: TenMinutesTalk = TenMinutesTalk(owner)
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
    def assignInitialCall(support: Student[G]): InitialCall[G] = {
      InitialCall(owner, support)
    }
  }

  implicit class returnVisitAssignment[G <: Gender](owner: Student[G]) {
    def assignReturnVisit(support: Student[G]): ReturnVisit[G] =
      ReturnVisit(owner, support)
  }

  implicit class bibleStudyAssignment[G <: Gender](owner: Student[G]) {
    def assignBibleStudy(support: Student[G]): BibleStudy[G] =
      BibleStudy(owner, support)
  }

  implicit class fiveMinutesTalkAssignment(owner: Student[Male]) {
    def assignFiveMinutesTalk: FiveMinutesTalk = FiveMinutesTalk(owner)
  }

  implicit class adhocAssignment(owner: AppointedMan[Male]) {
    def assignAdHoc(title: String): AdHoc = AdHoc(title, owner)
  }

  implicit class congregationBibleStudyAssignment(owner: Elder[Male]) {
    def assignCongregationBibleStudy: CongregationBibleStudy = CongregationBibleStudy(owner)
  }
}