package challenge

import base.Challenge

import scala.io.Source

object Day8 extends Challenge {

  case class Tree(children: Seq[Tree], metadata: Seq[Int])

  def parseTrees(licenceFile: Seq[Int], cc: Int): (Seq[Tree], Seq[Int]) = cc match {
    case 0 => (Seq.empty, licenceFile)
    case _ =>
      val (tree, remainder) = parseSubTree(licenceFile)
      val (trees, remainder2) = parseTrees(remainder, cc - 1)
      (tree +: trees, remainder2)
  }

  def parseSubTree(licenceFile: Seq[Int]): (Tree, Seq[Int]) = {
    licenceFile match {
      case cc :: mdc :: t =>
        val (trees, rem) = parseTrees(t, cc)
        val (md, t2) = rem.splitAt(mdc)
        (Tree(trees, md), t2)
    }
  }

  def metaSum(tree: Tree): Int = tree.children.map(metaSum).sum + tree.metadata.sum

  override def run(): Any = {
    val input = Source.fromResource("day8.txt").mkString.trim.split(' ').map(_.toInt).toList
    val tree = parseTrees(input, 1)._1.head
    metaSum(tree)
  }


}
