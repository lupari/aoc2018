package challenge

import base.Challenge

import scala.io.Source
import scala.util.matching.Regex

object Day3b extends Challenge {

  val pattern: Regex = "#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)".r

  case class Claim(id: Int, x: Int, y: Int, w: Int, h: Int) {
    def toSet: Set[(Int, Int)] = {
      (for (i <- x until x + w;
            j <- y until y + h) yield (i, j)
        ).toSet
    }
  }

  case class FabricPatch(x: Int, y: Int)

  def parse(s: String): Claim = {
    val pattern(id, x, y, w, h) = s
    Claim(id.toInt, x.toInt, y.toInt, w.toInt, h.toInt)
  }

  override def run(): Any = {
    val input = Source.fromResource("day3.txt").getLines().toList
    val claims = input.map(parse)
    val patches: List[FabricPatch] = claims.flatMap(c => {
      for (i <- c.x until c.x + c.w;
           j <- c.y until c.y + c.h) yield FabricPatch(i, j)
    })
    val nonConflicted: Set[(Int, Int)] =
      patches.groupBy(p => (p.x, p.y)).filter(_._2.length == 1).keySet

    claims.find(_.toSet.subsetOf(nonConflicted)).get.id
  }

}
