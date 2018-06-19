package extractor;

public class MissingVersionNumberWhenStoring extends RuntimeException {
    public MissingVersionNumberWhenStoring(){
        super("You are trying to save an snippet without providing a version number for it.");
    }
}
