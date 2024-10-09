package LoginState;

public interface LoginStateAPI {

    String login(String userInput);

    String login(String userInput, String adminPasswordInput);

    boolean administratorAuthentication(String adminPasswordInput);
}
