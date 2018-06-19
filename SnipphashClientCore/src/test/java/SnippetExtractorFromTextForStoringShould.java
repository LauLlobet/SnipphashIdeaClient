import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SnippetExtractorFromTextForStoringShould {

    private SnippetExtractorFromTextForStoring extractor = new SnippetExtractorFromTextForStoring();

    @Test(expected= MissingTitleCommentHeaderAtBeginningOfSnippet.class)
    public void
    check_that_the_given_text_starts_with_a_comment() {
        extractor.extract("/ bad header title 20\n" +
                "/* \n" +
                "good formated body \n" +
                "*/\n" +
                "text to skip");
    }

    @Test
    public void
    extract_header() {
        Snippet extracted = extractor.extract("//code smells  list  10\n" +
                "/* \n" +
                "good formated body \n" +
                "*/\n" +
                "text to skip");
        assertThat(extracted.getTitle(),is("code list smells 10"));
    }

    @Test(expected = MissingVersionNumberWhenStoring.class)
    public void
    require_a_version_number_when_extracting_the_snippet() {
        extractor.extract("//code smells  list\n" +
                "/* \n" +
                "good formated body \n" +
                "*/\n" +
                "text to skip");
    }

    @Test(expected = BadFormattedSnippetBody.class)
    public void
    prevent_to_extract_if_snippet_text_is_bad_formatted() {
        extractor.extract("//code smells  list 10\n" +
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
        Snippet snippet = extractor.extract("//code smells  list 10\n" +
                "/*" + body + "*/\n" +
                "text to skip");

        assertThat(snippet.getBody(),is(body));
    }
}
