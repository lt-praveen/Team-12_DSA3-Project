import java.nio.file.*;
import java.io.*;

/** Loads every .txt file from the corpus directory. */
public class CorpusLoader {
    public static CorpusDocument[] load(String folder) throws IOException {
        File dir=new File(folder); File[] files=dir.listFiles();
        if(files==null) throw new IOException("Corpus folder not found: "+folder);
        int count=0; for(File f:files) if(f.isFile()&&f.getName().toLowerCase().endsWith(".txt"))count++;
        CorpusDocument[] docs=new CorpusDocument[count]; int k=0;
        for(int i=0;i<files.length;i++) for(int j=i+1;j<files.length;j++) if(files[i].getName().compareToIgnoreCase(files[j].getName())>0){File z=files[i];files[i]=files[j];files[j]=z;}
        for(File f:files) if(f.isFile()&&f.getName().toLowerCase().endsWith(".txt")){
            docs[k++]=new CorpusDocument(f.getName(),Files.readString(f.toPath()));
        }
        return docs;
    }
}
