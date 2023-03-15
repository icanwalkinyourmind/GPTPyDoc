package settings;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;


public class SettingsConfigurable implements Configurable {

    private SettingsComponent settingsComponent;
    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "GPTPyDoc: Settings";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return settingsComponent.getPreferredFocusedComponent();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        var settings = Settings.getInstance();
        settingsComponent = new SettingsComponent(settings);
        return settingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        var settings = Settings.getInstance();
        return !settingsComponent.getApiKey().equals(settings.apiKey);
    }

    @Override
    public void apply() {
        var settings = Settings.getInstance();
        settings.apiKey = settingsComponent.getApiKey();
    }

    @Override
    public void reset() {
        var settings = Settings.getInstance();
        settingsComponent.setApiKey(settings.apiKey);
    }

    @Override
    public void disposeUIResources() {
        settingsComponent = null;
    }
}
