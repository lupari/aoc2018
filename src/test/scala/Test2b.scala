import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import challenge.Day2b

class Test2b extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
        Day2b.run() should be ("tjxmoewpdkyaihvrndfluwbzc")
    }
}