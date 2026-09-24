public class FlowComparison {
    private static int[][] network(){return new int[][]{{0,10,5,0,0,0},{0,0,4,8,0,0},{0,0,0,0,8,0},{0,0,0,0,3,10},{0,0,0,0,0,10},{0,0,0,0,0,0}};}
    public static void main(String[]x){int[][]c=network();int f=FordFulkerson.maxFlow(c,0,5),e=EdmondsKarp.maxFlow(c,0,5),d=Dinic.maxFlow(c,0,5);
        boolean[]side=new boolean[c.length];int cut=MinCut.maxFlowAndMarkCut(c,0,5,side);
        System.out.println("CO4 LEGAL CITATION FLOW ANALYSIS");System.out.println("Ford-Fulkerson = "+f);
        System.out.println("Edmonds-Karp   = "+e);System.out.println("Dinic          = "+d);System.out.println("Min-Cut value  = "+cut);
        System.out.print("Min-cut source side: ");for(int i=0;i<side.length;i++)if(side[i])System.out.print("Case"+(i+1)+" ");System.out.println();
        System.out.println(f==e&&e==d&&d==cut?"Verification: all algorithms agree.":"Verification: investigate results.");}
}
