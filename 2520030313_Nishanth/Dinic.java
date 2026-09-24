public class Dinic {
    private static int[]level,ptr;
    private static boolean bfs(int[][]r,int s,int t){int[]q=new int[r.length];int h=0,z=0;for(int i=0;i<level.length;i++)level[i]=-1;
        level[s]=0;q[z++]=s;while(h<z){int u=q[h++];for(int v=0;v<r.length;v++)if(r[u][v]>0&&level[v]<0){level[v]=level[u]+1;q[z++]=v;}}return level[t]>=0;}
    private static int send(int[][]r,int u,int t,int f){if(u==t)return f;for(;ptr[u]<r.length;ptr[u]++){int v=ptr[u];
        if(r[u][v]>0&&level[v]==level[u]+1){int x=send(r,v,t,Math.min(f,r[u][v]));if(x>0){r[u][v]-=x;r[v][u]+=x;return x;}}}return 0;}
    public static int maxFlow(int[][]c,int s,int t){int n=c.length,r[][]=new int[n][n];for(int i=0;i<n;i++)for(int j=0;j<n;j++)r[i][j]=c[i][j];
        level=new int[n];ptr=new int[n];int f=0,x;while(bfs(r,s,t)){for(int i=0;i<n;i++)ptr[i]=0;while((x=send(r,s,t,Integer.MAX_VALUE))>0)f+=x;}return f;}
    public static void main(String[]x){int[][]c={{0,10,5,0,0,0},{0,0,4,8,0,0},{0,0,0,0,8,0},{0,0,0,0,3,10},{0,0,0,0,0,10},{0,0,0,0,0,0}};
        System.out.println("Dinic Maximum Flow = "+maxFlow(c,0,5));}
}
