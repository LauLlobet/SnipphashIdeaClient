package extractor;

public class BadFormattedSnippetBody extends RuntimeException {
    BadFormattedSnippetBody(){
        super("You are trying to save a snippet with a body bad formatted, the format shpold be:\n"+
                "// yourtitle keywords ...\n"+
                "/* your snippet body  */");
    }
}
