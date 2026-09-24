import java.io.*;
public class DPComparison {
    private static String firstLine(File f)throws IOException{BufferedReader b=new BufferedReader(new FileReader(f));String s;
        while((s=b.readLine())!=null){s=s.trim();if(s.length()>0){b.close();return s.toLowerCase();}}b.close();return "";}
    public static void main(String[]x)throws Exception{
        BufferedReader b=new BufferedReader(new InputStreamReader(System.in));System.out.print("Enter legal query: ");String q=b.readLine().toLowerCase();
        File folder=new File("Corpus");if(!folder.isDirectory()){System.out.println("Corpus folder not found. Run from project root.");b.close();return;}
        File[]fs=folder.listFiles();System.out.println("\nCO3 DYNAMIC PROGRAMMING RESULTS\n");
        for(File f:fs)if(f.isFile()&&f.getName().startsWith("case")&&f.getName().endsWith(".txt")){
            String s=firstLine(f);if(s.length()==0)continue;if(s.length()>250)s=s.substring(0,250);
            System.out.println(f.getName()+" | Edit="+EditDistance.distance(q,s)+" | Global="+NeedlemanWunsch.score(q,s)+" | Local="+SmithWaterman.score(q,s));
        }
        System.out.println("\nInterval DP = "+IntervalDP.matrixChain(new int[]{10,20,30,40,30}));
        System.out.println("Bitmask TSP = "+BitmaskTSP.minimumTour(new int[][]{{0,10,15,20},{10,0,35,25},{15,35,0,30},{20,25,30,0}}));
        System.out.println("Subset DP = "+SubsetDP.subsetSum(new int[]{3,5,7,10},15));b.close();
    }
}
