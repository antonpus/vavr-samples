package values;

import io.vavr.Lazy;
import io.vavr.concurrent.Future;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValuesApiTest {

    @Test(expected = NoSuchElementException.class)
    public void optionApi() {

        Option<String> some = Option.of("some");

        assertFalse(some.isEmpty());

        assertTrue(some instanceof Option.Some);
        assertFalse(some instanceof Option.None);

        Option.none().get();
    }

    @Test
    public void tryApi() {

        String[] array = {};
        String result = Try.ofCallable(() -> array[1]).getOrElse("default");

        assertTrue(result == "default");
    }

    @Test
    public void lazyApi() {
        Lazy<Double> lazy = Lazy.of(Math::random);
        assertFalse(lazy.isEvaluated());

        Double result1 = lazy.get();
        assertTrue(lazy.isEvaluated());

        Double result2 = lazy.get();
        assertTrue(result1.equals(result2));
    }

    @Test
    public void futureApi() throws InterruptedException {

        Future<String> future = Future.of(() -> {
            Thread.sleep(1000);
            return "Completed";
        });

        Thread.sleep(2000);

        assertTrue(future.isCompleted());
    }
}
