import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestOffByN {
    @Test
    public void testOffByN() {
        CharacterComparator offBy3 = new OffByN(3);
        char c1 = 'a';
        char c2 = 'd';
        char c3 = 'f';
        assertTrue(offBy3.equalChars(c1, c2));
        assertFalse(offBy3.equalChars(c1, c3));
    }
}
