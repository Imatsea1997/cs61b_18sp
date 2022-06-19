import org.junit.Test;

import static org.junit.Assert.*;

public class FlikTest {
    @Test
    public void testIsSameNumber() {
        int a = 1;
        int b = 2;
        int c = 1;
        boolean expected1 = false;
        boolean actual1 = Flik.isSameNumber(a, b);

        boolean actual2 = Flik.isSameNumber(a, c);

        assertFalse(expected1);
        assertTrue(actual2);

    }
}
