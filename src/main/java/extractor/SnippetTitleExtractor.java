package extractor;

import core.SnippetTitle;
import core.StorableSnippetTitle;

public class SnippetTitleExtractor {

    public StorableSnippetTitle getStorableTitleFrom(String text) {
       return new StorableSnippetTitle(extractSnippetTitleFrom(text));
    }

    public SnippetTitle getTitleFrom(String text){
        return new SnippetTitle(extractSnippetTitleFrom(text));
    }

    String extractSnippetTitleFrom(String text) {
        checkIfWellFormated(text);
        return fromThirdCharacterToFirstEndOfLineOf(text);
    }

    String fromThirdCharacterToFirstEndOfLineOf(String text) {
        return text.substring(2, text.indexOf('\n'));
    }
    String twoFirstCharactersOf(String text) {
        return text.substring(0, 2);
    }

    void checkIfWellFormated(String text) {
        if (!twoFirstCharactersOf(text).equals("//")) {
            throw new MissingTitleCommentHeaderAtBeginningOfSnippet();
        }
    }

}
