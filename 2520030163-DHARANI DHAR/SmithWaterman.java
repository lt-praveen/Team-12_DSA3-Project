import java.io.*;
public class SmithWaterman {
    public static int score(String a,String b){
        int m=a.length(),n=b.length(),best=0,match=2,mis=-1,gap=-2;int[][]d=new int[m+1][n+1];
        for(int i=1;i<=m;i++)for(int j=1;j<=n;j++){
            int x=d[i-1][j-1]+(a.charAt(i-1)==b.charAt(j-1)?match:mis);
            d[i][j]=Math.max(0,Math.max(x,Math.max(d[i-1][j]+gap,d[i][j-1]+gap)));best=Math.max(best,d[i][j]);}
        return best;
    }
    public static void main(String[]x)throws Exception{BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
        System.out.print("First text: ");String a=b.readLine();System.out.print("Second text: ");String c=b.readLine();
        System.out.println("Local Alignment Score = "+score(a.toLowerCase(),c.toLowerCase()));b.close();}
}
