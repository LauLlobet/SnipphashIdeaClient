public class SnippetTitleiToStore extends SnippetTitle {
    public SnippetTitleiToStore(String titleString) {
        super(titleString);
        if(isVersionZero()){
            throw new MissingVersionNumberWhenStoring();
        }
    }
}
