package javaee.db;

import java.sql.Timestamp;

public class Message {
    int id;
    int id_chat;
    int id_sender;
    String message;
    int id_receiver;
    Timestamp sended_time;
    Boolean read_by_receiver;


    public Message(int id, int chat_id, int id_sender, String message, int id_receiver, Timestamp sended_time, Boolean read_by_receiver) {
        this.id = id;
        this.id_chat = chat_id;
        this.id_sender = id_sender;
        this.message = message;
        this.id_receiver = id_receiver;
        this.sended_time = sended_time;
        this.read_by_receiver = read_by_receiver;
    }

    public Message() {
        this.read_by_receiver = false;
    }

    public int getId() {
        return id;
    }

    public int getId_chat() {
        return id_chat;
    }

    public void setId_chat(int id_chat) {
        this.id_chat = id_chat;
    }

    public Boolean getRead_by_receiver() {
        return read_by_receiver;
    }

    public void setRead_by_receiver(Boolean read_by_receiver) {
        this.read_by_receiver = read_by_receiver;
    }

    public Timestamp getSended_time() {
        return sended_time;
    }

    public void setSended_time(Timestamp timestamp) {
        this.sended_time = timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_sender() {
        return id_sender;
    }

    public void setId_sender(int id_sender) {
        this.id_sender = id_sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId_receiver() {
        return id_receiver;
    }

    public void setId_receiver(int id_receiver) {
        this.id_receiver = id_receiver;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", id_sender=" + id_sender +
                ", message='" + message + '\'' +
                ", id_receiver=" + id_receiver +
                '}';
    }
}
