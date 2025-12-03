package challenge

import base.Challenge

import scala.io.Source
import scala.util.matching.Regex

object Day10 extends Challenge {

  case class Location(x: Int, y: Int)

  case class Point(l: Location, vx: Int, vy: Int) {
    def locationAt(t: Int): Location = Location(l.x + vx * t, l.y + vy * t)
  }

  val pattern: Regex = ".*<\\s*(-?\\d+),\\s*(-?\\d+).*<\\s*(-?\\d+),\\s*(-?\\d+)>".r
  def parse(line: String): Point = {
    val pattern(x, y, vx, vy) = line
    Point(Location(x.toInt, y.toInt), vx.toInt, vy.toInt)
  }

  def boundingBox(locations: Set[Location]): (Int, Int, Int, Int) = {
    (locations.minBy(_.x).x, locations.maxBy(_.x).x, locations.minBy(_.y).y, locations.maxBy(_.y).y)
  }

  def display(locations: Set[Location]): Any = {
    val (minX, maxX, minY, maxY) = boundingBox(locations)
    for (y <- minY to maxY) {
      for (x <- minX to maxX) {
        val found = locations.contains(Location(x, y))
        print(if (found) '#' else '.')
      }
      println()
    }
  }

  override def run(): Any = {
    val input = Source.fromResource("day10.txt").getLines().toList
    val points = input.map(parse).toSet
    val locations: Seq[Set[Location]] = (0 to 20000).map(t => points.map(_.locationAt(t)))
    val smallestHeight = locations.minBy(l => {
      val bb = boundingBox(l)
      bb._4 - bb._3
    })
    display(smallestHeight)
  }

}
