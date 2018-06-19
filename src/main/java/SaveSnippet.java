import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

public class SaveSnippet extends AnAction {
    public SaveSnippet() {
        super("Hello");
    }

    public void update(AnActionEvent e) {
        //Get required data keys
        final Project project = e.getProject();
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        //Set visibility only in case of existing project and editor and if some text in the editor is selected
        e.getPresentation().setVisible(project != null && editor != null &&
                editor.getSelectionModel().hasSelection());
    }


    public void actionPerformed(AnActionEvent e) {
        //Get all the required data from data keys
        final Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        final Project project = e.getProject();
        //Access document, caret, and selection
        final Document document = editor.getDocument();
        final SelectionModel selectionModel = editor.getSelectionModel();
        selectionModel.selectLineAtCaret();

        final int start = selectionModel.getSelectionStart();
        final int end = selectionModel.getSelectionEnd();

        String snippet = document.getText().substring(start);
        snippet = snippet.substring(0,snippet.indexOf("*/"));
        if(! snippet.contains())


        Messages.showInfoMessage(snippet,"Message");
        /*

        final int start = selectionModel.getSelectionStart();
        final int end = selectionModel.getSelectionEnd();
        //Making the replacement
        WriteCommandAction.runWriteCommandAction(project, () ->
                document.replaceString(start, end, "SNIPPET  \n line2 ")
        );*/
        selectionModel.removeSelection();
    }
}