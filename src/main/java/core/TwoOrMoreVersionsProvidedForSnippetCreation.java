package core;

public class TwoOrMoreVersionsProvidedForSnippetCreation extends RuntimeException {
    TwoOrMoreVersionsProvidedForSnippetCreation(){
        super("You've typed two version numbers in the snippet title");
    }
}
