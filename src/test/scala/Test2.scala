import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import challenge.Day2

class Test2 extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
        Day2.run() should be (7872)
    }
}