package client.api.text;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import client.api.CompletionClientEventListener;
import client.api.text.response.ApiResponse;
import java.util.function.Consumer;
import okhttp3.OkHttpClient;

public class TextCompletionClientEventListener extends CompletionClientEventListener {

  public TextCompletionClientEventListener(OkHttpClient client, Consumer<String> onMessageReceived, Consumer<String> onComplete) {
    super(client, onMessageReceived, onComplete);
  }

  protected String getMessage(String data) throws JsonProcessingException {
    return new ObjectMapper()
        .readValue(data, ApiResponse.class)
        .getChoices()
        .get(0)
        .getText();
  }
}
