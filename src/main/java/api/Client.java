package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import settings.Settings;

public class Client {
    public String get_answer(String prompt) throws Exception {
        HttpURLConnection con = (HttpURLConnection) new URL(Settings.getInstance().url).openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + Settings.getInstance().apiKey);

        JSONObject data = new JSONObject();
        data.put("model", "text-davinci-003");
        data.put("prompt", prompt);
        data.put("max_tokens", 4000);
        data.put("temperature", 1.0);

        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
                .reduce((a, b) -> a + b).get();

        return new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text");
    }
}