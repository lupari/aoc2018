import challenge.Day12b
import org.scalatest._

class Test12b extends FlatSpec with Matchers {

    it should "diplay correct result" in {
        Day12b.run() should be (1700000000011L)
    }
}
