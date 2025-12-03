package challenge

import base.Challenge

import scala.io.Source

object Day5b extends Challenge {

  def makeReaction(polymer: String): String = {
    polymer.foldRight(List[Char]())((c, acc) => (c, acc) match {
      case (x, h :: t) if math.abs(h - x) == 32 => t
      case _ => c +: acc
    }).mkString
  }

  def erase(s: String, char: Char): String = s.filterNot(c => c.toLower == char)

  override def run(): Any = {
    val input = Source.fromResource("day5.txt").getLines().mkString
    val polymers = ('a' to 'z').map(erase(input, _)).map(makeReaction)
    polymers.minBy(_.length).length
  }

}

