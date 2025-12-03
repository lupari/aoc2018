import challenge.Day11b
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Test11b extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
        Day11b.run() should be ((90, 57, 15))
    }
}
