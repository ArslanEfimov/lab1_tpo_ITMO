package task1Tests

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import task1.Arccos
import java.lang.IllegalArgumentException
import java.util.stream.Stream
import kotlin.math.acos
import kotlin.test.assertEquals

class ArccosTests {

    companion object{
        @JvmStatic
        fun dotsProvider(): Stream<Pair<Double, Double>>{
            return listOf(-1.0, -0.5, 0.0, 0.5, 1.0).map { it to acos(it) }.stream()
        }
    }

    //протестировать лишь -1, -0.5, 0, 0.5, 1 и значения, которые за пределами аркосинуса
    @DisplayName("Check dots between [-1,1]")
    @ParameterizedTest(name = "(x,y) = {0}")
    @MethodSource("dotsProvider")
    fun checkDotsBetweenMinusOneToOneTest(pair: Pair<Double, Double>){
        val (x, y) = pair
        val arccos = Arccos(x, 100)
        assertEquals(y, arccos.arrcos_teylor(), 1e-10)

    }
    @DisplayName("arccos(x) should throw an exception")
    @ParameterizedTest(name = "x = {0}")
    @ValueSource(doubles = [1.1, -1.1, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NaN])
    fun checkForImpossibleDotsTest(x: Double){
        assertThrows<IllegalArgumentException> {
            val arccos = Arccos(x, 100)
            arccos.arrcos_teylor()
        }
    }

}