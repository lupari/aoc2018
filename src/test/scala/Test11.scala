import challenge.Day11
import org.scalatest._

class Test11 extends FlatSpec with Matchers {

    it should "diplay correct result" in {
        Day11.run() should be ("20, 34")
    }
}
