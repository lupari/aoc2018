import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import challenge.Day4

class Test4 extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
        Day4.run() should be (19830)
    }
}
