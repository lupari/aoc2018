import challenge.Day8
import org.scalatest._

class Test8 extends FlatSpec with Matchers {

    it should "diplay correct result" in {
       Day8.run() should be (47112)
    }
}
