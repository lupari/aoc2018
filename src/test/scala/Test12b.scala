import challenge.Day12b
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Test12b extends AnyFlatSpec with Matchers {

    it should "diplay correct result" in {
        Day12b.run() should be (1700000000011L)
    }
}
