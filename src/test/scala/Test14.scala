import challenge.Day14
import org.scalatest._

class Test14 extends FlatSpec with Matchers {

    it should "diplay correct result" in {
        Day14.run() should be (1052903161)
    }
}
