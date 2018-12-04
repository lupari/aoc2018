import challenge.Day4b
import org.scalatest._

class Test4b extends FlatSpec with Matchers {

    it should "diplay correct result" in {
        Day4b.run() should be (43695)
    }
}
