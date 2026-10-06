/** CO3 algorithms used by the project: text similarity plus advanced DP demonstrations. */
public class DPAlgorithms {
    public static int editDistance(String a,String b){int[][] d=new int[a.length()+1][b.length()+1];for(int i=0;i<=a.length();i++)d[i][0]=i;for(int j=0;j<=b.length();j++)d[0][j]=j;for(int i=1;i<=a.length();i++)for(int j=1;j<=b.length();j++)d[i][j]=Math.min(Math.min(d[i-1][j]+1,d[i][j-1]+1),d[i-1][j-1]+(a.charAt(i-1)==b.charAt(j-1)?0:1));return d[a.length()][b.length()];}
    public static int lcs(String a,String b){int[][] d=new int[a.length()+1][b.length()+1];for(int i=1;i<=a.length();i++)for(int j=1;j<=b.length();j++)d[i][j]=a.charAt(i-1)==b.charAt(j-1)?d[i-1][j-1]+1:Math.max(d[i-1][j],d[i][j-1]);return d[a.length()][b.length()];}
    public static int needlemanWunsch(String a,String b){return align(a,b,2,-1,-2,false);}
    public static int smithWaterman(String a,String b){return align(a,b,2,-1,-2,true);}
    private static int align(String a,String b,int match,int mismatch,int gap,boolean local){int[][] d=new int[a.length()+1][b.length()+1];int best=0;for(int i=1;i<=a.length();i++){d[i][0]=local?0:d[i-1][0]+gap;}for(int j=1;j<=b.length();j++){d[0][j]=local?0:d[0][j-1]+gap;}for(int i=1;i<=a.length();i++)for(int j=1;j<=b.length();j++){int x=d[i-1][j-1]+(a.charAt(i-1)==b.charAt(j-1)?match:mismatch);int y=d[i-1][j]+gap,z=d[i][j-1]+gap;d[i][j]=local?Math.max(0,Math.max(x,Math.max(y,z))):Math.max(x,Math.max(y,z));if(d[i][j]>best)best=d[i][j];}return local?best:d[a.length()][b.length()];}
    // Interval DP: minimum scalar multiplications for a chain of matrices.
    public static long matrixChain(int[] p){int n=p.length-1;if(n<=1)return 0;long[][] d=new long[n][n];for(int len=2;len<=n;len++)for(int i=0;i+len<=n;i++){int j=i+len-1;d[i][j]=Long.MAX_VALUE/4;for(int k=i;k<j;k++){long v=d[i][k]+d[k+1][j]+(long)p[i]*p[k+1]*p[j+1];if(v<d[i][j])d[i][j]=v;}}return d[0][n-1];}
    // Bitmask DP: TSP over a small set of issue categories.
    public static int tsp(int[][] w){int n=w.length,full=1<<n;int[][] d=new int[full][n];for(int m=0;m<full;m++)for(int i=0;i<n;i++)d[m][i]=1_000_000;d[1][0]=0;for(int m=1;m<full;m++)for(int u=0;u<n;u++)if((m&(1<<u))!=0&&d[m][u]<1_000_000)for(int v=0;v<n;v++)if((m&(1<<v))==0)d[m|1<<v][v]=Math.min(d[m|1<<v][v],d[m][u]+w[u][v]);int ans=1_000_000;for(int u=1;u<n;u++)ans=Math.min(ans,d[full-1][u]+w[u][0]);return ans;}
    // DP on a tree: maximum number of selected nodes with no parent-child pair selected.
    public static int treeIndependentSet(int[][] children,int root){return dfs(children,root)[0];}
    private static int[] dfs(int[][] c,int u){int take=1,skip=0;for(int v:c[u]){int[] r=dfs(c,v);take+=r[1];skip+=Math.max(r[0],r[1]);}return new int[]{take,skip};}
    // SOS DP: for each subset, count how many corpus issue masks contain it.
    public static int[] sosCounts(int[] masks,int bits){int size=1<<bits; int[] dp=new int[size];for(int m:masks)dp[m]++;for(int b=0;b<bits;b++)for(int m=0;m<size;m++)if((m&(1<<b))==0)dp[m]+=dp[m|(1<<b)];return dp;}
}
