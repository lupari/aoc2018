import challenge.Day5b
import org.scalatest._

class Test5b extends FlatSpec with Matchers {

    it should "diplay correct result" in {
        Day5b.run() should be (4996)
    }
}
