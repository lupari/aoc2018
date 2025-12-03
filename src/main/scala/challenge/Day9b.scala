package challenge

import base.Challenge
import scala.annotation.tailrec

object Day9b extends Challenge {

  class Board extends java.util.ArrayDeque[Int] {
    def rotate(amount: Int): Any = amount match {
      case a if a >= 0 =>
        (0 until amount).foreach(_ => addFirst(removeLast()))
      case _ =>
        (0 until -amount -1).foreach(_ => addLast(removeFirst()))
    }
  }

  def play(players: Int, turns: Int): Long = {
    val board = new Board()
    board.addFirst(0)
    val scores: Array[Long] = Array.fill(players){0}

    @tailrec
    def acc(marble: Int): Long = marble match {
      case m if m % 23 == 0 =>
        board.rotate(-7)
        scores(m % players) += board.pop() + m
        acc(m + 1)
      case m if m < turns =>
        board.rotate(2)
        board.addLast(marble)
        acc(marble + 1)
      case _ => scores.max
    }
    acc(1)
  }

  override def run(): Any = {
    val (n, max) = (405, 71700*100)
    play(n, max)
  }


}
