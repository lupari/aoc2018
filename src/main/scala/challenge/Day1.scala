package challenge

import base.Challenge

import scala.io.Source

object Day1 extends Challenge {

  override def run(): Any = Source.fromResource("day1.txt").getLines().map(i => i.toInt).sum

}
