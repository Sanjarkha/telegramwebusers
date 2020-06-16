package db.uz.telegramwebusers.Model;

public class ModelLoginForm {

    private  String username;
    private String  password;

    public ModelLoginForm(){
        super();

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
