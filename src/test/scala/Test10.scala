import challenge.Day10
import org.scalatest._

class Test10 extends FlatSpec with Matchers {

    it should "diplay correct result" in {
        Day10.run() 
        println("should print GPJLLLLH")
    }
}
