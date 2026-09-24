public class IntervalDP {
    public static long matrixChain(int[]p){int n=p.length-1;long[][]d=new long[n][n];
        for(int len=2;len<=n;len++)for(int i=0;i+len-1<n;i++){int j=i+len-1;d[i][j]=Long.MAX_VALUE;
            for(int k=i;k<j;k++){long x=d[i][k]+d[k+1][j]+(long)p[i]*p[k+1]*p[j+1];if(x<d[i][j])d[i][j]=x;}}
        return d[0][n-1];}
    public static void main(String[]x){System.out.println("Interval DP Matrix-Chain Cost = "+matrixChain(new int[]{10,20,30,40,30}));}
}
