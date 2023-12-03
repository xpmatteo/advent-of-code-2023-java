import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PairTest {

    @Test
    void split() {
        var f = Pair.split(String::length, String::toUpperCase);

        assertThat(f.apply("ciao")).isEqualTo(new Pair(4, "CIAO"));
    }

    @Test
    void cross() {
        var f = Pair.cross(String::length, Integer::toHexString);

        assertThat(f.apply(Pair.of("hello", 255))).isEqualTo(Pair.of(5, "ff"));
    }
}
