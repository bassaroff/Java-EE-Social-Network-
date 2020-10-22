package javaee.db;

public class Request {
    int id;
    int id_user;
    int id_sender;


    public Request(int id, int id_user, int id_sender) {
        this.id = id;
        this.id_user = id_user;
        this.id_sender = id_sender;
    }

    public Request() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_sender() {
        return id_sender;
    }

    public void setId_sender(int id_sender) {
        this.id_sender = id_sender;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", id_user=" + id_user +
                ", id_sender=" + id_sender +
                '}';
    }
}
