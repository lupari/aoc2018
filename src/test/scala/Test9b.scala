import challenge.Day9b
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Test9b extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
        Day9b.run() should be (3628143500L)
    }
}
