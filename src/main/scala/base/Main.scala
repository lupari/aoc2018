package base

import challenge._

import scala.collection.immutable.ListMap

object Main extends App {

  def challenges: Map[String, (Challenge, Any)] = ListMap(
    "1" -> ((Day1, 472)),
    "1b" -> ((Day1b, 66932)),
    "2" -> ((Day2, 7872)),
    "2b" -> ((Day2b, "tjxmoewpdkyaihvrndfluwbzc")),
    "3" -> ((Day3, 113716)),
    "3b" -> ((Day3b, 742))
  )

  def check(key: String): Unit = {
    val entry = challenges(key)
    val t0 = System.currentTimeMillis()
    val (result, expected) = (entry._1.run(), entry._2)
    val t1 = System.currentTimeMillis()
    println("Result for " + key + ":\t" + result + " (executed in " + (t1 - t0) + " ms)")
    assert(result == expected, "Bad test result for key " + key + ", expected " + expected + " but got " + result)
  }

  args.toList match {
    case h :: Nil => h match {
      case "all" => challenges.keys.foreach(check)
      case k if challenges.contains(k) => check(k)
      case _ => println("not found")
    }
    case _ => println("wrong number of args")
  }

}
