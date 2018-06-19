package core;

import extractor.MissingVersionNumberWhenStoring;

public class StorableSnippetTitle extends SnippetTitle {
    public StorableSnippetTitle(String titleString) {
        super(titleString);
        if(isVersionZero()){
            throw new MissingVersionNumberWhenStoring();
        }
    }
}
