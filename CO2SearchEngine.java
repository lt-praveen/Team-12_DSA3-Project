/** CO2 application layer: one user pattern is sent to every team algorithm. */
public class CO2SearchEngine {
    public static void run(CorpusDocument[] docs,String rawPattern){
        String p=rawPattern.toLowerCase().trim();
        String[] names={"Naive","KMP","Z-Function","Rabin-Karp","Aho-Corasick","Suffix Array","LCP/Kasai","Suffix Automaton","Suffix Tree Preview"};
        int[][] hits=new int[names.length][docs.length]; long[] time=new long[names.length];
        String[] multi={p};
        for(int a=0;a<names.length;a++){
            long start=System.nanoTime();
            for(int d=0;d<docs.length;d++) hits[a][d]=search(a,docs[d].text,p,multi);
            time[a]=System.nanoTime()-start;
        }
        System.out.println("\n================ CO2 : REAL PATTERN SEARCH ================");
        System.out.println("Query pattern : \""+p+"\"");
        System.out.println("Corpus        : "+docs.length+" text documents");
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-22s %-12s %-14s%n","Algorithm","Documents","Occurrences");
        int baselineDocs=-1,baselineOcc=-1; boolean allAgree=true;
        for(int a=0;a<names.length;a++){
            int dc=0,oc=0;for(int d=0;d<docs.length;d++){if(hits[a][d]>0)dc++;oc+=hits[a][d];}
            if(a==0){baselineDocs=dc;baselineOcc=oc;} else if(dc!=baselineDocs||oc!=baselineOcc)allAgree=false;
            System.out.printf("%-22s %-12d %-14d%n",names[a],dc,oc);
        }
        System.out.println("------------------------------------------------------------");
        System.out.println("Result consistency : "+(allAgree?"PASS - all algorithms agree":"CHECK - algorithms disagree"));
        System.out.println("Measured time      : shown only for comparison; it varies by machine.");
        for(int a=0;a<names.length;a++)System.out.printf("  %-20s %8.3f ms%n",names[a],time[a]/1_000_000.0);
        System.out.println("\nMatching documents (from KMP baseline):");
        for(int d=0;d<docs.length;d++)if(hits[1][d]>0)System.out.printf("  %-28s %d occurrence(s)%n",docs[d].name,hits[1][d]);
        System.out.println("============================================================\n");
    }
    private static int search(int a,String t,String p,String[] m){
        if(a==0)return PraveenNaive(t,p); if(a==1)return PraveenKMP(t,p); if(a==2)return PraveenZ(t,p);
        if(a==3)return DharaniRabin(t,p); if(a==4)return DharaniAho(t,p); if(a==5)return DharaniSA(t,p);
        if(a==6)return NishanthLCP(t,p); if(a==7)return NishanthSAM(t,p); return NishanthTree(t,p);
    }
    private static int PraveenNaive(String t,String p){return NaiveSearch.count(t,p);}
    private static int PraveenKMP(String t,String p){return KMPSearch.count(t,p);}
    private static int PraveenZ(String t,String p){return ZSearch.count(t,p);}
    private static int DharaniRabin(String t,String p){return RabinKarpSearch.count(t,p);}
    private static int DharaniAho(String t,String p){return AhoCorasickSearch.count(t,p);}
    private static int DharaniSA(String t,String p){return SuffixArraySearch.count(t,p);}
    private static int NishanthLCP(String t,String p){return LCPKasaiSearch.count(t,p);}
    private static int NishanthSAM(String t,String p){return SuffixAutomatonSearch.count(t,p);}
    private static int NishanthTree(String t,String p){return SuffixTreeSearch.count(t,p);}
}
