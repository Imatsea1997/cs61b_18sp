public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public boolean isPalindrome(String word) {
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

    public boolean isPalindrome(String word, CharacterComparator cc) {
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
