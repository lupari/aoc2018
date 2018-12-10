import challenge.Day9
import org.scalatest._

class Test9 extends FlatSpec with Matchers {

    it should "diplay correct result" in {
        Day9.run() should be (428690)
    }
}
