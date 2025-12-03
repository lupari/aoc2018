package challenge

import base.Challenge

import scala.io.Source

object Day12 extends Challenge {

  def parseState(line: String): String = line.split(": ").last
  def parseRules(lines: List[String]): Map[String, Char] = {
    lines.map(_.split(" => " ).toList)
      .map(s => (s.head, s.last.last))
      .toMap.withDefaultValue('.')
  }

  def evolve(rules: Map[String, Char])(state: String, i: Int): (String, Int) = {
    val next = ("." * 4 + state + "." * 4).sliding(5).map(rules).mkString
    val firstPlant = next.indexOf('#')
    val lastPlant = next.lastIndexOf('#')
    (next.slice(firstPlant, lastPlant + 1), i + 2 - firstPlant)
  }

  override def run(): Any = {
    val input = Source.fromResource("day12.txt").getLines().toList
    val state = parseState(input.head)
    val rules = parseRules(input.drop(2))

    val lastGen: (String, Int) = (1 to 20).foldLeft((state, 0))((a, _) => {
      evolve(rules)(a._1, a._2)
    })

    lastGen._1.zipWithIndex.map({
      case ('.', _) => 0
      case ('#', i) => i - lastGen._2
    }).sum

  }

}
