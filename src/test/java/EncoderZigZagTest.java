import org.example.EncoderZigZag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EncoderZigZagTest {

    @Test
    public void testEncodeZigZagPositiveNumber() {
        int input = 42;
        int encoded = EncoderZigZag.encodeZigZag(input);
        assertEquals(encoded, 84);
    }

    @Test
    public void testEncodeZigZagNegativeNumber() {
        int input = -42;
        int encoded = EncoderZigZag.encodeZigZag(input);
        assertEquals(encoded, 83);
    }
    @Test
    public void testDecodeZigZagPositiveNumber() {
        int encoded = 84;
        int decoded = EncoderZigZag.decodeZigZag(encoded);
        assertEquals(decoded, 42);
    }

    @Test
    public void testDecodeZigZagNegativeNumber() {
        int encoded = 83;
        int decoded = EncoderZigZag.decodeZigZag(encoded);
        assertEquals(decoded, -42);
    }

}
