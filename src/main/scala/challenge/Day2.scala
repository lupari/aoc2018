package challenge

import base.Challenge

import scala.io.Source

object Day2 extends Challenge {

  override def run(): Any = {
    val input = Source.fromResource("day2.txt").getLines().toList
    val doubles = input.filter(_.groupBy(identity).mapValues(_.length).count(_._2 == 2) > 0)
    val triples = input.filter(_.groupBy(identity).mapValues(_.length).count(_._2 == 3) > 0)
    doubles.length * triples.length
  }

}
