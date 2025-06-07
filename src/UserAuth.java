public class UserAuth {

    public boolean authenticate(String username, String password) {
        // narazie tak
        return username.equals("admin") && password.equals("admin");
    }
}