import java.io.*;
import java.nio.file.*;

public class ZFunction {

    // Creates the Z array.
    // z[i] = length of the longest substring starting at i
    // that matches the prefix of the complete string.
    static int[] zArray(String s) {
        int[] z = new int[s.length()];

        // [left, right] represents the current Z-box
        int left = 0, right = 0;

        for (int i = 1; i < s.length(); i++) {

            // If i is inside the current Z-box,
            // use previously calculated information
            if (i <= right)
                z[i] = Math.min(right - i + 1, z[i - left]);

            // Continue comparing characters
            while (i + z[i] < s.length() &&
                   s.charAt(z[i]) == s.charAt(i + z[i]))
                z[i]++;

            // Update the Z-box if this match goes farther
            if (i + z[i] - 1 > right) {
                left = i;
                right = i + z[i] - 1;
            }
        }

        return z;
    }

    // Searches for a pattern using the Z-function
    static boolean search(String text, String pattern) {

        // Combine pattern and text.
        // '$' is a separator that does not belong to the pattern.
        String combined = pattern + "$" + text;

        // Calculate Z values for the combined string
        int[] z = zArray(combined);

        // Pattern length match in the Z array means pattern is found
        for (int i = pattern.length() + 1;
             i < combined.length(); i++) {

            if (z[i] == pattern.length())
                return true;
        }

        return false;
    }

    public static void main(String[] args) throws Exception {

        // Read the search pattern from the user
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.print("Enter pattern: ");

        // Convert pattern to lowercase for case-insensitive search
        String pattern = br.readLine().toLowerCase();

        // Open the common legal-document corpus
        File folder = new File("Corpus");

        // Get all files in the corpus
        File[] files = folder.listFiles();

        System.out.println("\nZ-Function Search Results:");

        // Search every text document
        for (File file : files) {

            // Process only .txt files
            if (file.getName().endsWith(".txt")) {

                // Read the complete document
                String text = new String(
                        Files.readAllBytes(file.toPath()))
                        .toLowerCase();

                // Apply Z-function pattern search
                if (search(text, pattern))
                    System.out.println(file.getName() + " -> Found");
                else
                    System.out.println(file.getName() + " -> Not Found");
            }
        }
    }
}
