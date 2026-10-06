/** CO2 - Practical suffix-array search; construction is O(n log n) by doubling. */
public class SuffixArraySearch {
    private static int[] build(String s) {
        int n=s.length(); int[] sa=new int[n], rank=new int[n], tmp=new int[n];
        for(int i=0;i<n;i++){sa[i]=i;rank[i]=s.charAt(i);}
        for(int k=1;k<n;k*=2){ final int kk=k; for(int i=0;i<n;i++) tmp[i]=rank[i];
            // Simple insertion sort keeps this engine independent of java.util.*.
            for(int i=1;i<n;i++){int x=sa[i],j=i-1; while(j>=0 && compare(sa[j],x,rank,kk,n)>0){sa[j+1]=sa[j];j--;} sa[j+1]=x;}
            int r=0; tmp[sa[0]]=0; for(int i=1;i<n;i++){ if(compare(sa[i-1],sa[i],rank,kk,n)!=0) r++; tmp[sa[i]]=r; } for(int i=0;i<n;i++) rank[i]=tmp[i]; if(r==n-1) break;
        } return sa;
    }
    private static int compare(int a,int b,int[] rank,int k,int n){ if(rank[a]!=rank[b]) return rank[a]-rank[b]; int ra=a+k<n?rank[a+k]:-1, rb=b+k<n?rank[b+k]:-1; return ra-rb; }
    public static int count(String text,String pattern){ if(pattern.isEmpty()) return 0; int[] sa=build(text); int lo=0,hi=text.length(); while(lo<hi){int m=(lo+hi)/2; if(text.substring(sa[m]).compareTo(pattern)>=0) hi=m; else lo=m+1;} int first=lo; lo=0;hi=text.length(); String p2=pattern+"\uffff"; while(lo<hi){int m=(lo+hi)/2; String suf=text.substring(sa[m]); if(suf.compareTo(p2)<0) lo=m+1; else hi=m;} return Math.max(0,lo-first); }
}
