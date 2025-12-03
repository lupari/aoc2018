import challenge.Day7b
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Test7b extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
        Day7b.run() should be (917)
    }
}
