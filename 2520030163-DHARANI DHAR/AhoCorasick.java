import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AhoCorasick {

    // Trie node: stores next characters and failure link
    static class Node {
        Node[] next = new Node[26];
        Node fail;
        boolean end;
    }

    static Node root = new Node();

    // Insert a pattern into the Trie
    static void insert(String pattern) {
        Node cur = root;

        for (char c : pattern.toCharArray()) {
            int x = c - 'a';

            if (cur.next[x] == null)
                cur.next[x] = new Node();

            cur = cur.next[x];
        }

        cur.end = true;
    }

    // Build failure links using BFS
    static void build() {
        Queue<Node> q = new LinkedList<>();

        root.fail = root;

        for (int i = 0; i < 26; i++) {
            if (root.next[i] != null) {
                root.next[i].fail = root;
                q.add(root.next[i]);
            }
        }

        while (!q.isEmpty()) {
            Node cur = q.remove();

            for (int i = 0; i < 26; i++) {
                Node child = cur.next[i];

                if (child == null) continue;

                Node f = cur.fail;

                while (f != root && f.next[i] == null)
                    f = f.fail;

                if (f.next[i] != null && f.next[i] != child)
                    child.fail = f.next[i];
                else
                    child.fail = root;

                if (child.fail.end)
                    child.end = true;

                q.add(child);
            }
        }
    }

    // Search for multiple patterns at once
    static boolean search(String text) {
        Node cur = root;

        for (char c : text.toCharArray()) {
            if (c < 'a' || c > 'z') {
                cur = root;
                continue;
            }

            int x = c - 'a';

            while (cur != root && cur.next[x] == null)
                cur = cur.fail;

            if (cur.next[x] != null)
                cur = cur.next[x];

            if (cur.end)
                return true;
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.print("Enter patterns separated by space: ");
        String[] patterns = br.readLine().toLowerCase().split(" ");

        for (String p : patterns)
            if (!p.isEmpty()) insert(p);

        build();

        File folder = new File("Corpus");
        File[] files = folder.listFiles();

        System.out.println("\nAho-Corasick Search Results:");

        for (File file : files) {
            if (file.getName().endsWith(".txt")) {
                String text = new String(
                        Files.readAllBytes(file.toPath()))
                        .toLowerCase();

                if (search(text))
                    System.out.println(file.getName() + " -> Pattern Found");
                else
                    System.out.println(file.getName() + " -> Not Found");
            }
        }
    }
}
