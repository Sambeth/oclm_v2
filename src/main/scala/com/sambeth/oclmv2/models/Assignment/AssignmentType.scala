package com.sambeth.oclmv2.models.Assignment

sealed trait AssignmentType

object AssignmentType {
  trait Others extends AssignmentType
  trait TreasuresFromGodsWord extends AssignmentType
  trait ApplyYourselfToFieldMinistry extends AssignmentType
  trait LivingAsChristians extends AssignmentType
}
