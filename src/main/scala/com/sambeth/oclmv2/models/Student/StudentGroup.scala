package com.sambeth.oclmv2.models.Student

import com.sambeth.oclmv2.models.Gender.Gender._
import com.sambeth.oclmv2.models.Student.Student._
import org.apache.spark.sql.{Dataset, SparkSession}


object StudentGroup {
  private val spark = SparkSession
    .builder()
    .appName("OCLM")
    .config("spark.master", "local")
    .getOrCreate()

  import spark.implicits._

  private val membersDf: Unit = spark
    .read
    .option("header", "true")
    .csv("C:\\Users\\sambe\\Documents\\projects\\oclm-v2\\src\\main\\resources\\members.csv")
    .createOrReplaceTempView("membersDf")

  // filter only those available
  private val availableBatch: Unit = spark.sql(
    "SELECT * FROM membersDf WHERE availability = 'Yes' AND student = 'Yes'"
  ).createOrReplaceTempView("availableStudents")

  private val females: Dataset[Female] = spark.sql(
    "SELECT * FROM availableStudents WHERE gender = 'Female'"
  ).as[Female]

  val simpleFemaleStudents: Dataset[SimpleStudent[Female]] = females
    .where("publisher = 'No'").map(female => SimpleStudent(female))

  val unbaptizedFemalePublishers: Dataset[UnbaptizedPublisher[Female]] = females
    .where("publisher = 'Yes' AND baptized = 'No'").map(female => UnbaptizedPublisher(female))

  val baptizedFemalePublishers: Dataset[SimpleBaptizedPublisher[Female]] = females
    .where("publisher = 'Yes' AND baptized = 'Yes' AND pioneer = 'No'").map(female => SimpleBaptizedPublisher(female))

  val femalePioneers: Dataset[Pioneer[Female]] = females
    .where("pioneer = 'Yes'").map(female => Pioneer(female))

  //  // filter only those available
  private val males: Dataset[Male] = spark.sql(
    "SELECT * FROM availableStudents WHERE gender = 'Male'"
  ).as[Male]

  val simpleMaleStudents: Dataset[SimpleStudent[Male]] = males
    .where("publisher = 'No'").map(male => SimpleStudent(male))

  val unbaptizedMalePublishers: Dataset[UnbaptizedPublisher[Male]] = males
    .where("publisher = 'Yes' AND baptized = 'No'").map(male => UnbaptizedPublisher(male))

  val baptizedMalePublishers: Dataset[SimpleBaptizedPublisher[Male]] = males
    .where("publisher = 'Yes' AND baptized = 'Yes' AND pioneer = 'No' AND ministerialServant = 'No' AND elder = 'No'").map(male => SimpleBaptizedPublisher(male))

  val malePioneers: Dataset[Pioneer[Male]] = males
    .where("pioneer = 'Yes' AND ministerialServant = 'No' AND elder = 'No'").map(male => Pioneer(male))

  val ministerialServants: Dataset[MinisterialServant[Male]] = males
    .where("ministerialServant = 'Yes'").map(male => MinisterialServant(male))

  val elders: Dataset[Elder[Male]] = males
    .where("elder = 'Yes'").map(male => Elder(male))
}
