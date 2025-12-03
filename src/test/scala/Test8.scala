import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import challenge.Day8

class Test8 extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
       Day8.run() should be (47112)
    }
}
