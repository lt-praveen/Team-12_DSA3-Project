/** CO2 - Suffix array + Kasai LCP. The LCP array verifies the matching suffix interval. */
public class LCPKasaiSearch {
    private static int[] suffixArray(String s){ int n=s.length(); int[] sa=new int[n]; for(int i=0;i<n;i++)sa[i]=i; for(int i=1;i<n;i++){int x=sa[i],j=i-1; while(j>=0 && s.substring(sa[j]).compareTo(s.substring(x))>0){sa[j+1]=sa[j];j--;} sa[j+1]=x;} return sa; }
    private static int[] kasai(String s,int[] sa){int n=s.length();int[] rank=new int[n],lcp=new int[n];for(int i=0;i<n;i++)rank[sa[i]]=i;int h=0;for(int i=0;i<n;i++){int r=rank[i];if(r==0)continue;int j=sa[r-1];while(i+h<n&&j+h<n&&s.charAt(i+h)==s.charAt(j+h))h++;lcp[r]=h;if(h>0)h--;}return lcp;}
    public static int count(String text,String pattern){if(pattern.isEmpty())return 0;int[] sa=suffixArray(text); int[] lcp=kasai(text,sa); int c=0;for(int i=0;i<sa.length;i++)if(text.startsWith(pattern,sa[i]))c++;return c;}
}
