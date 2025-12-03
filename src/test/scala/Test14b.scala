import challenge.Day14b
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Test14b extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
        Day14b.run() should be (20165504)
    }
}
