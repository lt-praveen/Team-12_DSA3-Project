import java.io.*;
import java.nio.file.*;

public class KMP {

    // Creates the LPS array for the given pattern.
    // LPS = Longest Proper Prefix which is also a Suffix.
    static int[] lps(String p) {
        int[] a = new int[p.length()];
        int j = 0;

        // Build the LPS array from left to right
        for (int i = 1; i < p.length();) {

            // If characters match, increase the current prefix length
            if (p.charAt(i) == p.charAt(j))
                a[i++] = ++j;

            // If mismatch occurs, use the previous LPS value
            else if (j > 0)
                j = a[j - 1];

            // No previous match, so move to the next character
            else
                i++;
        }

        return a;
    }

    // KMP pattern searching algorithm
    static boolean search(String text, String p) {

        // First calculate the LPS array of the pattern
        int[] a = lps(p);

        int i = 0;  // Current position in text
        int j = 0;  // Current position in pattern

        while (i < text.length()) {

            // Characters match, so move both pointers
            if (text.charAt(i) == p.charAt(j)) {
                i++;
                j++;

                // Complete pattern matched
                if (j == p.length())
                    return true;
            }

            // Mismatch after some characters matched:
            // use LPS instead of starting from the beginning
            else if (j > 0)
                j = a[j - 1];

            // Mismatch at the beginning, move to next text character
            else
                i++;
        }

        // Pattern was not found
        return false;
    }

    public static void main(String[] args) throws Exception {

        // Read the pattern entered by the user
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.print("Enter pattern: ");

        // Convert pattern to lowercase
        String pattern = br.readLine().toLowerCase();

        // Open the common legal-document corpus
        File folder = new File("Corpus");

        // Get all files from the corpus
        File[] files = folder.listFiles();

        System.out.println("\nKMP Search Results:");

        // Search every text document
        for (File file : files) {

            // Only read .txt files
            if (file.getName().endsWith(".txt")) {

                // Read complete document text
                String text = new String(
                        Files.readAllBytes(file.toPath()))
                        .toLowerCase();

                // Apply KMP search
                if (search(text, pattern))
                    System.out.println(file.getName() + " -> Found");
                else
                    System.out.println(file.getName() + " -> Not Found");
            }
        }
    }
}
