package challenge

import base.Challenge

import scala.annotation.tailrec
import scala.io.Source
import scala.util.matching.Regex

object Day4 extends Challenge {

  val idPattern: Regex = ".*#(\\d+).*".r
  val minutePattern: Regex = "\\[.*\\d+:(\\d+)\\].*".r
  
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

  override def run(): Any = {
    val input = Source.fromResource("day4.txt").getLines().toList.sorted
    val guard =  getShifts(input).maxBy(_._2.length)
    val topMinute = guard._2.groupBy(identity).mapValues(_.length).maxBy(_._2)._1
    guard._1.toInt * topMinute
  }

}
