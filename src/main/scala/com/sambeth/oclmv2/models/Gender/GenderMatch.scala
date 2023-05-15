package com.sambeth.oclmv2.models.Gender

trait GenderMatch[G] {
  def checkMatch(x: G, y: G): Boolean
}

object GenderMatch {
  def apply[G](implicit g: GenderMatch[G]): GenderMatch[G] = g

  implicit def genderMatch[G <: Gender]: GenderMatch[G] = new GenderMatch[G] {
    def checkMatch(x: G, y: G): Boolean =
      (x, y) match {
        case (x: Male, y: Male) => true
        case (x: Female, y: Female) => true
      }
  }

  implicit class genderSemiGroupOps[G <: Gender](x: G) {
    def +(y: G)(implicit g: GenderMatch[G]): Boolean = g.checkMatch(x, y)
  }
}