# Team 12 - DSA-3 Legal Document Analytics Engine

## Project idea
A Java console application that treats a collection of legal-text documents as a corpus and applies the DSA-3 algorithms directly to that data.

**Important design change:** algorithm classes are reusable methods. They do not contain their own `main()` methods. `Main.java` is the single entry point and links CO2, CO3 and CO4 to the same corpus.

## Team structure
- `Praveen's_Algorithms/` - Naive, KMP, Z-function
- `Dharani_Algorithms/` - Rabin-Karp, Aho-Corasick, Suffix Array
- `Nishanth_Algorithms/` - LCP/Kasai, Suffix Automaton, Suffix Tree preview
- Root classes - corpus loading, CO2/CO3/CO4 application layers and the single main program
- `corpus/` - 72 synthetic legal-text documents for testing. Replace these with the team's approved corpus if required by faculty.

## CO2 - String algorithms
The user enters one pattern. The engine sends the same normalized pattern to all nine implementations and searches every corpus document. The report shows, for each algorithm:
- number of matching documents
- total occurrence count
- measured runtime
- consistency check against the KMP result
- names of documents where the pattern was found

Algorithms demonstrated: Naive, KMP, Z-function, Rabin-Karp, Aho-Corasick, suffix-array search, LCP/Kasai, suffix automaton, and suffix-tree preview.

## CO3 - Advanced Dynamic Programming
The engine compares two corpus documents using:
- Levenshtein edit distance
- LCS
- Needleman-Wunsch global alignment
- Smith-Waterman local alignment

It also runs small, clearly labelled demonstrations of interval DP, bitmask DP/TSP, tree DP and SOS/subset DP so the advanced DP patterns are visible without pretending that they are string-search algorithms.

## CO4 - Network Flow
The engine derives a small capacity network from corpus categories/issues and compares:
- Ford-Fulkerson
- Edmonds-Karp
- Dinic
- minimum cut

The output explicitly verifies whether the three max-flow methods agree and whether max-flow equals min-cut.

## Run in VS Code / terminal
Prerequisite: JDK 17+.

From the project root:

```text
javac -d out "Praveen's_Algorithms"/*.java Dharani_Algorithms/*.java Nishanth_Algorithms/*.java *.java
java -cp out Main
```

Then use the menu. Option `4` runs a complete demonstration of CO2, CO3 and CO4.

## Why this structure is faculty-friendly
There is one application entry point. The main program loads the corpus once, then calls the algorithm classes as methods. The output reports actual computed results instead of printing algorithm names or hard-coded values.

## Note on corpus
The included 72 files are synthetic test documents generated for software testing. They are not presented as real court records or legal advice. The project is an academic DSA demonstration.
