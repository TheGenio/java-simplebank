public class client {
    private double balance;
    private String user_id;
    private String password;

    public client(){
        this(0,"Default","default");
    }

    public client(double balance, String user_id, String password){
        this.balance = balance;
        this.user_id = user_id;
        this.password = password;
    }

    // setters

    public void setbalance(double balance){
        this.balance = balance;
    }
    public void setUserId(String user_id){
        this.user_id = user_id;
    }
    public void setPassword(String password){
        this.password = password;
    }

    //getters
    public double getBalance(){
        return this.balance;
    }
    public String getUserId(){
        return this.user_id;
    }
    public String getPassword(){
        return this.password;
    }



    public boolean transfer(double amount){
        double temp = getBalance() + amount;
        setbalance(temp);
        return true;
    }

    public boolean checkBalance(double amount){

        if (this.balance > amount){
            return true;
        } else
            return false;

    }


}


