package LoginState;

public interface LoginStateAPI {

    String login(String userInput, String adminPasswordInput);

    public boolean administratorAuthentication(String adminPasswordInput);
}
