import challenge.Day11
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Test11 extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
        Day11.run() should be ("20, 34")
    }
}
