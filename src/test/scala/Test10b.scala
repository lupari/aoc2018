import challenge.Day10b
import org.scalatest._

class Test10b extends FlatSpec with Matchers {

    it should "diplay correct result" in {
        Day10b.run() should be (10515)
    }
}
