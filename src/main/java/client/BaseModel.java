package client;

public enum BaseModel {

  DAVINCI("text-davinci-003", "Davinci - Most powerful (Default)"),
  CHATGPT("gpt-3.5-turbo", "ChatGPT - Most recent and capable model (Default)");

  private final String model;
  private final String description;

  BaseModel(String model, String description) {
    this.model = model;
    this.description = description;
  }

  public String getModel() {
    return model;
  }

  public String getDescription() {
    return description;
  }
}
