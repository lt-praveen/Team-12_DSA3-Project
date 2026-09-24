public class FordFulkerson {
    private static boolean dfs(int[][]r,int s,int t,int[]p){
        boolean[]v=new boolean[r.length];int[]st=new int[r.length];int top=0;
        st[top++]=s;v[s]=true;p[s]=-1;
        while(top>0){int u=st[--top];for(int w=0;w<r.length;w++)
            if(!v[w]&&r[u][w]>0){p[w]=u;v[w]=true;if(w==t)return true;st[top++]=w;}}
        return false;
    }
    public static int maxFlow(int[][]c,int s,int t){
        int n=c.length,f=0;int[][]r=new int[n][n];int[]p=new int[n];
        for(int i=0;i<n;i++)for(int j=0;j<n;j++)r[i][j]=c[i][j];
        while(dfs(r,s,t,p)){int pf=Integer.MAX_VALUE;
            for(int v=t;v!=s;v=p[v])pf=Math.min(pf,r[p[v]][v]);
            for(int v=t;v!=s;v=p[v]){int u=p[v];r[u][v]-=pf;r[v][u]+=pf;} f+=pf;}
        return f;
    }
    public static void main(String[]a){int[][]c={{0,10,5,0,0,0},{0,0,4,8,0,0},{0,0,0,0,8,0},{0,0,0,0,3,10},{0,0,0,0,0,10},{0,0,0,0,0,0}};
        System.out.println("Ford-Fulkerson Maximum Flow = "+maxFlow(c,0,5));}
}
