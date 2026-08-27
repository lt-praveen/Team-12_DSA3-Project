import java.io.*;
import java.nio.file.*;

public class LCP_Kasai {

    // Build a simple suffix array
    static int[] suffixArray(String s) {
        int n = s.length();
        Integer[] a = new Integer[n];

        for (int i = 0; i < n; i++)
            a[i] = i;

        java.util.Arrays.sort(a, (x, y) ->
                s.substring(x).compareTo(s.substring(y)));

        int[] sa = new int[n];

        for (int i = 0; i < n; i++)
            sa[i] = a[i];

        return sa;
    }

    // Kasai algorithm builds the LCP array
    static int[] kasai(String s, int[] sa) {
        int n = s.length();
        int[] rank = new int[n];
        int[] lcp = new int[n];

        for (int i = 0; i < n; i++)
            rank[sa[i]] = i;

        int k = 0;

        for (int i = 0; i < n; i++) {
            if (rank[i] == n - 1) {
                k = 0;
                continue;
            }

            int j = sa[rank[i] + 1];

            while (i + k < n && j + k < n &&
                   s.charAt(i + k) == s.charAt(j + k))
                k++;

            lcp[rank[i]] = k;

            if (k > 0)
                k--;
        }

        return lcp;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.print("Enter pattern: ");
        String pattern = br.readLine().toLowerCase();

        File folder = new File("Corpus");
        File[] files = folder.listFiles();

        System.out.println("\nLCP/Kasai Search Results:");

        for (File file : files) {
            if (file.getName().endsWith(".txt")) {
                String text = new String(
                        Files.readAllBytes(file.toPath()))
                        .toLowerCase();

                int[] sa = suffixArray(text);
                int[] lcp = kasai(text, sa);

                boolean found = false;

                for (int i = 0; i < sa.length; i++) {
                    if (text.substring(sa[i]).startsWith(pattern)) {
                        found = true;
                        break;
                    }
                }

                System.out.println(file.getName() +
                        (found ? " -> Found" : " -> Not Found"));
            }
        }
    }
}
