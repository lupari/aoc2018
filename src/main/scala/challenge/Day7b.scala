package challenge

import base.Challenge

import scala.annotation.tailrec
import scala.collection.immutable.SortedSet
import scala.io.Source

object Day7b extends Challenge {

  private val pattern = "Step (.?).* step (.?).*".r
  def parse(s: String): (Char, Char) = s match {
    case pattern(i, j) => i.head -> j.head
  }

  def parseEdges(input: List[String]): Map[Char, Set[Char]] = {
    input.map(parse).foldLeft(Map.empty[Char, Set[Char]])({
      case (acc, (reqStep, step)) =>
        acc +
          (reqStep -> (acc.getOrElse(reqStep, Set()) + step)) +
          (step -> acc.getOrElse(step, Set()))
    })
  }

  case class Work(instruction: Char, timeLeft: Int) {
    def -(time: Int): Work = copy(timeLeft = timeLeft - time)
  }
  object Work {
    def apply(instruction: Char): Work = Work(instruction, 60 + (instruction - 'A' + 1))
  }

  private def timePassed(instructions: Map[Char, Set[Char]], works: Set[Work]) = {
    val earliestFinish = works.map(_.timeLeft).min
    val progressedWorks = works.map(_.-(earliestFinish))
    val (finished, rest) = progressedWorks.partition(_.timeLeft == 0)
    val finishedInstructions = finished.map(_.instruction)
    val unfinishedInstructions = instructions -- finishedInstructions

    (unfinishedInstructions, rest, earliestFinish)
  }

  def tsort(instructions: Map[Char, Set[Char]], works: Set[Work]): Set[Work] = {
    instructions.values.reduceOption(_ ++ _) match {
      case None => Set.empty
      case Some(found) =>
        val instructionsUnderWork = works.map(_.instruction)
        val eligible = instructions.keySet -- found -- instructionsUnderWork
        SortedSet.from(eligible).take(5 - works.size).toList.map(Work(_)).toSet
    }
  }

  @tailrec
  def workingTime(xs: Map[Char, Set[Char]], acc: Set[Work], t: Int): Int = {
    if (xs.isEmpty && acc.isEmpty) {
      return t
    }
    val progressedWorks = acc ++ tsort(xs, acc)
    val (i, w, t2) = timePassed(xs, progressedWorks)
    workingTime(i, w, t + t2)
  }

  override def run(): Any = {
    val input = Source.fromResource("day7.txt").getLines().toList
    workingTime(parseEdges(input), Set.empty, 0)
  }

}
