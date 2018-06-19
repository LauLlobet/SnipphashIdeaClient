package extractor;
import core.*;

public class SnippetDTO {
    private SnippetTitle title;
    private String body;

    @Override
    public String toString() {
        return "SnippetDTO{" +
                "title=" + title +
                ", body='" + body + '\'' +
                '}';
    }

    public SnippetDTO(SnippetTitle title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title.toString();
    }

    public String getBody() {
        return body;
    }
}
