package hadar_and_neta;

public class User {
    private String name;
    private String password;

    public User(String name, String password) throws IllegalArgumentException {
        setName(name);
        setPassword(password);
    }

    public User(User other) throws IllegalArgumentException {
        setName(other.name);
        setPassword(other.password);
    }

    public String getName() {
        return this.name;
    }
    public String getPassword() {
        return this.password;
    }
    public void setName(String name) throws IllegalArgumentException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name can't be empty");
        }
        this.name = name;
    }
    public void setPassword(String password)  throws IllegalArgumentException {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Name can't be empty");
        }
        this.password = password;
    }

    @Override
    public String toString() {
        return "Username [name = " + name +"]";
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof User)) {
            return false;
        }
        User otherUser = (User)other;
        return otherUser.name.equals(this.name) && otherUser.password.equals(this.password);
    }

}
