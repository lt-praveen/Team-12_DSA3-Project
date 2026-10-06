/** CO2 - Knuth-Morris-Pratt using the LPS/failure array. */
public class KMPSearch {
    private static int[] lps(String p) {
        int[] a = new int[p.length()];
        for (int i = 1, j = 0; i < p.length();) {
            if (p.charAt(i) == p.charAt(j)) a[i++] = ++j;
            else if (j > 0) j = a[j - 1];
            else i++;
        }
        return a;
    }
    public static int count(String text, String pattern) {
        if (pattern.isEmpty()) return 0;
        int[] a = lps(pattern); int count = 0;
        for (int i = 0, j = 0; i < text.length();) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++; j++;
                if (j == pattern.length()) { count++; j = a[j - 1]; }
            } else if (j > 0) j = a[j - 1];
            else i++;
        }
        return count;
    }
}
