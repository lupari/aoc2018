import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import challenge.Day7

class Test7 extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
        Day7.run() should be ("FHMEQGIRSXNWZBCLOTUADJPKVY")
    }
}
