import java.io.*;
public class EditDistance {
    public static int distance(String a,String b){
        int m=a.length(), n=b.length(); int[][] d=new int[m+1][n+1];
        for(int i=0;i<=m;i++) d[i][0]=i;
        for(int j=0;j<=n;j++) d[0][j]=j;
        for(int i=1;i<=m;i++) for(int j=1;j<=n;j++){
            int c=a.charAt(i-1)==b.charAt(j-1)?0:1;
            d[i][j]=Math.min(d[i-1][j]+1,Math.min(d[i][j-1]+1,d[i-1][j-1]+c));
        }
        return d[m][n];
    }
    public static int bestLineDistance(String q,File f)throws IOException{
        BufferedReader br=new BufferedReader(new FileReader(f)); String s; int best=Integer.MAX_VALUE;
        while((s=br.readLine())!=null){ s=s.trim().toLowerCase(); if(s.length()==0)continue;
            best=Math.min(best,distance(q.toLowerCase(),s)); if(best==0)break; }
        br.close(); return best==Integer.MAX_VALUE?-1:best;
    }
    public static void main(String[] x)throws Exception{
        BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Query: "); String a=b.readLine();
        System.out.print("Target: "); String c=b.readLine();
        System.out.println("Edit Distance = "+distance(a.toLowerCase(),c.toLowerCase()));
        b.close();
    }
}
