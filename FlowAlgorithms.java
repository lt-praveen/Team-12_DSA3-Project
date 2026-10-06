/** CO4 max-flow algorithms implemented from scratch without java.util.*. */
public class FlowAlgorithms {
    public static int fordFulkerson(int[][] cap,int s,int t){int n=cap.length,flow=0;int[][] r=copy(cap);while(true){int[] parent=bfs(r,s,t);if(parent[t]<0)break;int add=1_000_000;for(int v=t;v!=s;v=parent[v])add=Math.min(add,r[parent[v]][v]);for(int v=t;v!=s;v=parent[v]){int u=parent[v];r[u][v]-=add;r[v][u]+=add;}flow+=add;}return flow;}
    public static int edmondsKarp(int[][] cap,int s,int t){return fordFulkerson(cap,s,t);}
    public static int dinic(int[][] cap,int s,int t){int n=cap.length,flow=0;int[][] r=copy(cap);int[] level=new int[n];while(levelBfs(r,s,t,level)){int[] it=new int[n];int f;while((f=dfs(r,level,it,s,t,1_000_000))>0)flow+=f;}return flow;}
    public static boolean[] sourceSideOfMinCut(int[][] cap,int s,int t){
        int n=cap.length; int[][] r=copy(cap);
        while(true){int[] p=bfs(r,s,t);if(p[t]<0)break;int add=1_000_000;for(int v=t;v!=s;v=p[v])add=Math.min(add,r[p[v]][v]);for(int v=t;v!=s;v=p[v]){int u=p[v];r[u][v]-=add;r[v][u]+=add;}}
        boolean[] seen=new boolean[n];int[] q=new int[n];int h=0,b=0;q[b++]=s;seen[s]=true;while(h<b){int u=q[h++];for(int v=0;v<n;v++)if(!seen[v]&&r[u][v]>0){seen[v]=true;q[b++]=v;}}return seen;
    }
    private static int[] bfs(int[][] r,int s,int t){int n=r.length;int[] p=new int[n];for(int i=0;i<n;i++)p[i]=-1;int[] q=new int[n];int h=0,b=0;q[b++]=s;p[s]=s;while(h<b){int u=q[h++];for(int v=0;v<n;v++)if(p[v]<0&&r[u][v]>0){p[v]=u;if(v==t)return p;q[b++]=v;}}return p;}
    private static boolean levelBfs(int[][] r,int s,int t,int[] level){for(int i=0;i<level.length;i++)level[i]=-1;int[] q=new int[level.length];int h=0,b=0;q[b++]=s;level[s]=0;while(h<b){int u=q[h++];for(int v=0;v<r.length;v++)if(level[v]<0&&r[u][v]>0){level[v]=level[u]+1;q[b++]=v;}}return level[t]>=0;}
    private static int dfs(int[][] r,int[] level,int[] it,int u,int t,int pushed){if(u==t)return pushed;for(;it[u]<r.length;it[u]++){int v=it[u];if(r[u][v]>0&&level[v]==level[u]+1){int f=dfs(r,level,it,v,t,Math.min(pushed,r[u][v]));if(f>0){r[u][v]-=f;r[v][u]+=f;return f;}}}return 0;}
    private static int[][] copy(int[][] a){int[][] b=new int[a.length][a.length];for(int i=0;i<a.length;i++)for(int j=0;j<a.length;j++)b[i][j]=a[i][j];return b;}
}
