package challenge

import base.Challenge

import scala.annotation.tailrec

object Day14 extends Challenge {

  def improveRecipes(initial: List[Int], n: Int): Int = {

    @tailrec
    def accumulator(acc: Vector[Int], current: (Int, Int)): Int = acc match {
      case a if a.length >= n + 10 =>
        acc.take(n + 10).takeRight(10).mkString.toInt
      case _ =>
        val (recipeA, recipeB) = (acc(current._1), acc(current._2))
        val score: Seq[Int] = (recipeA + recipeB).toString.map(_.asDigit)
        val nextA: Vector[Int] = acc ++ score
        val nextC = ((current._1 + recipeA + 1) % nextA.length, (current._2 + recipeB + 1) % nextA.length)
        accumulator(nextA, nextC)
    }

    accumulator(initial.toVector, (0, 1))

  }

  override def run(): Any = {
    improveRecipes(List(3, 7), 440231)
  }

}
