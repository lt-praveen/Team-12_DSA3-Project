/** CO2 - Naive O(n*m) pattern matching. */
public class NaiveSearch {
    public static int count(String text, String pattern) {
        if (pattern.isEmpty()) return 0;
        int count = 0;
        for (int i = 0; i + pattern.length() <= text.length(); i++) {
            int j = 0;
            while (j < pattern.length() && text.charAt(i + j) == pattern.charAt(j)) j++;
            if (j == pattern.length()) count++;
        }
        return count;
    }
}
