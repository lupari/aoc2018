import challenge.Day4b
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Test4b extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
        Day4b.run() should be (43695)
    }
}
