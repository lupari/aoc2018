package challenge

import base.Challenge

import scala.io.Source
import scala.util.matching.Regex

object Day10b extends Challenge {

  case class Location(x: Int, y: Int)

  case class Point(l: Location, vx: Int, vy: Int) {
    def locationAt(t: Int): Location = Location(l.x + vx * t, l.y + vy * t)
  }

  val pattern: Regex = ".*<\\s*(-?\\d+),\\s*(-?\\d+).*<\\s*(-?\\d+),\\s*(-?\\d+)>".r
  def parse(line: String): Point = {
    val pattern(x, y, vx, vy) = line
    Point(Location(x.toInt, y.toInt), vx.toInt, vy.toInt)
  }

  def boundingBox(locations: List[Location]) = {
    (locations.minBy(_.x).x, locations.maxBy(_.x).x, locations.minBy(_.y).y, locations.maxBy(_.y).y)
  }

  def bbSize(bb: (Int, Int, Int, Int)) = bb._2 - bb._1 + bb._4 - bb._3 

  override def run(): Any = {
    val points = Source.fromResource("day10.txt").getLines.map(parse).toList
    val boxes = (0 to 20000).map(t => (t, boundingBox(points.map(_.locationAt(t)))))
    boxes.minBy(b => bbSize(b._2))._1
  }

}