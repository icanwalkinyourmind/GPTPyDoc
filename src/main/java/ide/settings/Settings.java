package ide.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Settings {
    public String url = "https://api.openai.com/v1/completions";
    public String apiKey = "";

    public static Settings getInstance() {
        return ApplicationManager.getApplication().getService(Settings.class);
    }

    @Nullable
    public Settings getState() {
        return this;
    }

    public void loadState(@NotNull Settings state) {
        XmlSerializerUtil.copyBean(state, this);
    }
}
