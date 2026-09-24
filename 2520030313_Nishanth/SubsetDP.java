import java.io.*;
public class SubsetDP {
    public static boolean subsetSum(int[]a,int target){if(target<0)return false;boolean[]d=new boolean[target+1];d[0]=true;
        for(int x:a)for(int s=target;s>=x;s--)if(d[s-x])d[s]=true;return d[target];}
    public static void main(String[]x)throws Exception{BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Number of items: ");int n=Integer.parseInt(b.readLine());int[]a=new int[n];
        for(int i=0;i<n;i++){System.out.print("Value "+(i+1)+": ");a[i]=Integer.parseInt(b.readLine());}
        System.out.print("Target: ");int t=Integer.parseInt(b.readLine());System.out.println("Subset exists = "+subsetSum(a,t));b.close();}
}
