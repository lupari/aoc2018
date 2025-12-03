import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import challenge.Day1b

class Test1b extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
        Day1b.run() should be (66932)
    }
}