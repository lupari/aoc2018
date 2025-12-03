import challenge.Day10b
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Test10b extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
        Day10b.run() should be (10515)
    }
}
