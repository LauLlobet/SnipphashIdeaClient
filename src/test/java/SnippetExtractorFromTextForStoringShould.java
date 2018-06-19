import extractor.*;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SnippetExtractorFromTextForStoringShould {

    private SnippetExtractor snippetExtractorFromTextForStoring = new SnippetExtractor();

    @Test(expected= MissingTitleCommentHeaderAtBeginningOfSnippet.class)
    public void
    check_that_the_given_text_starts_with_a_comment() {
        snippetExtractorFromTextForStoring.extract("/ bad header title 20\n" +
                "/* \n" +
                "good formated body \n" +
                "*/\n" +
                "text to skip");
    }

    @Test
    public void
    extract_header() {
        SnippetDTO extracted = snippetExtractorFromTextForStoring.extract("//code smells  list  10\n" +
                "/* \n" +
                "good formated body \n" +
                "*/\n" +
                "text to skip");
        assertThat(extracted.getTitle(),is("code list smells 10"));
    }

    @Test(expected = MissingVersionNumberWhenStoring.class)
    public void
    require_a_version_number_when_extracting_the_snippet() {
        snippetExtractorFromTextForStoring.extract("//code smells  list\n" +
                "/* \n" +
                "good formated body \n" +
                "*/\n" +
                "text to skip");
    }

    @Test(expected = BadFormattedSnippetBody.class)
    public void
    prevent_to_extract_if_snippet_text_is_bad_formatted() {
        snippetExtractorFromTextForStoring.extract("//code smells  list 10\n" +
                " erroneous line \n"+
                "/* \n" +
                "good formated body \n" +
                "*/\n" +
                "text to skip");
    }

    @Test
    public void
    extract_body_of_snippet() {
        String body = " tip1 plus \n" +
                "tip2 plus" +
                "tip3 ";
        SnippetDTO snippet = snippetExtractorFromTextForStoring.extract("//code smells  list 10\n" +
                "/*" + body + "*/\n" +
                "text to skip");

        assertThat(snippet.getBody(),is(body));
    }
}
