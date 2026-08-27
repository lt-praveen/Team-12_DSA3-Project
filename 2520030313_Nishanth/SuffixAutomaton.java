import java.io.*;
import java.nio.file.*;

public class SuffixAutomaton {

    static class State {
        State[] next = new State[26];
        State link;
        int len;
    }

    static State root, last;

    // Initialize the suffix automaton
    static void init() {
        root = new State();
        root.link = null;
        last = root;
    }

    // Add one character to the automaton
    static void extend(char c) {
        int x = c - 'a';

        State cur = new State();
        cur.len = last.len + 1;

        State p = last;

        while (p != null && p.next[x] == null) {
            p.next[x] = cur;
            p = p.link;
        }

        if (p == null) {
            cur.link = root;
        } else {
            State q = p.next[x];

            if (p.len + 1 == q.len) {
                cur.link = q;
            } else {
                State clone = new State();
                clone.len = p.len + 1;
                clone.link = q.link;
                clone.next = q.next.clone();

                while (p != null && p.next[x] == q) {
                    p.next[x] = clone;
                    p = p.link;
                }

                q.link = cur.link = clone;
            }
        }

        last = cur;
    }

    // Check whether a pattern exists in the text
    static boolean search(String pattern) {
        State cur = root;

        for (char c : pattern.toCharArray()) {
            if (c < 'a' || c > 'z' || cur.next[c - 'a'] == null)
                return false;

            cur = cur.next[c - 'a'];
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.print("Enter pattern: ");
        String pattern = br.readLine().toLowerCase();

        File folder = new File("Corpus");
        File[] files = folder.listFiles();

        System.out.println("\nSuffix Automaton Search Results:");

        for (File file : files) {
            if (file.getName().endsWith(".txt")) {
                String text = new String(
                        Files.readAllBytes(file.toPath()))
                        .toLowerCase();

                init();

                // Build automaton using alphabetic characters
                for (char c : text.toCharArray())
                    if (c >= 'a' && c <= 'z')
                        extend(c);

                if (search(pattern.replaceAll("[^a-z]", "")))
                    System.out.println(file.getName() + " -> Found");
                else
                    System.out.println(file.getName() + " -> Not Found");
            }
        }
    }
}
