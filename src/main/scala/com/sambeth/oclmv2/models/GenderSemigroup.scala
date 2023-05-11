package com.sambeth.oclmv2.models


trait GenderSemigroup[G] {
  def checkMatch(x: G, y: G): Boolean
}

object GenderSemigroup {
  def apply[G](implicit g: GenderSemigroup[G]): GenderSemigroup[G] = g

  implicit def genderSemigroup[G <: Gender]: GenderSemigroup[G] = new GenderSemigroup[G] {
    def checkMatch(x: G, y: G): Boolean =
      (x, y) match {
        case (x: Male, y: Male) => true
        case (x: Female, y: Female) => true
      }
  }

  implicit class genderSemiGroupOps[G <: Gender](x: G) {
    def +(y: G)(implicit g: GenderSemigroup[G]): Boolean = g.checkMatch(x, y)
  }
}