import challenge.Day14
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Test14 extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
        Day14.run() should be (1052903161)
    }
}
