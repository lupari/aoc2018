import challenge.Day8b
import org.scalatest._

class Test8b extends FlatSpec with Matchers {

    it should "diplay correct result" in {
       Day8b.run() should be (28237)
    }
}
