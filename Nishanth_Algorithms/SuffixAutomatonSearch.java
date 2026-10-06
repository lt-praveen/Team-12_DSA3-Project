/** CO2 - Suffix automaton. Occurrence counts are obtained from end-position frequencies. */
public class SuffixAutomatonSearch {
    private static class State { int[] next=new int[26]; int link=-1,len,occ; State(){for(int i=0;i<26;i++)next[i]=-1;} }
    public static int count(String text,String pattern){
        if(pattern.isEmpty())return 0;
        int cap=Math.max(2,2*text.length()+2); State[] st=new State[cap]; int size=1,last=0; st[0]=new State();
        for(char ch:text.toCharArray()){
            int c=ch-'a'; if(c<0||c>=26)continue; int cur=size++; st[cur]=new State(); st[cur].len=st[last].len+1; st[cur].occ=1; int p=last;
            while(p!=-1&&st[p].next[c]==-1){st[p].next[c]=cur;p=st[p].link;}
            if(p==-1) st[cur].link=0;
            else {int q=st[p].next[c]; if(st[p].len+1==st[q].len) st[cur].link=q; else {int clone=size++;st[clone]=new State();st[clone].len=st[p].len+1;st[clone].link=st[q].link;for(int k=0;k<26;k++)st[clone].next[k]=st[q].next[k];while(p!=-1&&st[p].next[c]==q){st[p].next[c]=clone;p=st[p].link;}st[q].link=st[cur].link=clone;}}
            last=cur;
        }
        int[] order=new int[size], cntLen=new int[text.length()+1];
        for(int i=0;i<size;i++)cntLen[st[i].len]++;
        for(int i=1;i<cntLen.length;i++)cntLen[i]+=cntLen[i-1];
        for(int i=size-1;i>=0;i--)order[--cntLen[st[i].len]]=i;
        for(int i=size-1;i>0;i--){int v=order[i]; if(st[v].link>=0)st[st[v].link].occ+=st[v].occ;}
        int u=0;for(char ch:pattern.toCharArray()){int c=ch-'a';if(c<0||c>=26||st[u].next[c]<0)return 0;u=st[u].next[c];}return st[u].occ;
    }
}
