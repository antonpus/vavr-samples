package tuple;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TupleApiTest {

    static Tuple2<String, Integer> oneTwo = Tuple.of("one", 2);

    @Test
    public void creationApi() {

        String one = oneTwo._1;
        Integer two = oneTwo._2;
    }

    @Test
    public void mapApi() {

        Tuple2<String, Integer> mapped = oneTwo.map(
                String::toUpperCase,
                i -> i);

        assertEquals(mapped._1, "ONE");
        assertEquals(mapped._2, oneTwo._2);
    }

    @Test
    public void transformApi() {

        String result = oneTwo.apply((s, i) -> s + i);

        assertEquals(result, "one2");
    }
}
