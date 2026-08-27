# Legal Document Repository

## Project Overview

A Data Structures and Algorithms - 3 (DSA-3) project that implements efficient string-searching techniques on a shared corpus of legal text documents. The system treats the corpus as a searchable legal-document repository and evaluates multiple string algorithms using the same documents and search patterns.

## Team Members

| Team Member | ID Number |
|---|---|
| Busala Laxmi Praveen | 2520030406 |
| Dharani Dhar | 2520030163 |
| N. Nishanth | 2520030313 |

## Supervisor

**Supervisor:** Dr. Vinay Kumar

> Replace the placeholder above with the supervisor's exact name before submitting the repository.

## Abstract

The Legal Document Repository is a DSA-3 project focused on efficient pattern searching over a collection of legal text documents. The project uses a common corpus of 72 legal-document text files and implements multiple string algorithms to identify documents containing a given search pattern. The implemented algorithms include Naive Pattern Matching, Knuth-Morris-Pratt (KMP), Z-Function, Rabin-Karp, Aho-Corasick, Suffix Array, LCP/Kasai, Suffix Automaton, and Suffix Tree. Each team member contributes individual algorithm implementations, while the integrated search-comparison program runs the algorithms on the same corpus and compares their matching results and execution times. The project demonstrates how different string-processing techniques can be applied to large collections of text documents and provides a basis for evaluating their practical performance.

## Project Structure

```text
Team-12_DSA3-Project/
│
├── Corpus/
│   ├── case001.txt
│   ├── case002.txt
│   ├── ...
│   └── case072.txt
│
├── Praveen_Algorithms/
│   ├── NaiveSearch.java
│   ├── KMP.java
│   └── ZFunction.java
│
├── Dharani_Algorithms/
│   ├── RabinKarp.java
│   ├── AhoCorasick.java
│   └── SuffixArray.java
│
├── Nishanth_Algorithms/
│   ├── LCP_Kasai.java
│   ├── SuffixAutomaton.java
│   └── SuffixTree.java
│
├── SearchComparison.java
└── README.md
```

The `Corpus` folder is common to the entire team. Algorithm ownership and individual contribution are represented through the Java implementations rather than by dividing the corpus between members.

## Algorithms Implemented

### Praveen
- Naive Pattern Matching
- Knuth-Morris-Pratt (KMP)
- Z-Function

### Dharani Dhar
- Rabin-Karp
- Aho-Corasick
- Suffix Array

### N. Nishanth
- LCP / Kasai
- Suffix Automaton
- Suffix Tree

### Team Integration
- `SearchComparison.java` runs the implemented algorithms on the common corpus and compares their results and execution times.

## Setup

### Requirements

- Java JDK installed
- Visual Studio Code or another Java-supported IDE
- The complete project folder with the `Corpus` directory and all algorithm source files

### Folder Requirement

The `Corpus` directory must be available from the project root:

```text
Project/
├── Corpus/
├── Praveen_Algorithms/
├── Dharani_Algorithms/
├── Nishanth_Algorithms/
└── SearchComparison.java
```

The corpus contains 72 case text files (`case001.txt` to `case072.txt`).

## Compilation

Open the project root in the VS Code terminal and compile all algorithm classes together with the comparison program:

```bash
javac -d out "Praveen_Algorithms"/*.java Dharani_Algorithms/*.java Nishanth_Algorithms/*.java SearchComparison.java
```

The `-d out` option stores the generated `.class` files in the `out` directory.

## Execution

Run the integrated comparison program using:

```bash
java -cp out SearchComparison
```

Enter a search pattern when prompted, for example:

```text
ownership
```

The program searches the common corpus using the implemented algorithms and displays the number of matching documents and execution time for each algorithm. It also checks whether the algorithms return the same set of matching documents.

## Current Phase Status

**Status: CO2 String Algorithms Implementation and Integration Completed**

Completed:
- Common 72-document legal corpus prepared
- Individual string-algorithm implementations completed
- Individual team-member contributions organized in separate algorithm folders
- Integrated `SearchComparison.java` completed
- Algorithms tested against the common corpus
- Matching results and execution-time comparison supported

The repository is currently organized for the DSA-3 project implementation and demonstration. Further application-level features can be added in later project phases if required by the course review.

## Course Alignment

The project focuses on the CO2 String Algorithms component, including pattern matching, KMP and its failure/LPS function, Z-Function, Rabin-Karp hashing, multi-pattern matching with Aho-Corasick, suffix arrays, and LCP/Kasai. Suffix automata and suffix trees are included at the preview/implementation level appropriate to the project scope.
