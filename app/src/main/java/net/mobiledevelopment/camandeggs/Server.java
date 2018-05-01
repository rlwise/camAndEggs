package net.mobiledevelopment.camandeggs;

/**
 * Created by robbwise on 3/28/18.
 */

public class Server {
    private int id;
    private String ip;

    //constructors
    public Server(){}

    public Server(String ip) {
        super();
        this.ip = ip;

    }

    //getters
    public int getId(){
        return this.id;
    }
    public String getIP(){
        return this.ip;
    }

    //setters
    public void setId(int i){
        this.id = i;
    }
    public void setIP(String i){
        this.ip = i;
    }

    @Override
    public String toString(){
        return "Server [id=" + id + ", ip=" + ip + "]";
    }
}
