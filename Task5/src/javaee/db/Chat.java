package javaee.db;

import java.sql.Timestamp;

public class Chat {
    int id;
    int id_user;
    int id_friend;
    Timestamp created_date;
    String latest_message_text;
    Timestamp latest_message_time;

    public Chat(int id, int id_user, int id_friend, Timestamp created_date, String latest_message_text,
                Timestamp lates_message_time) {
        this.id = id;
        this.id_user = id_user;
        this.id_friend = id_friend;
        this.created_date = created_date;
        this.latest_message_text = latest_message_text;
        this.latest_message_time = lates_message_time;
    }

    public Chat() {
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

    public int getId_friend() {
        return id_friend;
    }

    public void setId_friend(int id_friend) {
        this.id_friend = id_friend;
    }

    public Timestamp getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Timestamp created_date) {
        this.created_date = created_date;
    }

    public String getLatest_message_text() {
        return latest_message_text;
    }

    public void setLatest_message_text(String latest_message_text) {
        this.latest_message_text = latest_message_text;
    }

    public Timestamp getLatest_message_time() {
        return latest_message_time;
    }

    public void setLatest_message_time(Timestamp lates_message_time) {
        this.latest_message_time = lates_message_time;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", id_user=" + id_user +
                ", id_friend=" + id_friend +
                ", created_date=" + created_date +
                ", latest_message_text='" + latest_message_text + '\'' +
                ", lates_message_time=" + latest_message_time +
                '}';
    }
}
