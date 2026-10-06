/** CO3 application: DP-based document similarity and optimisation demonstrations. */
public class CO3Engine {
    public static void run(CorpusDocument[] docs,String first,String second){
        CorpusDocument a=find(docs,first), b=find(docs,second); if(a==null||b==null){System.out.println("CO3: document not found.");return;}
        String x=shortText(a.text),y=shortText(b.text);
        int ed=DPAlgorithms.editDistance(x,y), lcs=DPAlgorithms.lcs(x,y), nw=DPAlgorithms.needlemanWunsch(x,y), sw=DPAlgorithms.smithWaterman(x,y);
        System.out.println("\n================ CO3 : DYNAMIC PROGRAMMING ================");
        System.out.println("Document A : "+a.name);System.out.println("Document B : "+b.name);System.out.println("Comparison : first 300 normalized characters of each document");
        System.out.println("------------------------------------------------------------");
        System.out.printf("Edit Distance (Levenshtein) : %d%n",ed);
        System.out.printf("LCS length                  : %d%n",lcs);
        System.out.printf("Needleman-Wunsch (global)   : %d%n",nw);
        System.out.printf("Smith-Waterman (local)      : %d%n",sw);
        System.out.println("Interpretation: lower edit distance and higher LCS/local alignment indicate greater textual similarity.");
        int[] dims={10,20,30,15};System.out.println("\nInterval DP demo (matrix-chain cost) : "+DPAlgorithms.matrixChain(dims));
        int[][] tsp={{0,4,7,6},{4,0,5,3},{7,5,0,2},{6,3,2,0}};System.out.println("Bitmask DP demo (issue-route cost)   : "+DPAlgorithms.tsp(tsp));
        int[][] tree={{1,2},{3},{}, {}};System.out.println("Tree DP demo (max independent set)   : "+Math.max(DPAlgorithms.treeIndependentSet(tree,0),0));
        int[] masks={1,3,5,7,3,1};int[] sos=DPAlgorithms.sosCounts(masks,3);System.out.println("SOS/Subset DP demo (all-issue subset): "+sos[7]);
        System.out.println("============================================================\n");
    }
    private static CorpusDocument find(CorpusDocument[] d,String n){for(CorpusDocument x:d)if(x.name.equals(n))return x;return null;}
    private static String shortText(String s){s=s.replaceAll("\\s+"," ");return s.length()>300?s.substring(0,300):s;}
}
