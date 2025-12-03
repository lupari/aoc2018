package challenge

import base.Challenge

import scala.annotation.tailrec
import scala.io.Source

object Day13 extends Challenge {

  type Grid = Array[Array[Char]]

  case class Cart(x: Int, y: Int, track: Char, dir: Char, lastTurn: Char = 'R', collided: Boolean = false) {
    def nextPos(direction: Char): (Int, Int) = direction match {
      case 'U' => (x, y - 1)
      case 'D' => (x, y + 1)
      case 'R' => (x + 1, y)
      case 'L' => (x - 1, y)
    }

    def turn(): Char = track match {
      case '/' if dir == 'U' => 'R'
      case '/' if dir == 'R' => 'U'
      case '/' if dir == 'L' => 'D'
      case '/' if dir == 'D' => 'L'
      case '\\' if dir == 'U' => 'L'
      case '\\' if dir == 'L' => 'U'
      case '\\' if dir == 'R' => 'D'
      case '\\' if dir == 'D' => 'R'
    }

    def nextDirAtJunction(): Char = lastTurn match {
      case 'R' => 'L'
      case 'S' => 'R'
      case 'L' => 'S'
    }

    def traverseJunction(): (Char, Char) = {
      val next = nextDirAtJunction()
      val nextDir = next match {
        case 'S' => dir
        case 'R' if dir == 'U' => 'R'
        case 'R' if dir == 'D' => 'L'
        case 'R' if dir == 'L' => 'U'
        case 'R' if dir == 'R' => 'D'
        case 'L' if dir == 'U' => 'L'
        case 'L' if dir == 'D' => 'R'
        case 'L' if dir == 'L' => 'D'
        case 'L' if dir == 'R' => 'U'
      }
      (nextDir, next)
    }

    def move(others: Set[Cart])(implicit grid: Grid): Cart = {
      val (nextDir, junctionTurn): (Char, Char) = track match {
        case '+' =>
          traverseJunction()
        case c if c == '\\' | c == '/' =>
          (turn(), lastTurn)
        case _ =>
          (dir, lastTurn)
      }
      val next = nextPos(nextDir)
      val collided = others.exists(o => o.x == next._1 && o.y == next._2)
      Cart(next._1, next._2, grid(next._2)(next._1), nextDir, junctionTurn, collided)
    }

  }

  def initialCarts(implicit grid: Grid): List[Cart] = {
    (
      for {
        j <- grid.indices
        i <- grid.head.indices
        if "><v^".indexOf(grid(j)(i)) > - 1
      } yield {
        grid(j)(i) match {
          case c if c == '^' | c == 'v' =>
            Cart(i, j, '|', if (c == 'v') 'D' else 'U')
          case c if c == '<' | c == '>' =>
            Cart(i, j, '-', if (c == '<') 'L' else 'R')
        }
      }
    ).toList
  }

  def moveCarts(carts: List[Cart])(implicit grid: Grid): List[Cart] = {
    @tailrec
    def helper(xs: List[Cart], others: Set[Cart], acc: List[Cart]): List[Cart] = xs match {
      case h :: t =>
        grid(h.y)(h.x) = h.track
        val moved = h.move(others - h)(grid)
        helper(t, others - h + moved, acc :+ moved)
      case _ => acc
    }
    val sorted = carts.sortBy(c => (c.x, c.y))
    helper(sorted, Set() ++ sorted, Nil)
  }

  def colliding(carts: List[Cart]): Boolean = carts.exists(_.collided == true)

  override def run(): Any = {
    val lines: List[String] = Source.fromResource("day13.txt").getLines().toList
    val width = lines.maxBy(_.length).length
    val input = lines.map(l => l + " " * (width - l.length))
    implicit val grid: Grid = Array.ofDim[Char](input.length, width)

    for {i <- 0 until width; j <- input.indices} yield {
      grid(j)(i) = input(j)(i)
    }

    val collision: List[Cart] =
      Iterator.iterate(initialCarts)(carts => moveCarts(carts)).dropWhile(carts => !colliding(carts)).next()

    val collided = collision.filter(_.collided).minBy(c => (c.x, c.y))
    (collided.x, collided.y)
  }

}
