import challenge.Day9b
import org.scalatest._

class Test9b extends FlatSpec with Matchers {

    it should "diplay correct result" in {
        Day9b.run() should be (3628143500L)
    }
}
