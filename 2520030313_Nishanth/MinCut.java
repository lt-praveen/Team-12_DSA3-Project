public class MinCut {
    private static boolean bfs(int[][]r,int s,int t,int[]p){boolean[]v=new boolean[r.length];int[]q=new int[r.length];int h=0,z=0;q[z++]=s;v[s]=true;p[s]=-1;
        while(h<z){int u=q[h++];for(int w=0;w<r.length;w++)if(!v[w]&&r[u][w]>0){p[w]=u;v[w]=true;q[z++]=w;}}return v[t];}
    public static int maxFlowAndMarkCut(int[][]c,int s,int t,boolean[]side){int n=c.length,f=0,r[][]=new int[n][n],p[]=new int[n];
        for(int i=0;i<n;i++)for(int j=0;j<n;j++)r[i][j]=c[i][j];
        while(bfs(r,s,t,p)){int pf=Integer.MAX_VALUE;for(int v=t;v!=s;v=p[v])pf=Math.min(pf,r[p[v]][v]);
            for(int v=t;v!=s;v=p[v]){int u=p[v];r[u][v]-=pf;r[v][u]+=pf;}f+=pf;}
        boolean[]v=new boolean[n];int[]q=new int[n];int h=0,z=0;q[z++]=s;v[s]=true;while(h<z){int u=q[h++];
            for(int w=0;w<n;w++)if(!v[w]&&r[u][w]>0){v[w]=true;q[z++]=w;}}for(int i=0;i<n;i++)side[i]=v[i];return f;}
    public static void main(String[]x){int[][]c={{0,10,5,0,0,0},{0,0,4,8,0,0},{0,0,0,0,8,0},{0,0,0,0,3,10},{0,0,0,0,0,10},{0,0,0,0,0,0}};
        boolean[]s=new boolean[c.length];System.out.println("Min-Cut Value = "+maxFlowAndMarkCut(c,0,5,s));System.out.print("Source side: ");
        for(int i=0;i<s.length;i++)if(s[i])System.out.print("Case"+(i+1)+" ");System.out.println();}
}
