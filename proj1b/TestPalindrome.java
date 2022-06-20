import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        //normal:True
        String input1 = "abcde";
        boolean actual1 = Palindrome.isPalindrome(input1);
        assertFalse(actual1);

        //normal:False
        String input2 = "aaa";
        boolean actual2 = Palindrome.isPalindrome(input2);
        assertTrue(actual2);

        //special case1: one element
        String input3 = "a";
        boolean actual3 = Palindrome.isPalindrome(input3);
        assertTrue(actual3);

        //special case2: 0 element
        String input4 = "";
        boolean actual4 = Palindrome.isPalindrome(input4);
        assertTrue(actual4);
    }

    @Test
    /** test Palindrome.isPalindrome(string, Cc) */
    public void testCustomizedIsPalindrome() {
        /* special case: 0 or 1 element */
        String s1= "";
        String s2 = "a";
        CharacterComparator offByOne = new OffByOne();
        boolean actual1 = Palindrome.isPalindrome(s1, offByOne);
        boolean actual2 = Palindrome.isPalindrome(s2, offByOne);
        assertTrue(actual1);
        assertTrue(actual2);

        /* normal case: true */
        String s3 = "acedb";
        boolean actual3 = Palindrome.isPalindrome(s3, offByOne);
        assertTrue(actual3);

        /* normal case: false */
        String s4 = "aceedb";
        boolean actual4 = Palindrome.isPalindrome(s4, offByOne);
        assertFalse(actual4);
    }

}
