
public class User {
    private int id;
    private String name;
    private String password;
    private String email;
    private boolean online;
    public User(int id, String name, String password, String email, boolean online) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.online = online;
    }
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean isOnline() {
        return online;
    }
    
    public void setOnline(boolean online) {
        this.online = online;
    }
    
}