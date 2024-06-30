package hadar_and_neta;

public class Username {
    private String name;
    private String password;

    public Username(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Username(Username other) {
        this.name = other.name;
        this.password = other.password;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Username [name = " + name +"]";
    }

}
