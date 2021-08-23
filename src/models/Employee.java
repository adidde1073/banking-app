package models;

public class Employee extends User {
    private String role;

    public Employee(String username, String password, String role) {
        super(username, password);
        this.role=role;
    }
    public String getRole() {
        return this.role;
    }
}