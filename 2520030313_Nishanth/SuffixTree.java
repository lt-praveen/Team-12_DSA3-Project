import java.io.*;
import java.nio.file.*;

public class SuffixTree {

    // Simple suffix-trie/tree style structure for text indexing
    static class Node {
        Node[] next = new Node[26];
        boolean end;
    }

    static Node root = new Node();

    // Insert a suffix into the tree
    static void insert(String s, int start) {
        Node cur = root;

        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c < 'a' || c > 'z')
                continue;

            int x = c - 'a';

            if (cur.next[x] == null)
                cur.next[x] = new Node();

            cur = cur.next[x];
        }

        cur.end = true;
    }

    // Insert every suffix of the text
    static void build(String text) {
        for (int i = 0; i < text.length(); i++)
            insert(text, i);
    }

    // Search a pattern in the suffix structure
    static boolean search(String pattern) {
        Node cur = root;

        for (char c : pattern.toCharArray()) {
            if (c < 'a' || c > 'z')
                continue;

            if (cur.next[c - 'a'] == null)
                return false;

            cur = cur.next[c - 'a'];
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.print("Enter pattern: ");
        String pattern = br.readLine().toLowerCase()
                .replaceAll("[^a-z]", "");

        File folder = new File("Corpus");
        File[] files = folder.listFiles();

        System.out.println("\nSuffix Tree Search Results:");

        for (File file : files) {
            if (file.getName().endsWith(".txt")) {
                String text = new String(
                        Files.readAllBytes(file.toPath()))
                        .toLowerCase();

                root = new Node();
                build(text);

                if (search(pattern))
                    System.out.println(file.getName() + " -> Found");
                else
                    System.out.println(file.getName() + " -> Not Found");
            }
        }
    }
}
