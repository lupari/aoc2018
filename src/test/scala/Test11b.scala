import challenge.Day11b
import org.scalatest._

class Test11b extends FlatSpec with Matchers {

    it should "diplay correct result" in {
        Day11b.run() should be ((90, 57, 15))
    }
}
