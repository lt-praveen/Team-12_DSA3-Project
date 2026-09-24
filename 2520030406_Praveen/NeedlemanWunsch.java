import java.io.*;
public class NeedlemanWunsch {
    public static int score(String a,String b){
        int m=a.length(),n=b.length(),match=2,mismatch=-1,gap=-2;
        int[][] d=new int[m+1][n+1];
        for(int i=1;i<=m;i++)d[i][0]=i*gap;
        for(int j=1;j<=n;j++)d[0][j]=j*gap;
        for(int i=1;i<=m;i++)for(int j=1;j<=n;j++){
            int diag=d[i-1][j-1]+(a.charAt(i-1)==b.charAt(j-1)?match:mismatch);
            d[i][j]=Math.max(diag,Math.max(d[i-1][j]+gap,d[i][j-1]+gap));
        }
        return d[m][n];
    }
    public static void main(String[] x)throws Exception{
        BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
        System.out.print("First text: ");String a=b.readLine();
        System.out.print("Second text: ");String c=b.readLine();
        System.out.println("Global Alignment Score = "+score(a.toLowerCase(),c.toLowerCase()));b.close();
    }
}
