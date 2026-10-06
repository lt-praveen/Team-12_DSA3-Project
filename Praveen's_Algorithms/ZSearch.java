/** CO2 - Z-function pattern matching. */
public class ZSearch {
    public static int count(String text, String pattern) {
        if (pattern.isEmpty()) return 0;
        String s = pattern + "\u0001" + text;
        int[] z = new int[s.length()]; int l = 0, r = 0, count = 0;
        for (int i = 1; i < s.length(); i++) {
            if (i <= r) z[i] = Math.min(r - i + 1, z[i - l]);
            while (i + z[i] < s.length() && s.charAt(z[i]) == s.charAt(i + z[i])) z[i]++;
            if (i + z[i] - 1 > r) { l = i; r = i + z[i] - 1; }
            if (z[i] == pattern.length()) count++;
        }
        return count;
    }
}
