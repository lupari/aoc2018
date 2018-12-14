package challenge

import base.Challenge

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer

object Day14b extends Challenge {

  def improveRecipes(n: String): Int = {

    // had to resort to using mutable buf, immutables had terrible performance
    val acc: ArrayBuffer[Int] = ArrayBuffer(3, 7)

    @tailrec
    def accumulator(current: (Int, Int)): Int = acc match {
      case a if a.takeRight(10).mkString.contains(n) =>
        acc.mkString.indexOf(n)
      case _ =>
        val (recipeA, recipeB) = (acc(current._1), acc(current._2))
        val score: Seq[Int] = (recipeA + recipeB).toString.map(_.asDigit)
        acc ++= score
        accumulator((current._1 + recipeA + 1) % acc.length, (current._2 + recipeB + 1) % acc.length)
    }

    accumulator((0, 1))

  }

  override def run(): Any = {
    improveRecipes("440231")
  }

}
