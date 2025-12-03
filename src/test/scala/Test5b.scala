import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import challenge.Day5b

class Test5b extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
        Day5b.run() should be (4996)
    }
}
