import challenge.Day13b
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Test13b extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
        Day13b.run() should be ((0, 98))
    }
}
