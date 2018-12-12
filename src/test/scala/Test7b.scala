import challenge.Day7b
import org.scalatest._

class Test7b extends FlatSpec with Matchers {

    it should "diplay correct result" in {
        Day7b.run() should be (917)
    }
}
