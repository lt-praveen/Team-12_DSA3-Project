# Legal Document Repository

## Project Overview

A Data Structures and Algorithms - 3 (DSA-3) project that applies string algorithms, dynamic programming techniques, and graph algorithms to a common corpus of legal-style text documents.

The project treats the corpus as a searchable legal-document repository and demonstrates multiple algorithmic techniques from the DSA-3 syllabus. The system is organized into three major components:

- **CO2:** String Algorithms for searching legal documents
- **CO3:** Dynamic Programming for text comparison and analysis
- **CO4:** Graph and Flow Algorithms for relationship and capacity-based analysis

All team members work on the same common corpus, while individual contributions are represented through separate algorithm implementations.

---

## Team Members

| Team Member | ID Number |
|------------|-----------|
| Busala Laxmi Praveen | 2520030406 |
| Dharani Dhar | 2520030163 |
| N. Nishanth | 2520030313 |

## Supervisor

**Supervisor:** Dr. Vinay Kumar

---

## Abstract

The Legal Document Repository is a DSA-3 project focused on applying fundamental and advanced data structures and algorithms to a common corpus of legal-style text documents.

The project contains a corpus of 72 legal-document text files and implements multiple algorithmic techniques across CO2, CO3, and CO4.

For **CO2**, the project implements string-searching and string-processing algorithms including Naive Pattern Matching, Knuth-Morris-Pratt (KMP), Z-Function, Rabin-Karp, Aho-Corasick, Suffix Array, LCP/Kasai, Suffix Automaton, and Suffix Tree. These algorithms are used to search for patterns across the common legal-document corpus.

For **CO3**, the project applies Dynamic Programming techniques including Edit Distance using the Wagner-Fischer approach, Needleman-Wunsch global sequence alignment, Smith-Waterman local sequence alignment, Interval DP, Bitmask DP, and Subset DP. These techniques demonstrate text comparison, sequence alignment, and other dynamic programming patterns.

For **CO4**, the project applies graph and network-flow algorithms including Ford-Fulkerson, Edmonds-Karp, Dinic, and Min-Cut. These algorithms demonstrate maximum-flow and minimum-cut analysis using a legal-case flow network.

The project integrates the individual contributions of all three team members and provides comparison programs for demonstrating the implemented algorithms.

---

# Project Structure

```text
Team-12_DSA3-Project/
│
├── Corpus/
│   ├── case001.txt
│   ├── case002.txt
│   ├── ...
│   └── case072.txt
│
├── Praveen's_Algorithms/
│   ├── NaiveSearch.java
│   ├── KMP.java
│   ├── ZFunction.java
│   ├── EditDistance.java
│   ├── NeedlemanWunsch.java
│   └── FordFulkerson.java
│
├── Dharani_Algorithms/
│   ├── RabinKarp.java
│   ├── AhoCorasick.java
│   ├── SuffixArray.java
│   ├── SmithWaterman.java
│   ├── BitmaskTSP.java
│   └── EdmondsKarp.java
│
├── Nishanth_Algorithms/
│   ├── LCP_Kasai.java
│   ├── SuffixAutomaton.java
│   ├── SuffixTree.java
│   ├── IntervalDP.java
│   ├── SubsetDP.java
│   ├── Dinic.java
│   └── MinCut.java
│
├── SearchComparison.java
├── DPComparison.java
├── FlowComparison.java
├── README_CO3_CO4.md
└── README.md
