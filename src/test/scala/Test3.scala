import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import challenge.Day3

class Test3 extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
        Day3.run() should be (113716)
    }
}