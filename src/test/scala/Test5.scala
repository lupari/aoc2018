import challenge.Day5
import org.scalatest._

class Test5 extends FlatSpec with Matchers {

    it should "diplay correct result" in {
        Day5.run() should be (9348)
    }
}
