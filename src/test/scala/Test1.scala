import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import challenge.Day1

class Test1 extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
        Day1.run() should be (472)
    }
}