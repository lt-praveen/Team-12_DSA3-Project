import java.io.*;
import java.nio.file.*;
import java.util.*;

public class SearchComparison {

    // Read one document from the common Corpus folder
    static String readFile(File file) throws IOException {
        return new String(Files.readAllBytes(file.toPath())).toLowerCase();
    }

    // Run all algorithms on the same corpus and compare their results
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.print("Enter a single-word pattern: ");
        String pattern = br.readLine().toLowerCase().trim();

        if (pattern.isEmpty() || pattern.contains(" ")) {
            System.out.println("Please enter one word for this comparison.");
            System.out.println("Example: ownership, court, contract");
            return;
        }

        File folder = new File("Corpus");
        File[] files = folder.listFiles();

        if (files == null) {
            System.out.println("Corpus folder not found.");
            return;
        }

        // Store the matching files returned by each algorithm
        Map<String, Set<String>> results = new LinkedHashMap<>();

        results.put("Naive", new LinkedHashSet<>());
        results.put("KMP", new LinkedHashSet<>());
        results.put("Z-Function", new LinkedHashSet<>());
        results.put("Rabin-Karp", new LinkedHashSet<>());
        results.put("Aho-Corasick", new LinkedHashSet<>());
        results.put("Suffix Array", new LinkedHashSet<>());
        results.put("LCP/Kasai", new LinkedHashSet<>());
        results.put("Suffix Automaton", new LinkedHashSet<>());
        results.put("Suffix Tree", new LinkedHashSet<>());

        // Store total execution time of each algorithm
        Map<String, Long> time = new LinkedHashMap<>();

        for (String name : results.keySet())
            time.put(name, 0L);

        int documentCount = 0;

        // Test every algorithm on every corpus document
        for (File file : files) {

            if (!file.getName().startsWith("case") ||
                !file.getName().endsWith(".txt"))
                continue;

            documentCount++;
            String text = readFile(file);

            // 1. Naive
            long start = System.nanoTime();
            boolean found = NaiveSearch.search(text, pattern);
            time.put("Naive",
                    time.get("Naive") + (System.nanoTime() - start));

            if (found)
                results.get("Naive").add(file.getName());

            // 2. KMP
            start = System.nanoTime();
            found = KMP.search(text, pattern);
            time.put("KMP",
                    time.get("KMP") + (System.nanoTime() - start));

            if (found)
                results.get("KMP").add(file.getName());

            // 3. Z-Function
            start = System.nanoTime();
            found = ZFunction.search(text, pattern);
            time.put("Z-Function",
                    time.get("Z-Function") + (System.nanoTime() - start));

            if (found)
                results.get("Z-Function").add(file.getName());

            // 4. Rabin-Karp
            start = System.nanoTime();
            found = RabinKarp.search(text, pattern);
            time.put("Rabin-Karp",
                    time.get("Rabin-Karp") + (System.nanoTime() - start));

            if (found)
                results.get("Rabin-Karp").add(file.getName());

            // 5. Aho-Corasick
            // The current team implementation accepts multiple patterns.
            // Here we insert the same single pattern for a fair comparison.
            start = System.nanoTime();

            AhoCorasick.root = new AhoCorasick.Node();
            AhoCorasick.insert(pattern);
            AhoCorasick.build();
            found = AhoCorasick.search(text);

            time.put("Aho-Corasick",
                    time.get("Aho-Corasick") + (System.nanoTime() - start));

            if (found)
                results.get("Aho-Corasick").add(file.getName());

            // 6. Suffix Array
            start = System.nanoTime();
            found = SuffixArray.search(text, pattern);
            time.put("Suffix Array",
                    time.get("Suffix Array") + (System.nanoTime() - start));

            if (found)
                results.get("Suffix Array").add(file.getName());

            // 7. LCP/Kasai
            // Build the suffix array and calculate LCP using Kasai.
            // The current LCP_Kasai class does not expose a separate
            // pattern-search method, so we use its suffix array for
            // the occurrence check after computing the LCP array.
            start = System.nanoTime();

            int[] sa = LCP_Kasai.suffixArray(text);
            int[] lcp = LCP_Kasai.kasai(text, sa);

            found = false;

            for (int index : sa) {
                if (text.substring(index).startsWith(pattern)) {
                    found = true;
                    break;
                }
            }

            time.put("LCP/Kasai",
                    time.get("LCP/Kasai") + (System.nanoTime() - start));

            if (found)
                results.get("LCP/Kasai").add(file.getName());

            // 8. Suffix Automaton
            start = System.nanoTime();

            SuffixAutomaton.init();

            for (char c : text.toCharArray()) {
                if (c >= 'a' && c <= 'z')
                    SuffixAutomaton.extend(c);
            }

            found = SuffixAutomaton.search(pattern);

            time.put("Suffix Automaton",
                    time.get("Suffix Automaton")
                            + (System.nanoTime() - start));

            if (found)
                results.get("Suffix Automaton").add(file.getName());

            // 9. Suffix Tree
            start = System.nanoTime();

            SuffixTree.root = new SuffixTree.Node();
            SuffixTree.build(text);
            found = SuffixTree.search(pattern);

            time.put("Suffix Tree",
                    time.get("Suffix Tree")
                            + (System.nanoTime() - start));

            if (found)
                results.get("Suffix Tree").add(file.getName());
        }

        // Display comparison
        System.out.println("\n==============================================");
        System.out.println("        LEGAL DOCUMENT SEARCH COMPARISON");
        System.out.println("==============================================");
        System.out.println("Pattern : " + pattern);
        System.out.println("Corpus  : " + documentCount + " text files");
        System.out.println("----------------------------------------------");

        System.out.printf("%-20s %-10s %-15s%n",
                "Algorithm", "Matches", "Time (ms)");
        System.out.println("----------------------------------------------");

        for (String name : results.keySet()) {
            double ms = time.get(name) / 1_000_000.0;

            System.out.printf("%-20s %-10d %-15.3f%n",
                    name,
                    results.get(name).size(),
                    ms);
        }

        System.out.println("----------------------------------------------");

        // Compare every algorithm with Naive as the baseline
        Set<String> baseline = results.get("Naive");
        boolean allMatch = true;

        for (String name : results.keySet()) {
            if (!results.get(name).equals(baseline)) {
                allMatch = false;
                System.out.println(
                        "Mismatch found in: " + name);
            }
        }

        if (allMatch)
            System.out.println("RESULT: All algorithms returned the same documents.");
        else
            System.out.println("RESULT: Some algorithms need correction.");

        System.out.println("==============================================");
    }
}
