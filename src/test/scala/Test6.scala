import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import challenge.Day6

class Test6 extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
        Day6.run() should be (4398)
    }
}
