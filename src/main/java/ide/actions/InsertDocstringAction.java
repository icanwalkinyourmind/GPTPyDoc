package ide.actions;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.*;
import com.intellij.psi.util.*;
import com.jetbrains.python.psi.PyClass;
import com.jetbrains.python.psi.PyFunction;
import com.intellij.psi.PsiElement;
import com.jetbrains.python.psi.StructuredDocString;
import com.intellij.openapi.command.WriteCommandAction;
import org.jetbrains.annotations.*;

public class InsertDocstringAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        // Get the current editor and project
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        final Project project = e.getData(CommonDataKeys.PROJECT);
        if (editor == null || project == null) {
            return;
        }

        // Get the selected method
        PsiElement element = PsiUtilBase.getElementAtCaret(editor);
        PyFunction method = PsiTreeUtil.getParentOfType(element, PyFunction.class);
        if (method == null) {
            return;
        }

        // Generate the new docstring
        String docstring = "/**\n" +
                " * Test method for " + method.getName() + "\n" +
                " */";

        // Add the docstring to the method
        //WriteCommandAction.runWriteCommandAction(project, () -> {
        //    PsiComment comment = PsiElementFactory.getInstance(project).createCommentFromText(docstring, method);
        //    method.addBefore(comment, method.getFirstChild());
       // });
    }

}
