import challenge.Day13
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Test13 extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
        Day13.run() should be ((115, 138))
    }
}
