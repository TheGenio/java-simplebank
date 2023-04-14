import java.io.Serializable;

public class client implements Serializable {
    private double balance;
    private String userId;
    private String password;

    public client(){
        this(0,"Default","default");
    }

    public client(double balance, String userId, String password){
        this.balance = balance;
        this.userId = userId;
        this.password = password;
    }

    // setters
    public void setBalance(double balance){
        this.balance = balance;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }
    public void setPassword(String password){
        this.password = password;
    }

    //getters
    public double getBalance(){
        return this.balance;
    }
    public String getUserId(){
        return this.userId;
    }
    public String getPassword(){
        return this.password;
    }


    public boolean transfer(double amount){
        double temp = getBalance() + amount;
        setBalance(temp);
        return true;
    }

    public boolean checkBalance(double amount){
        if (this.balance > amount){
            return true;
        } else
            return false;

    }

    public String toString(){
        return this.getUserId();
    }


}


