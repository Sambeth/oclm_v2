package com.sambeth.oclmv2.models.Student

import com.sambeth.oclmv2.models.Gender.Gender


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

//  implicit class maleStudentOtherAssignment[F[_], G](x: Elder[Male]) {
//    def assignChairman: Chairman = Chairman(x)
//  }

}