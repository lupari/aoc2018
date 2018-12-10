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

  def boundingBox(locations: Set[Location]) = {
    (locations.minBy(_.x).x, locations.maxBy(_.x).x, locations.minBy(_.y).y, locations.maxBy(_.y).y)
  }

  def bbSize(bb: (Int, Int, Int, Int)) = bb._2 - bb._1 + bb._4 - bb._3 

  def display(t: Int, points: Set[Point]) = {
    val projection: Set[Location] = points.map(_.locationAt(t)).toSet
    val (minX, maxX, minY, maxY) = boundingBox(projection)
    for (y <- minY to maxY) {
      for (x <- minX to maxX) {
        val found = projection.contains(Location(x, y))
        print(if (found) '#' else '.')
      }
      println()
    }
  }

  override def run(): Any = {
    val input = Source.fromResource("day10.txt").getLines.toList
    val points = input.map(parse).toSet
    val boxes = (0 to 20000).map(t => (t, boundingBox(points.map(_.locationAt(t)))))
    val iteration = boxes.minBy(b => bbSize(b._2))
    display(iteration._1, points)
  }

}