package challenge

import base.Challenge

import scala.io.Source
import scala.util.matching.Regex

object Day3 extends Challenge {

  case class Claim(x: Int, y: Int, w: Int, h: Int)

  def parse(s: String): Claim = {
    val pattern: Regex = ".* @ (\\d+),(\\d+): (\\d+)x(\\d+)".r
    val pattern(x, y, w, h) = s
    Claim(x.toInt, y.toInt, w.toInt, h.toInt)
  }

  override def run(): Any = {
    val claims = Source.fromResource("day3.txt").getLines().map(parse).toList
    val patches: List[(Int, Int)] = claims.flatMap(c => {
      (for {i <- c.x until c.x + c.w;
           j <- c.y until c.y + c.h} yield (i, j)
      ).toList
    })

    patches.groupBy(p => (p._1, p._2)).count(_._2.length > 1)
  }

}
