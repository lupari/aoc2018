package challenge

import base.Challenge

import scala.io.Source

object Day2b extends Challenge {

  def difference(s1: String, s2: String): Int = s1.zip(s2).count(c => c._1 != c._2)

  override def run(): Any = {
    val input = Source.fromResource("day2.txt").getLines().toList
    val boxes = input.combinations(2).filter(c => difference(c.head, c.last) == 1).flatten.toList
    boxes.head.zip(boxes.last).filter(b => b._1 == b._2).map(_._1).mkString
  }

}
