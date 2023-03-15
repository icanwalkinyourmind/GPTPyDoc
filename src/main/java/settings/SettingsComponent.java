package settings;

import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class SettingsComponent {

    private final JPanel mainPanel;

    private final JBTextField apiKeyField;

    public SettingsComponent(Settings settings) {
        apiKeyField = new JBTextField(settings.apiKey, 1);

        mainPanel = FormBuilder.createFormBuilder()
                .getPanel();
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    public JComponent getPreferredFocusedComponent() {
        return apiKeyField;
    }

    @NotNull
    public String getApiKey() {
        return apiKeyField.getText();
    }

    public void setApiKey(@NotNull String apiKey) {
        apiKeyField.setText(apiKey);
    }
}
