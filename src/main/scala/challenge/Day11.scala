package challenge

import base.Challenge

object Day11 extends Challenge {

  case class Point(x: Int, y: Int) {
    override def toString: String = x + ", " + y
  }

  def powerLevel(p: Point, sn: Int): Int = {
    val rackId = p.x + 10
    val level = (rackId * p.y + sn) * rackId
    val hd = if (level < 100) 0 else level.toString.reverse(2).asDigit
    hd - 5
  }

  def squareSum(s: List[Seq[Point]], sn: Int): Int =
    s.flatten.map(powerLevel(_, sn)).sum

  override def run(): Any = {
    val (sn, n, k) = (6878, 300, 3)
    val squares = for { i <- 1 to n - (k - 1); j <- 1 to n - (k - 1) } yield
      List((i until i + k).map(Point(_, j)),
           (i until i + k).map(Point(_, j + 1)),
           (i until i + k).map(Point(_, j + 2))
      )
    squares.maxBy(squareSum(_, sn)).head.head.toString
  }

}
