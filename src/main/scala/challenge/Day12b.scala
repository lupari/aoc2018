package challenge

import base.Challenge

import scala.collection.mutable
import scala.io.Source

object Day12b extends Challenge {

  def parseState(line: String): String = line.split(": ").last
  def parseRules(lines: List[String]): Map[String, Char] = {
    lines.map(_.split(" => " ).toList)
      .map(s => (s.head, s.last.last))
      .toMap.withDefaultValue('.')
  }

  def evolve(rules: Map[String, Char])(state: String, i: Int): (String, Int) = {
    val next = ("...." + state + "....").sliding(5).map(rules).mkString
    val firstPlant = next.indexOf('#')
    val lastPlant = next.lastIndexOf('#')
    (next.slice(firstPlant, lastPlant + 1), i + 2 - firstPlant)
  }

  def getCycle(state: String, rules: Map[String, Char]): (Int, String, Int, Int) = {
    // memoization
    val memo: mutable.Map[String, (Int, Int)] = mutable.Map()
    val it = Iterator.iterate((state, 0))(i => evolve(rules)(i._1, i._2))
    val cycle: (((String, Int), Int), Option[(Int, Int)]) =
      it.zipWithIndex.map(x => (x, memo.put(x._1._1, (x._1._2, x._2)))).dropWhile(x => x._2.isEmpty).next()
    val (generation, startIndex) = cycle._1._1
    val (prevStartIndex, prevGenIndex) = cycle._2.get
    (startIndex, generation, prevStartIndex, prevGenIndex)
  }

  def lastGenSum(state: String, rules: Map[String, Char], n: Long): Long = {
    val cycle = getCycle(state, rules)
    // Calculate the shifts that took place during the cycle
    val cycleCount = n - cycle._4
    val indexShift = cycle._1 - cycle._3
    val latestStartingPlant = cycle._3 + cycleCount * indexShift
    cycle._2.zipWithIndex.map({
      case ('.', _) => 0
      case ('#', ix) => ix - latestStartingPlant
    }).sum
  }

  override def run(): Any = {
    val input = Source.fromResource("day12.txt").getLines().toList
    val state = parseState(input.head)
    val rules = parseRules(input.drop(2))
    lastGenSum(state, rules, 50000000000L)
  }

}
