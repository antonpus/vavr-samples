package function;

import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function3;
import io.vavr.control.Option;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FunctionApiTest {

    @Test
    public void functionCreationApi() {
        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;

        assertTrue(sum.apply(2, 3) == 5);
    }

    @Test
    public void compositionApi() {

        Function1<Integer, Integer> plusOne = a -> a + 1;
        Function1<Integer, Integer> multiplyByTwo = a -> a * 2;
        Function1<Integer, Integer> multiplyByThree = a -> a * 3;

        //andThen
        Function1<Integer, Integer> add1AndMultiplyBy2 = plusOne.andThen(multiplyByTwo);

        assertTrue(add1AndMultiplyBy2.apply(3) == 8);

        //Or compose
        Function1<Integer, Integer> add1AndMultiplyBy3 = multiplyByThree.compose(plusOne);

        assertTrue(add1AndMultiplyBy3.apply(3) == 12);
    }

    @Test
    public void liftingApi() {
        Function2<Integer, Integer, Integer> division = (a, b) -> a / b;
        Function2<Integer, Integer, Option<Integer>> lifted = Function2.lift(division);

        assertTrue(lifted.apply(4, 0).equals(Option.none()));
    }

    @Test
    public void partialApplicationApi() {
        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        Function1<Integer, Integer> add2 = sum.apply(2);

        assertTrue(add2.apply(3) == 5);
    }

    @Test
    public void curryingApi() {
        Function3<Integer, Integer, Integer, Integer> sum = (a, b, c) -> a + b + c;
        Function1<Integer, Function1<Integer, Integer>> add2 = sum.curried().apply(2);

        assertTrue(add2.apply(1).apply(3) == 6);
    }

    @Test
    public void memoizationApi() {
        Function0<Double> hashCache = Function0.of(Math::random).memoized();

        double value1 = hashCache.apply();
        double value2 = hashCache.apply();

        assertTrue(value1 == value2);
    }
}
