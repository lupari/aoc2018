package challenge

import base.Challenge

import scala.annotation.tailrec
import scala.io.Source
import scala.util.matching.Regex

object Day4 extends Challenge {

  val idPattern: Regex = ".*#(\\d+).*".r
  val minutePattern: Regex = "\\[.*\\d+:(\\d+)\\].*".r
  val dateTimePattern: Regex = "\\[(.*)\\].*".r

  def getShifts(xs: List[String]): Map[String, List[Int]] = {

    @tailrec
    def accumulator(shifts: List[String], current: String, fellAsleep: Option[Int], acc: Map[String, List[Int]]):
    Map[String, List[Int]] = shifts match {
      case h :: t => h match {
        case s if s.contains("begins") =>
          val idPattern(id) = s
          accumulator(t, id, None, acc)
        case s if s.contains("asleep") =>
          val minutePattern(m) = s
          accumulator(t, current, Option(m.toInt), acc)
        case s if s.contains("up") =>
          val minutePattern(m) = s
          val asleep = (fellAsleep.get until m.toInt).toList
          accumulator(t, current, None, acc + (current -> (acc(current) ++ asleep)))
      }
      case Nil => acc
    }

    accumulator(xs, "", None, Map().withDefaultValue(Nil))
  }

  def compare(s1: String, s2: String): Boolean = {
    val dateTimePattern(dt1) = s1
    val dateTimePattern(dt2) = s2
    dt1 < dt2
  }

  override def run(): Any = {
    val input = Source.fromResource("day4.txt").getLines().toList.sortWith(compare)
    val shifts: Map[String, List[Int]] = getShifts(input)
    val guard: (String, List[Int]) = shifts.maxBy(_._2.length)
    val mostPopularMinute: Int = guard._2.groupBy(identity).mapValues(_.length).maxBy(_._2)._1
    guard._1.toInt * mostPopularMinute
  }

}
