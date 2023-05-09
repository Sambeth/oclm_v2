package com.sambeth.oclmv2.models

sealed trait Gender {
  def age: Option[Int] = None
}

object Gender {
  case class Female(name: String) extends Gender
  case class Male(name: String) extends Gender

//  implicit val femaleSemigroup: Semigroup[Female] = new Semigroup[Female] {
//    override def combine(x: Female, y: Female): Female = x
//  }
//
//  implicit val maleSemigroup: Semigroup[Male] = new Semigroup[Male] {
//    override def combine(x: Male, y: Male): Male = x
//  }
//
//  implicit class genderSemigroupOps[G](x: G) {
//    def |+|(y: G)(implicit s: Semigroup[G]) = s.combine(x, y)
//  }

}
