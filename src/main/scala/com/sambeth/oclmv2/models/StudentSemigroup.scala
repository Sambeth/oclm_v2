package com.sambeth.oclmv2.models

import Gender.{Female, Male}


trait StudentSemigroup[F[_]] {
  def pair[G](x: F[G], y: F[G]): (F[G], F[G])
}

object StudentSemigroup {
  implicit val studentGenderSemigroup: StudentSemigroup[Student] = new StudentSemigroup[Student] {
    def pair[G](x: Student[G], y: Student[G]): (Student[G], Student[G]) =
      (x, y) match {
        case (x: Student[Male], y: Student[Male]) => (x, y)
        case (x: Student[Female], y: Student[Female]) => (x, y)
      }
  }

  implicit class studentGenderSemigroupOps[F[_], G](x: F[G]) {
    def |+|(y: F[G])(implicit s: StudentSemigroup[F]): (F[G], F[G]) = s.pair(x, y)
  }

}