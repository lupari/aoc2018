package challenge

import base.Challenge

import scala.annotation.tailrec
import scala.collection.mutable
import scala.io.Source

object Day1b extends Challenge {

  def getFirstDuplicate(xs: List[Int]): Int = {

    val seed = xs.scanLeft(0)(_ + _).dropRight(1)
    val inc = xs.sum

    @tailrec
    def accumulator(prev: List[Int], acc: mutable.LinkedHashSet[Int]): Int = {
      val frequencies: List[Int] = prev.map(_ + inc)
      frequencies.find(acc.contains) match {
        case Some(f) => f
        case _ =>
          val next: mutable.LinkedHashSet[Int] = acc + frequencies.last ++ frequencies.dropRight(1)
          accumulator(frequencies, next)
      }
    }
    accumulator(seed, mutable.LinkedHashSet() ++ seed)

  }

  override def run(): Any = {
    val input: List[Int] = Source.fromResource("day1.txt").getLines().map(_.toInt).toList
    getFirstDuplicate(input)
  }

}
