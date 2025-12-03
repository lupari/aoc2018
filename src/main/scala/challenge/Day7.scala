package challenge

import base.Challenge

import scala.annotation.tailrec
import scala.io.Source

object Day7 extends Challenge {

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

  @tailrec
  def tsort(instructions: Map[Char, Set[Char]], acc: List[Char]): String = {
    instructions.values.flatten.toList.distinct match {
      case Nil =>
        (instructions.head._1 +: acc).reverse.mkString
      case found =>
        val instruction = (instructions.keySet -- found).min
        val avail = instructions.view.filterKeys(_ != instruction).toMap
        tsort(avail, instruction :: acc)
    }
  }

  override def run(): Any = {
    val input = Source.fromResource("day7.txt").getLines().toList
    tsort(parseEdges(input), Nil)
  }


}
