package models;

public class UserModel {
    //user attributes
    private int UID;
    private String username;
    private String password;
    private String name;
    private Boolean admin;
    private Boolean teacher;

    //constructor
    public UserModel(String username, String password, String name, Boolean admin, Boolean teacher) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.admin = admin;
        this.teacher = teacher;
    }

    //overloaded constructor
    public UserModel(){

    }

    //GETTER AND SETTERS

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public Boolean getTeacher() {
        return teacher;
    }

    public void setTeacher(Boolean teacher) {
        this.teacher = teacher;
    }
}

