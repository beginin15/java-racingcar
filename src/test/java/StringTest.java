import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringTest {

    @Test
    public void split() {
        String[] result = "1,2".split(",");
        assertThat(result).contains("1")
                .contains("2");

        result = "1".split(",");
        assertThat(result).containsExactly("1");
    }
}
