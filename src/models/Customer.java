package models;

public class Customer extends User {
    private long accountNo;

    public Customer(String username, String password, long accountNo) {
        super(username, password);
        this.accountNo=accountNo;
    }
    public long getAccountNo() {
        return this.accountNo;
    }
}
