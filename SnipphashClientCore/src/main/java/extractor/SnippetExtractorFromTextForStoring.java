package extractor;
import core.*;
public class SnippetExtractorFromTextForStoring {

    private SnippetTitleExtractor snippetTitleExtractor = new SnippetTitleExtractor();

    public SnippetDTO extract(String text) {
        checkIfBodyIsWellFormated(text);
        String body = extractBodyFrom(text);
        return new SnippetDTO(snippetTitleExtractor.getTitleFrom(text), body);
    }

    private void checkIfBodyIsWellFormated(String text) {
        if (!extractCommentedSnippetBodyFrom(text).startsWith("/*")) {
            throw new BadFormattedSnippetBody();
        }
    }

    private String extractBodyFrom(String text) {
        String commentedBody = extractCommentedSnippetBodyFrom(text);
        return commentedBody.substring(2, commentedBody.length() - 2);
    }

    private String extractCommentedSnippetBodyFrom(String text) {
        try {
            String secondLineToEndOfText = text.substring(text.indexOf('\n') + 1);
            String secondLineToEndOfBodyIncludingCommentNotation = secondLineToEndOfText.substring(0, secondLineToEndOfText.indexOf("*/")+2);
            return secondLineToEndOfBodyIncludingCommentNotation;
        } catch (Exception e) {
            throw new BadFormattedSnippetBody();
        }
    }

}
