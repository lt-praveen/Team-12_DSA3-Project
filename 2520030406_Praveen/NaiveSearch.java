import java.io.*;
import java.nio.file.*;

public class NaiveSearch {

    // Naive pattern matching:
    // Try to match the pattern starting from every position in the text.
    static boolean search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        // Check every possible starting position
        for (int i = 0; i <= n - m; i++) {
            int j = 0;

            // Compare pattern characters with text characters
            while (j < m && text.charAt(i + j) == pattern.charAt(j))
                j++;

            // If all pattern characters matched, pattern is found
            if (j == m)
                return true;
        }

        // Pattern was not found
        return false;
    }

    public static void main(String[] args) throws Exception {

        // Used to read the search pattern from the user
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.print("Enter pattern: ");

        // Convert to lowercase for case-insensitive searching
        String pattern = br.readLine().toLowerCase();

        // Open the common legal-document corpus folder
        File folder = new File("Corpus");

        // Get all files present in the corpus
        File[] files = folder.listFiles();

        System.out.println("\nNaive Search Results:");

        // Search every text document in the corpus
        for (File file : files) {

            // Process only .txt documents
            if (file.getName().endsWith(".txt")) {

                // Read the complete document
                String text = new String(
                        Files.readAllBytes(file.toPath()))
                        .toLowerCase();

                // Apply Naive Pattern Matching
                if (search(text, pattern))
                    System.out.println(file.getName() + " -> Found");
                else
                    System.out.println(file.getName() + " -> Not Found");
            }
        }
    }
}
