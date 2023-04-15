import java.io.Serializable;
import java.util.List;

public class banker extends client implements Serializable {

    private List<client> clients;
    banker(List<client> clients, String name, String password) {
        super(0, name, password);
        this.clients = clients;
    }


    public void GetAllUsers(){
        for(client client : clients){
            System.out.println("Clients are: " + client + " Balance is: "+ client.getBalance());
        }

    }


}
