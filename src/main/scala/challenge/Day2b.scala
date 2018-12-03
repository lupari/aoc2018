package challenge

import base.Challenge

import scala.io.Source

object Day2b extends Challenge {

  def difference(s1: String, s2: String): Int = s1.zip(s2).count(p => p._1 != p._2)

  override def run(): Any = {
    val input = Source.fromResource("day2.txt").getLines().toList
    val boxes = input.combinations(2).filter(x => difference(x.head, x.last) == 1).flatten.toList
    boxes.head.zip(boxes.last).filter(p => p._1 == p._2).map(_._1).mkString
  }

}
