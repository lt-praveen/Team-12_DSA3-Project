import java.io.*;
import java.nio.file.*;

public class SuffixArray {

    // Create a suffix array by sorting all suffix starting positions
    static int[] build(String text) {
        int n = text.length();
        Integer[] sa = new Integer[n];

        for (int i = 0; i < n; i++)
            sa[i] = i;

        java.util.Arrays.sort(sa, (a, b) ->
                text.substring(a).compareTo(text.substring(b)));

        int[] result = new int[n];

        for (int i = 0; i < n; i++)
            result[i] = sa[i];

        return result;
    }

    // Check whether pattern occurs using the suffix array
    static boolean search(String text, String pattern) {
        int[] sa = build(text);

        int left = 0, right = sa.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int start = sa[mid];

            String suffix = text.substring(start);

            if (suffix.startsWith(pattern))
                return true;

            if (suffix.compareTo(pattern) < 0)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.print("Enter pattern: ");
        String pattern = br.readLine().toLowerCase();

        File folder = new File("Corpus");
        File[] files = folder.listFiles();

        System.out.println("\nSuffix Array Search Results:");

        for (File file : files) {
            if (file.getName().endsWith(".txt")) {
                String text = new String(
                        Files.readAllBytes(file.toPath()))
                        .toLowerCase();

                if (search(text, pattern))
                    System.out.println(file.getName() + " -> Found");
                else
                    System.out.println(file.getName() + " -> Not Found");
            }
        }
    }
}
