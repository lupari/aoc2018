import challenge.Day9
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Test9 extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
        Day9.run() should be (428690)
    }
}
