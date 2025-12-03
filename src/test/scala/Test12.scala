import challenge.Day12
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Test12 extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
        Day12.run() should be (2040)
    }
}
