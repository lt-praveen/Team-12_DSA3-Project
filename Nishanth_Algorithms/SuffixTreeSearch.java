/** CO2 - Suffix-tree preview: builds a query-length suffix trie for the requested pattern. */
public class SuffixTreeSearch {
    private static class Node { int[] next=new int[26]; int count; Node(){for(int i=0;i<26;i++)next[i]=-1;} }
    public static int count(String text,String pattern){
        if(pattern.isEmpty())return 0; int max=1+text.length()*Math.min(pattern.length(),32); Node[] t=new Node[Math.max(2,max)];int size=1;t[0]=new Node();
        for(int i=0;i<text.length();i++){int u=0;for(int j=i;j<text.length()&&j<i+pattern.length();j++){int c=text.charAt(j)-'a';if(c<0||c>=26)break;if(t[u].next[c]<0){if(size>=t.length)break;t[u].next[c]=size;t[size++]=new Node();}u=t[u].next[c];if(j-i+1==pattern.length())t[u].count++;}}
        int u=0;for(char ch:pattern.toCharArray()){int c=ch-'a';if(c<0||c>=26||t[u].next[c]<0)return 0;u=t[u].next[c];}return t[u].count;
    }
}
