public class Palindrome {
    public static Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public static boolean isPalindrome(String word) {
        Deque<Character> d1 = wordToDeque(word);
        while (d1.size() > 1) {
            char c1 = d1.removeFirst();
            char c2 = d1.removeLast();
            if (c1 != c2) {
                return false;
            }
        }
        return true;
    }

    /** return true if the word is a palindrome(not traditional meaning but we define it by cc)
     * according to the character comparison test provided by the CharacterComparator passed in as argument cc. */
    public static boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() <= 1) {
            return true;
        }
        Deque<Character> d1 = wordToDeque(word);
        while (d1.size() > 1) {
            char c1 = d1.removeFirst();
            char c2 = d1.removeLast();
            if (!cc.equalChars(c1, c2)) {
                return false;
            }
        }
        return true;
    }
}
