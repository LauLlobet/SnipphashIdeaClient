package extractor;

public class MissingTitleCommentHeaderAtBeginningOfSnippet extends RuntimeException{
    public MissingTitleCommentHeaderAtBeginningOfSnippet(){
        super("You are trying to set a title of a snippet without following the correct format:\n" +
                "// yourtitle keywords ...\n"+
                "/* your snippet body  */");
    }
}
