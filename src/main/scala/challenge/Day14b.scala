package challenge

import base.Challenge

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer

object Day14b extends Challenge {

  def improveRecipes(n: String): Int = {

    // had to resort to using mutable buf, immutables had terrible performance
    val acc: ArrayBuffer[Int] = ArrayBuffer(3, 7)
    val targetLength = n.length
    val targetDigits = n.map(_.asDigit).toArray

    @tailrec
    def accumulator(current: (Int, Int)): Int = {
      val (recipeA, recipeB) = (acc(current._1), acc(current._2))
      val score: Seq[Int] = (recipeA + recipeB).toString.map(_.asDigit)
      val startLen = acc.length
      acc ++= score
      
      // Only check the last few positions where the pattern could appear
      // Check from (startLen - targetLength) to acc.length, but not before 0
      val checkStart = math.max(0, startLen - targetLength)
      val checkEnd = acc.length
      
      // Check if pattern appears in the newly added region using sliding window
      val foundIndex = (checkStart until (checkEnd - targetLength + 1))
        .find { i =>
          (0 until targetLength).forall(j => acc(i + j) == targetDigits(j))
        }
      
      foundIndex match {
        case Some(idx) => idx
        case None => accumulator((current._1 + recipeA + 1) % acc.length, (current._2 + recipeB + 1) % acc.length)
      }
    }

    accumulator((0, 1))

  }

  override def run(): Any = {
    improveRecipes("440231")
  }

}
