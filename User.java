import javax.swing.*;

/**
 * User Class
 * @Author Michael Tuskan and Ian Liston
 * @Version 2.0
 */
public class User {
    private String username;
    private JPasswordField password;
    private boolean newUser;

    User(String userName, JPasswordField password,boolean newUser){
        this.username = userName;
        this.password = password;
        this.newUser = newUser;
    }
    /**
     * @Author Ian Liston
     */
    public void verifySetUser(String userName, JPasswordField password, JTextPane alertPane){
        if(userAndPasswordMatch(userName,password)){
                this.username = userName;
                this.password = password;
        }else{
            alertPane.setText("Incorrect email or password");
        }
    }

    public String getUsername() {return username;}

    private boolean userAndPasswordMatch(String userName, JPasswordField password) {
        return false; //// HOLD FOR SQL
    }

    private boolean userExists(String user){
        return false; /////// HOLD for SQL
    }
}