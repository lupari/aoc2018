import challenge.Day13
import org.scalatest._

class Test13 extends FlatSpec with Matchers {

    it should "diplay correct result" in {
        Day13.run() should be ((115, 138))
    }
}
