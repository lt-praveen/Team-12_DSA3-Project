public class BitmaskTSP {
    private static int[][]d,memo;private static int n;
    private static int go(int mask,int p){if(mask==(1<<n)-1)return d[p][0];if(memo[mask][p]!=-1)return memo[mask][p];
        int ans=Integer.MAX_VALUE/4;for(int q=0;q<n;q++)if((mask&(1<<q))==0)ans=Math.min(ans,d[p][q]+go(mask|(1<<q),q));
        return memo[mask][p]=ans;}
    public static int minimumTour(int[][]a){d=a;n=a.length;memo=new int[1<<n][n];
        for(int i=0;i<memo.length;i++)for(int j=0;j<n;j++)memo[i][j]=-1;return go(1,0);}
    public static void main(String[]x){int[][]a={{0,10,15,20},{10,0,35,25},{15,35,0,30},{20,25,30,0}};
        System.out.println("Minimum TSP Tour Cost = "+minimumTour(a));}
}
