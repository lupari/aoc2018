import challenge.Day6b
import org.scalatest._

class Test6b extends FlatSpec with Matchers {

    it should "diplay correct result" in {
        Day6b.run() should be (39560)
    }
}
