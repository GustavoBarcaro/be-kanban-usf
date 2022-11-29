package classes;

public class UserModel {
    public String username;
    public String password;
    public boolean is_admin = false;

    public void debug()
    {
        System.out.println("name: " + this.username);
        System.out.println("time: " + this.password);
        System.out.println("dueTime: " + this.is_admin);
    }
}
