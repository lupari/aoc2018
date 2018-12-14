import challenge.Day14b
import org.scalatest._

class Test14b extends FlatSpec with Matchers {

    it should "diplay correct result" in {
        Day14b.run() should be (20165504)
    }
}
