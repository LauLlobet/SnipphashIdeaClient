public class SnippetExtractorFromTextForStoring {
    public Snippet extract(String text) {
        checkIfWellFormated(text);
        String titleString = extractSnippetTitleFrom(text);
        String body = extractBodyFrom(text);
        return new Snippet(new SnippetTitleiToStore(titleString), body);
    }

    private String extractBodyFrom(String text) {
        String commentedBody = extractCommentedSnippetBodyFrom(text);
        return commentedBody.substring(2, commentedBody.length() - 2);
    }

    private String extractSnippetTitleFrom(String text) {
        return fromThirdCharacterToFirstEndOfLineOf(text);
    }

    private void checkIfWellFormated(String text) {
        if (!twoFirstCharactersOf(text).equals("//")) {
            throw new MissingTitleCommentHeaderAtBeginningOfSnippet();
        }
        if (!extractCommentedSnippetBodyFrom(text).startsWith("/*")) {
            throw new BadFormattedSnippetBody();
        }
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

    private String twoFirstCharactersOf(String text) {
        return text.substring(0, 2);
    }

    private String fromThirdCharacterToFirstEndOfLineOf(String text) {
        return text.substring(2, text.indexOf('\n'));
    }
}
