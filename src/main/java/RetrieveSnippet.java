import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import core.SnippetTitle;
import extractor.SnippetTitleExtractor;

public class RetrieveSnippet extends AnAction {
    public RetrieveSnippet() {
        super("Hello");
    }

    public void update(AnActionEvent e) {
        final Project project = e.getProject();
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        e.getPresentation().setVisible(project != null && editor != null &&
                editor.getSelectionModel().hasSelection());
    }


    public void actionPerformed(AnActionEvent e) {
            final Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
            final Project project = e.getProject();
            final Document document = editor.getDocument();
            final SelectionModel selectionModel = editor.getSelectionModel();
            selectionModel.selectLineAtCaret();

            final int start = selectionModel.getSelectionStart();

        try {
            String text = document.getText().substring(start);
            SnippetTitleExtractor snippetExtractor = new SnippetTitleExtractor();
            SnippetTitle title = snippetExtractor.getTitleFrom(text);
            selectionModel.removeSelection();
            Messages.showInfoMessage(title.toString(),"Message");
        }catch (Exception exception){
            Messages.showInfoMessage(exception.getMessage(),"Message");
        }
    }
}