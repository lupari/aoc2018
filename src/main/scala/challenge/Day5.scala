package challenge

import base.Challenge

import scala.io.Source

object Day5 extends Challenge {

  def makeReaction(polymer: String): String = {
    polymer.foldRight(List[Char]())((c, acc) => (c, acc) match {
      case (x, h :: t) if math.abs(h - x) == 32 => t
      case _ => c +: acc
    }).mkString
  }

  override def run(): Any = {
    val input = Source.fromResource("day5.txt").getLines().mkString
    makeReaction(input).length
  }

}
