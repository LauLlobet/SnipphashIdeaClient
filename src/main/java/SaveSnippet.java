import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import extractor.*;

public class SaveSnippet extends AnAction {
    public SaveSnippet() {
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
        String text = document.getText().substring(start);
        SnippetExtractor snippetExtractor = new SnippetExtractor();
        try {
            SnippetDTO snippetDTO = snippetExtractor.extract(text);
            Messages.showInfoMessage(snippetDTO.toString(),"Message");
            WriteCommandAction.runWriteCommandAction(project, () ->
                    document.replaceString(start, start, "Saved !\n")
            );
            selectionModel.removeSelection();
        }catch (Exception exception){
            Messages.showInfoMessage(exception.getMessage(),"Message");
        }

    }
}