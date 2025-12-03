package challenge

import base.Challenge

object Day11b extends Challenge {

  def powerLevel(x: Int, y: Int, sn: Int): Int = {
    val rackId = x + 10
    val level = (rackId * y + sn) * rackId
    (level / 100) % 10 - 5
  }

  def summedAreaTable(grid: Array[Array[Int]], n: Int): Array[Array[Int]] = {
    val sat = Array.ofDim[Int](n, n)
    for { i <- 0 until n } yield sat(0)(i) = grid(0)(i)
    for { i <- 1 until n; j <- 0 until n } yield sat(i)(j) = grid(i)(j) + sat(i - 1)(j)
    for { i <- 0 until n; j <- 1 until n } yield sat(i)(j) += sat(i)(j - 1)
    sat
  }

  def sumQuery(sat: Array[Array[Int]], tli: Int, tlj: Int, size: Int): Int = {
    val (br, tr, bl, tl) = (sat(tli + size)(tlj + size), sat(tli + size)(tlj), sat(tli)(tlj + size), sat(tli)(tlj))
    br - tr - bl + tl
  }

  override def run(): Any = {
    val (sn, n) = (6878, 300)
    val grid = Array.ofDim[Int](n, n)
    for {i <- 1 to n; j <- 1 to n} yield grid(i - 1)(j - 1) = powerLevel(i, j, sn)
    val sat = summedAreaTable(grid, n)
    val partialSums = for {s <- 1 until n; i <- 0 until n - s; j <- 0 until n - s} yield
      ((i + 2, j + 2, s), sumQuery(sat, i, j, s))
    // + 2 is because original grid starts from (1,1) instead of (0,0). Moreover the sat gives us the bounds off by one
    partialSums.maxBy(_._2)._1
  }

}
