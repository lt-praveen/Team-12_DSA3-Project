/** CO2 - Rabin-Karp with a polynomial rolling hash and direct verification. */
public class RabinKarpSearch {
    private static final long MOD = 1_000_000_007L, BASE = 911382323L;
    public static int count(String text, String pattern) {
        int m = pattern.length(), n = text.length();
        if (m == 0 || m > n) return 0;
        long ph = 0, th = 0, high = 1;
        for (int i = 0; i < m; i++) { ph = (ph * 31 + pattern.charAt(i)) % MOD; th = (th * 31 + text.charAt(i)) % MOD; if (i < m - 1) high = high * 31 % MOD; }
        int count = 0;
        for (int i = 0; i <= n - m; i++) {
            if (ph == th && text.regionMatches(i, pattern, 0, m)) count++;
            if (i < n - m) {
                th = (th - text.charAt(i) * high) % MOD;
                if (th < 0) th += MOD;
                th = (th * 31 + text.charAt(i + m)) % MOD;
            }
        }
        return count;
    }
}
