package challenge

import base.Challenge

import scala.io.Source

object Day6 extends Challenge {

  case class Point(x: Int, y: Int)

  def distance(p1: Point, p2: Point): Int =  (p1.x - p2.x).abs + (p1.y - p2.y).abs

  def closestNeighbors(point: Point, neighbors: List[Point]): List[Point] =
    neighbors.groupBy(distance(point, _)).minBy(_._1)._2

  def isOnEdge(point: Point, edges: (Int, Int, Int, Int)): Boolean =
    point.x == edges._1 || point.x == edges._2 || point.y == edges._3 || point.y == edges._4

  override def run(): Any = {
    val input = Source.fromResource("day6.txt").getLines().toList
    val coords: List[Point] = input.map(_.split(", ")).map(c => Point(c.head.toInt, c.last.toInt))
    val limits = (coords.map(_.x).min, coords.map(_.y).min)
    val fixedCoords = coords.map(c => Point(c.x - limits._1, c.y - limits._2))
    val edges: (Int, Int, Int, Int) =
      (fixedCoords.map(_.x).min, fixedCoords.map(_.x).max, fixedCoords.map(_.y).min, fixedCoords.map(_.y).max)
    val grid: List[Point] =
      (for {x <- edges._1 to edges._2; y <- edges._3 to edges._4} yield(x, y)).toList.map(x => Point(x._1, x._2))
    val withClosestLocations: List[(Point, List[Point])] =
      grid.map(c => (c, closestNeighbors(c, fixedCoords)))
    val withClosestLocation: List[(Point, Point)] =
      withClosestLocations.filter(_._2.length == 1).map(l => (l._1, l._2.head))
    val byLocation: Map[Point, List[Point]] = withClosestLocation.groupBy(_._2).map(l => (l._1, l._2.map(_._1)))
    val finiteAreas = byLocation.filterNot(_._2.exists(isOnEdge(_, edges)))
    finiteAreas.map(_._2.length).max
  }

}
