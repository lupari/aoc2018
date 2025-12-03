import challenge.Day8b
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Test8b extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
       Day8b.run() should be (28237)
    }
}
