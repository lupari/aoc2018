import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import challenge.Day3b

class Test3b extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
        Day3b.run() should be (742)
    }
}