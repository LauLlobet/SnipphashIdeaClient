import core.SnippetTitle;
import extractor.MissingTitleCommentHeaderAtBeginningOfSnippet;
import extractor.SnippetDTO;
import extractor.SnippetTitleExtractor;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SnippetTitleExtractorShould {

    SnippetTitleExtractor snippetTitleExtractor = new SnippetTitleExtractor();

    @Test(expected= MissingTitleCommentHeaderAtBeginningOfSnippet.class)
    public void
    check_that_the_given_text_starts_with_a_comment_slashes() {
        snippetTitleExtractor.getTitleFrom("/ bad header title 20\n" +
                "text to skip");
    }

    @Test
    public void
    extract_header() {
        SnippetTitle extracted = snippetTitleExtractor.getTitleFrom("//code smells  list  10\n" +
                "text to skip");
        assertThat(extracted.toString(),is("code list smells 10"));
    }

}
