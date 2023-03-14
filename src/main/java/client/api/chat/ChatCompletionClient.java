package client.api.chat;

import client.ApiRequestDetails;
import client.Client;
import client.api.chat.request.ApiRequest;
import client.api.chat.request.ApiRequestMessage;
import ide.settings.SettingsState;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import okhttp3.sse.EventSourceListener;

public class ChatCompletionClient extends Client {

  private static final List<Map.Entry<String, String>> queries = new ArrayList<>();
  private static ChatCompletionClient instance;

  private ChatCompletionClient() {
  }

  public static ChatCompletionClient getInstance() {
    if (instance == null) {
      instance = new ChatCompletionClient();
    }
    return instance;
  }

  public void clearPreviousSession() {
    queries.clear();
  }

  /**
   * Returns the details of an API request for completing a chat prompt using OpenAI's API.
   *
   * @param prompt the prompt for the chat completion request
   * @return an {@code ApiRequestDetails} object that contains the URL, request data, and API key for the chat completion request
   */
  protected ApiRequestDetails getRequestDetails(String prompt) {
    var messages = new ArrayList<ApiRequestMessage>();
    messages.add(new ApiRequestMessage(
        "system",
        "Answer as concisely as possible."));
    queries.forEach(query -> {
      messages.add(new ApiRequestMessage("user", query.getKey()));
      messages.add(new ApiRequestMessage("assistant", query.getValue()));
    });
    messages.add(new ApiRequestMessage("user", prompt));

    return new ApiRequestDetails(
        "https://api.openai.com/v1/chat/completions",
        new ApiRequest(
            SettingsState.getInstance().chatCompletionBaseModel.getModel(),
            true,
            messages
        ),
        SettingsState.getInstance().apiKey);
  }

  protected EventSourceListener getEventSourceListener(Consumer<String> onMessageReceived, Runnable onComplete) {
    return new ChatCompletionClientEventListener(client, onMessageReceived, finalMessage -> {
      queries.add(Map.entry(prompt, finalMessage));
      onComplete.run();
    });
  }
}
