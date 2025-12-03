import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import challenge.Day5

class Test5 extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
        Day5.run() should be (9348)
    }
}
