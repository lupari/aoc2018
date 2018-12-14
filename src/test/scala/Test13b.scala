import challenge.Day13b
import org.scalatest._

class Test13b extends FlatSpec with Matchers {

    it should "diplay correct result" in {
        Day13b.run() should be ((0, 98))
    }
}
