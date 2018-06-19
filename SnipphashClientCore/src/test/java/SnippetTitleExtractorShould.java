import extractor.MissingTitleCommentHeaderAtBeginningOfSnippet;
import extractor.SnippetTitleExtractorForQuering;
import org.junit.Test;

public class SnippetTitleExtractorShould {

    SnippetTitleExtractorForQuering snippetTitleExtractor = new SnippetTitleExtractorForQuering();

    @Test(expected= MissingTitleCommentHeaderAtBeginningOfSnippet.class)
    public void
    check_that_the_given_text_starts_with_a_comment_slashes() {
        snippetTitleExtractor.extract("/ bad header title 20\n" +
                "text to skip");
    }
}
