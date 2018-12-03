package challenge

import base.Challenge

import scala.io.Source
import scala.util.matching.Regex

object Day3b extends Challenge {

  case class Claim(id: Int, x: Int, y: Int, w: Int, h: Int) {
    def toSet: Set[(Int, Int)] = {
      (for {i <- x until x + w;
            j <- y until y + h} yield (i, j)
      ).toSet
    }
  }

  def parse(s: String): Claim = {
    val pattern: Regex = "#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)".r
    val pattern(id, x, y, w, h) = s
    Claim(id.toInt, x.toInt, y.toInt, w.toInt, h.toInt)
  }

  override def run(): Any = {
    val claims = Source.fromResource("day3.txt").getLines().map(parse).toList
    val patches: List[(Int, Int)] = claims.flatMap(c => {
      (for {i <- c.x until c.x + c.w;
            j <- c.y until c.y + c.h} yield (i, j)
      ).toList
    })
    val nonConflicting: Set[(Int, Int)] =
      patches.groupBy(p => (p._1, p._2)).filter(_._2.length == 1).keySet

    claims.find(_.toSet.subsetOf(nonConflicting)).get.id
  }

}
