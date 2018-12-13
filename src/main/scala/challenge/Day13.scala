package challenge

import base.Challenge

import scala.annotation.tailrec
import scala.io.Source

object Day13 extends Challenge {

  type Grid = Array[Array[Char]]
  private val lines: List[String] = Source.fromResource("day13.txt").getLines.toList
  private val width = lines.maxBy(_.length).length
  private val input = lines.map(l => l + " " * (width- l.length))
  private val grid: Grid = Array.ofDim[Char](input.length, width)

  case class Cart(x: Int, y: Int, track: Char, dir: Char, id: Int, lastTurn: Char = 'R', collided: Boolean = false) {
    def nextPos(direction: Char): (Int, Int) = direction match {
      case 'U' => (x, y - 1)
      case 'D' => (x, y + 1)
      case 'R' => (x + 1, y)
      case 'L' => (x - 1, y)
      case 'S' => nextPos(dir)
    }

    def getTurn(x: Int, y: Int, dir: Char): Option[Char] = track match {
      case '/' if dir == 'U' => Some('R')
      case '/' if dir == 'R' => Some('U')
      case '/' if dir == 'L' => Some('D')
      case '/' if dir == 'D' => Some('L')
      case '\\' if dir == 'U' => Some('L')
      case '\\' if dir == 'L' => Some('U')
      case '\\' if dir == 'R' => Some('D')
      case '\\' if dir == 'D' => Some('R')
      case _ => None
    }

    def getNextDirAtJunction(lastTurn: Char): Char = track match {
      case '+' => lastTurn match {
        case 'R' => 'L'
        case 'S' => 'R'
        case 'L' => 'S'
      }
    }

    def getJunction(x: Int, y: Int, dir: Char, lastTurn: Char): Option[(Char, Char)] = track match {
      case '+' =>
        val next = getNextDirAtJunction(lastTurn)
        next match {
          case 'S' => Some((next, dir))
          case 'R' if dir == 'U' => Some((next, 'R'))
          case 'R' if dir == 'D' => Some((next, 'L'))
          case 'R' if dir == 'L' => Some((next, 'U'))
          case 'R' if dir == 'R' => Some((next, 'D'))
          case 'L' if dir == 'U' => Some((next, 'L'))
          case 'L' if dir == 'D' => Some((next, 'R'))
          case 'L' if dir == 'L' => Some((next, 'D'))
          case 'L' if dir == 'R' => Some((next, 'U'))
        }
      case _ => None
    }

    def move(others: Set[Cart]): Cart = {
      val junctionDir: Option[(Char, Char)] = getJunction(x, y, dir, lastTurn)
      val direction = getTurn(x, y, dir).orElse(junctionDir.map(_._2)).orElse(Some(dir)).get
      val next = nextPos(direction)
      val collided = others.exists(o => o.x == next._1 && o.y == next._2)
      grid(y)(x) = track
      Cart(next._1, next._2, grid(next._2)(next._1), direction, id, junctionDir.map(_._1).getOrElse(lastTurn), collided)
    }

  }

  def initialCarts(): List[Cart] = {
    (for {
        j <- grid.indices
        i <- grid.head.indices
        if "><v^".indexOf(grid(j)(i)) > - 1
      } yield {
        grid(j)(i) match {
          case c if "^v".indexOf(c) > - 1 =>
            Cart(i, j, '|', if (c == 'v') 'D' else 'U', i*10 + j)
          case c if "><".indexOf(c) > - 1 =>
            Cart(i, j, '-', if (c == '<') 'L' else 'R', i*10 + j)
        }
    }).toList
  }

  def moveCarts(carts: List[Cart]) = {
    @tailrec
    def helper(xs: List[Cart], others: Set[Cart], acc: List[Cart]): List[Cart] = xs match {
        case h :: t =>
            val moved = h.move(others - h)
            helper(t, others - h + moved, acc :+ moved)
        case _ => acc    
    }
    val sorted = carts.sortBy(c => (c.x, c.y))
    helper(sorted, Set() ++ sorted, Nil)
  }

  def colliding(carts: List[Cart]): Boolean = carts.exists(_.collided == true)

  override def run(): Any = {
    for {i <- 0 until width; j <- input.indices} yield {
      grid(j)(i) = input(j)(i)
    }

    val collision: List[Cart] =
      Iterator.iterate(initialCarts())(carts => moveCarts(carts)).dropWhile(carts => !colliding(carts)).next

    val collided = collision.filter(_.collided).minBy(c => (c.x, c.y))
    (collided.x, collided.y)
  }

}
