/** CO2 - Aho-Corasick: one automaton searches many patterns in one pass. */
public class AhoCorasickSearch {
    private static class Node { int[] next=new int[26]; int fail,mask; Node(){for(int i=0;i<26;i++)next[i]=-1;} }
    public static int[] countAll(String text,String[] patterns){
        int max=1; for(String p:patterns) max+=p.length(); Node[] t=new Node[max]; int size=1; t[0]=new Node();
        for(int k=0;k<patterns.length;k++){int u=0;for(char ch:patterns[k].toCharArray()){int c=ch-'a';if(c<0||c>=26)continue;if(t[u].next[c]<0){t[u].next[c]=size;t[size++]=new Node();}u=t[u].next[c];}t[u].mask|=1<<k;}
        int[] q=new int[size];int front=0,back=0;for(int c=0;c<26;c++){int v=t[0].next[c];if(v<0)t[0].next[c]=0;else{t[v].fail=0;q[back++]=v;}}
        while(front<back){int u=q[front++];t[u].mask|=t[t[u].fail].mask;for(int c=0;c<26;c++){int v=t[u].next[c];if(v<0)t[u].next[c]=t[t[u].fail].next[c];else{t[v].fail=t[t[u].fail].next[c];q[back++]=v;}}}
        int[] ans=new int[patterns.length];int u=0;for(char ch:text.toCharArray()){if(ch<'a'||ch>'z'){u=0;continue;}u=t[u].next[ch-'a'];int mask=t[u].mask;for(int k=0;k<patterns.length;k++)if((mask&(1<<k))!=0)ans[k]++;}return ans;
    }
    public static int count(String text,String pattern){return countAll(text,new String[]{pattern})[0];}
}
