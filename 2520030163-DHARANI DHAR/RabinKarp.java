import java.io.*;
import java.nio.file.*;

public class RabinKarp {

    // Rabin-Karp uses a rolling hash to compare the pattern with text windows
    static boolean search(String text, String pattern) {
        int n = text.length(), m = pattern.length();
        if (m > n) return false;

        int base = 256;
        int prime = 101;
        int pHash = 0, tHash = 0, h = 1;

        // Calculate base^(m-1) % prime
        for (int i = 0; i < m - 1; i++)
            h = (h * base) % prime;

        // Calculate initial hash values
        for (int i = 0; i < m; i++) {
            pHash = (base * pHash + pattern.charAt(i)) % prime;
            tHash = (base * tHash + text.charAt(i)) % prime;
        }

        // Slide the pattern over the text
        for (int i = 0; i <= n - m; i++) {

            // If hashes match, verify characters
            if (pHash == tHash) {
                int j = 0;
                while (j < m &&
                       text.charAt(i + j) == pattern.charAt(j))
                    j++;

                if (j == m) return true;
            }

            // Calculate hash for the next window
            if (i < n - m) {
                tHash = (base * (tHash
                        - text.charAt(i) * h)
                        + text.charAt(i + m)) % prime;

                if (tHash < 0)
                    tHash += prime;
            }
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

        System.out.println("\nRabin-Karp Search Results:");

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
