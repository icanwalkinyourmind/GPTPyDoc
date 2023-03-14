package client;

import client.api.chat.ChatCompletionClient;
import client.api.text.TextCompletionClient;
import ide.settings.SettingsState;

public class ClientFactory {

  public Client getClient() {
    if (SettingsState.getInstance().isChatCompletionOptionSelected) {
      return ChatCompletionClient.getInstance();
    }
    return TextCompletionClient.getInstance();
  }
}
