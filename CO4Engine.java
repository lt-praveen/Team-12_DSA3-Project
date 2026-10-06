/** CO4 application: builds a capacity network from corpus issue categories and compares max-flow algorithms. */
public class CO4Engine {
    private static final String[] CAT={"Property","Contract","Criminal","Family"};
    private static final String[] ISSUE={"Ownership","Agreement","Offence","Custody"};
    public static void run(CorpusDocument[] docs){
        int n=2+CAT.length+ISSUE.length,s=0,t=n-1;int[][] cap=new int[n][n];
        int[] catCount=new int[CAT.length], issueCount=new int[ISSUE.length]; int[][] edgeCount=new int[CAT.length][ISSUE.length];
        for(CorpusDocument d:docs){int c=category(d.text),i=issue(d.text);catCount[c]++;issueCount[i]++;edgeCount[c][i]++;}
        for(int c=0;c<CAT.length;c++){cap[s][1+c]=catCount[c];for(int i=0;i<ISSUE.length;i++)if(edgeCount[c][i]>0)cap[1+c][1+CAT.length+i]=edgeCount[c][i];}
        for(int i=0;i<ISSUE.length;i++)cap[1+CAT.length+i][t]=issueCount[i];
        int ff=FlowAlgorithms.fordFulkerson(cap,s,t),ek=FlowAlgorithms.edmondsKarp(cap,s,t),di=FlowAlgorithms.dinic(cap,s,t);boolean[] side=FlowAlgorithms.sourceSideOfMinCut(cap,s,t);int cut=0;for(int u=0;u<n;u++)if(side[u])for(int v=0;v<n;v++)if(!side[v])cut+=cap[u][v];
        System.out.println("\n================ CO4 : NETWORK FLOW ========================");
        System.out.println("Network meaning: document categories -> legal issues -> sink.");
        System.out.println("Source capacity = number of corpus documents in each category.");
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-14s %-12s%n","Category","Documents");for(int c=0;c<CAT.length;c++)System.out.printf("%-14s %-12d%n",CAT[c],catCount[c]);
        System.out.println("\nIssue capacities:");for(int i=0;i<ISSUE.length;i++)System.out.printf("  %-14s %d document(s)%n",ISSUE[i],issueCount[i]);
        System.out.println("\nCorpus-derived category -> issue capacities:");for(int c=0;c<CAT.length;c++)for(int i=0;i<ISSUE.length;i++)if(edgeCount[c][i]>0)System.out.printf("  %s -> %s : %d%n",CAT[c],ISSUE[i],edgeCount[c][i]);
        System.out.println("\nMaximum-flow comparison:");System.out.printf("  Ford-Fulkerson : %d%n",ff);System.out.printf("  Edmonds-Karp   : %d%n",ek);System.out.printf("  Dinic          : %d%n",di);System.out.printf("  Minimum Cut    : %d%n",cut);
        System.out.println("Verification: "+(ff==ek&&ek==di&&di==cut?"PASS - all max-flow methods agree and max-flow = min-cut":"CHECK - inspect network"));
        System.out.println("============================================================\n");
    }
    private static int category(String s){int p=s.indexOf("category:");if(p>=0){String x=s.substring(p+9).trim();if(x.startsWith("property"))return 0;if(x.startsWith("contract"))return 1;if(x.startsWith("criminal"))return 2;}return 3;}
    private static int issue(String s){int p=s.indexOf("primary issue:");if(p>=0){String x=s.substring(p+14).trim();if(x.startsWith("ownership")||x.startsWith("property"))return 0;if(x.startsWith("agreement")||x.startsWith("contract"))return 1;if(x.startsWith("offence")||x.startsWith("criminal"))return 2;}return 3;}
}
