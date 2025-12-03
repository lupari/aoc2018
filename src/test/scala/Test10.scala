import challenge.Day10
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Test10 extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
        Day10.run() 
        println("should print GPJLLLLH")
    }
}
