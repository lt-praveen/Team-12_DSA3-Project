import java.io.*;

/**
 * SINGLE ENTRY POINT for the complete DSA-3 project.
 * All algorithm classes are libraries; only this file contains main().
 */
public class Main {
    public static void main(String[] args) throws Exception {
        CorpusDocument[] docs=CorpusLoader.load("corpus");
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("============================================================");
        System.out.println("      LEGAL DOCUMENT DSA-3 ANALYTICS ENGINE");
        System.out.println("============================================================");
        System.out.println("Loaded corpus : "+docs.length+" documents");
        while(true){
            System.out.println("\nMAIN MENU");
            System.out.println("1. CO2 - Pattern search across corpus");
            System.out.println("2. CO3 - Dynamic-programming document analysis");
            System.out.println("3. CO4 - Network-flow corpus analysis");
            System.out.println("4. Run CO2 + CO3 + CO4 demonstration");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            String choice=br.readLine(); if(choice==null)break;
            if(choice.equals("1")){
                System.out.print("Enter pattern to search: ");String p=br.readLine();if(p!=null&&!p.trim().isEmpty())CO2SearchEngine.run(docs,p);else System.out.println("Pattern cannot be empty.");
            }else if(choice.equals("2")){
                System.out.println("Available documents: "+docs.length);
                System.out.print("Enter first document name (e.g., case001.txt): ");String a=br.readLine();
                System.out.print("Enter second document name (e.g., case002.txt): ");String b=br.readLine();
                CO3Engine.run(docs,a,b);
            }else if(choice.equals("3"))CO4Engine.run(docs);
            else if(choice.equals("4")){
                CO2SearchEngine.run(docs,"property");
                if(docs.length>=2)CO3Engine.run(docs,docs[0].name,docs[1].name);
                CO4Engine.run(docs);
            }else if(choice.equals("0")){System.out.println("Exiting. Thank you.");break;}
            else System.out.println("Invalid option. Choose 0-4.");
        }
    }
}
