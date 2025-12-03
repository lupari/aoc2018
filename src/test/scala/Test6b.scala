import challenge.Day6b
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Test6b extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
        Day6b.run() should be (39560)
    }
}
