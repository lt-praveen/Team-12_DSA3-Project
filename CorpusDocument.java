import java.nio.file.*;
import java.io.*;

/** One legal-corpus document loaded by the main application. */
public class CorpusDocument {
    public final String name;
    public final String text;
    public CorpusDocument(String name,String text){this.name=name;this.text=text.toLowerCase();}
}
