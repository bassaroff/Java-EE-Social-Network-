package javaee.db;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/javaee?useUnicode=true&serverTimezone=UTC",
                    "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean addRequest(int id_user, int id_sender){
        int rows_added = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO requests (sent_time, id_user, id_sender) " +
                            "VALUES (?, ?, ?)"
            );

            preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setInt(2, id_user);
            preparedStatement.setInt(3, id_sender);

            rows_added = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_added > 0;
    }
    public static ArrayList<Request> getRequests(int id){
        ArrayList<Request> requests = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM requests where id_user=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Request request = new Request(rs.getInt("id"), rs.getInt("id_user"), rs.getInt("id_sender"));
                requests.add(request);
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return requests;
    }
    public static ArrayList<Request> getRequestsBySender(int id){
        ArrayList<Request> requests = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM requests where id_sender=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Request request = new Request(rs.getInt("id"), rs.getInt("id_user"), rs.getInt("id_sender"));
                requests.add(request);
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return requests;
    }

    public static boolean deleteFriend(int id_current, int id_friend){
        int rows_deleted = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM friends WHERE id_user =? AND id_friend=?"
            );
            preparedStatement.setInt(1, id_current);
            preparedStatement.setInt(2, id_friend);
            rows_deleted = preparedStatement.executeUpdate();

            preparedStatement.setInt(1, id_friend);
            preparedStatement.setInt(2, id_current);
            rows_deleted = preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rows_deleted > 0;
    }
    public static boolean addFriend(int id_user, int id_friend, int id_request){
        int rows_added = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO friends (id_user, id_friend) " +
                            "VALUES (?, ?)"
            );


            preparedStatement.setInt(1, id_user);
            preparedStatement.setInt(2, id_friend);

            rows_added = preparedStatement.executeUpdate();



            preparedStatement = connection.prepareStatement(
                    "INSERT INTO friends (id_user, id_friend) " +
                            "VALUES (?, ?)"
            );
            preparedStatement.setInt(1, id_friend);
            preparedStatement.setInt(2, id_user);

            rows_added = preparedStatement.executeUpdate();



            preparedStatement = connection.prepareStatement(
              "Delete  from requests where id = ?"
            );
            preparedStatement.setInt(1, id_request);

            rows_added = preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rows_added > 0;
    }

//    public static ArrayList<Request> getRequestsBySender(int id){
//        ArrayList<Request> requests = new ArrayList<>();
//        try
//        {
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM requests where id_sender=?");
//            preparedStatement.setInt(1, id);
//            ResultSet rs = preparedStatement.executeQuery();
//            while(rs.next()){
//                Request request = new Request(rs.getInt("id"), rs.getInt("id_user"), rs.getInt("id_sender"));
//                requests.add(request);
//            }
//            preparedStatement.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return requests;
//    }

    public static ArrayList<User> getFriends(int id){
        ArrayList<User> friends = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from friends where id_user=?");
            preparedStatement.setInt(1,id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                User user = DBManager.getUser(rs.getInt("id_friend"));
                friends.add(user);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return friends;
    }



    public static ArrayList<User> getAllUsers(){
        ArrayList<User> users = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, full_name, picture_url, birthdate " +
                    "FROM users");
            ResultSet rs = preparedStatement.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("id");
                String full_name = rs.getString("full_name");
                String picture_url = rs.getString("picture_url");
                Date date = rs.getDate("birthdate");

                User user = new User();
                user.setId(id);
                user.setBirthdate(date);
                user.setFullName(full_name);
                user.setPictureURL(picture_url);

                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public static boolean addUser(User user) {
        int rows_added = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (email, password, full_name, picture_url, birthdate) " +
                            "VALUES (?, ?, ?, ?, ?)"
            );

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setString(4, user.getPictureURL());
            preparedStatement.setDate(5, user.getSqlBirthdate());

            rows_added = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_added > 0;
    }

    public static boolean addPost(Post post) {
        int rows_added = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO posts (title, short_content, content, post_date, author_id) " +
                            "VALUES (?, ?, ?, ?, ?)"
            );

            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getShortContent());
            preparedStatement.setString(3, post.getContent());
            preparedStatement.setTimestamp(4, post.getPostDate());
            preparedStatement.setInt(5, post.getUser().getId());

            rows_added = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_added > 0;
    }

    public static boolean addLanguage(Language language) {
        int rows_added = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO languages (name, code) " +
                            "VALUES (?, ?)"
            );
            preparedStatement.setString(1, language.getName());
            preparedStatement.setString(2, language.getCode());

            rows_added = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_added > 0;
    }

    public static boolean addPublication(Publication publication) {
        int rows_added = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO publications (name, description, rating) " +
                            "VALUES (?, ?, ?)"
            );
            preparedStatement.setString(1, publication.getName());
            preparedStatement.setString(2, publication.getDescription());
            preparedStatement.setDouble(3, publication.getRating());

            rows_added = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_added > 0;
    }

    public static boolean deleteRequest(int id){
        int rows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM requests WHERE id=?");
            preparedStatement.setInt(1,id);
            rows = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows > 0;
    }
    public static boolean addNewsItem(NewsItem newsItem) {
        int rows_added = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO news (title, short_content, content, picture_url, post_date, language_id, publication_id) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            preparedStatement.setString(1, newsItem.getTitle());
            preparedStatement.setString(2, newsItem.getShortContent());
            preparedStatement.setString(3, newsItem.getContent());
            preparedStatement.setString(4, newsItem.getPictureURL());
            preparedStatement.setTimestamp(5, newsItem.getPostDate());
            preparedStatement.setInt(6, newsItem.getLanguage().getId());
            preparedStatement.setInt(7, newsItem.getPublication().getId());

            rows_added = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_added > 0;
    }

    public static ArrayList<NewsItem> getPublicationNewsItems(int publicationId, int languageId) {
        ArrayList<NewsItem> publicationNewsItems = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT n.id, n.title, n.short_content, n.content, n.post_date, n.picture_url, n.language_id, n.publication_id " +
                            "FROM news n " +
                            "INNER JOIN publications p ON n.publication_id = p.id " +
                            "INNER JOIN languages l ON n.language_id = l.id " +
                            "WHERE n.publication_id = ? AND n.language_id = ? "
            );
            preparedStatement.setInt(1, publicationId);
            preparedStatement.setInt(2, languageId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                publicationNewsItems.add(new NewsItem(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getString("picture_url"),
                        resultSet.getTimestamp("post_date"),
                        getLanguage(resultSet.getInt("language_id")),
                        getPublication(resultSet.getInt("publication_id"))
                ));
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return publicationNewsItems;
    }

    public static ArrayList<NewsItem> getNewsItemsByLanguage(int languageId) {
        ArrayList<NewsItem> publicationNewsItems = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT n.id, n.title, n.short_content, n.content, n.post_date, n.picture_url, n.language_id, n.publication_id " +
                            "FROM news n " +
                            "INNER JOIN languages l ON n.language_id = l.id " +
                            "WHERE n.language_id = ? "
            );
            preparedStatement.setInt(1, languageId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                publicationNewsItems.add(new NewsItem(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getString("picture_url"),
                        resultSet.getTimestamp("post_date"),
                        getLanguage(resultSet.getInt("language_id")),
                        getPublication(resultSet.getInt("publication_id"))
                ));
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return publicationNewsItems;
    }

    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, email, password, full_name, picture_url, birthdate " +
                            "FROM users"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("picture_url"),
                        resultSet.getDate("birthdate")
                ));
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public static ArrayList<Post> getPosts() {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, title, short_content, content, post_date, author_id " +
                            "FROM posts order by post_date DESC"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                posts.add(new Post(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("post_date"),
                        getUser(resultSet.getInt("author_id"))
                ));
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return posts;
    }
    public static Chat chatExist(Chat chat){
        Chat chat1 = new Chat();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from chats where (id_user=? and id_friend=?) or (id_user=? and id_friend=?)");
            preparedStatement.setInt(1, chat.getId_user());
            preparedStatement.setInt(2,chat.getId_friend());

            preparedStatement.setInt(3, chat.getId_friend());
            preparedStatement.setInt(4,chat.getId_user());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){

                chat1.setId(rs.getInt("id"));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println(chat1);
        return chat1;
    }

    public static ArrayList<Chat> getChats(int id_current){
        ArrayList<Chat> chats = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from chats where id_user=? or id_friend=?");
            preparedStatement.setInt(1,id_current);
            preparedStatement.setInt(2,id_current);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                Chat chat = new Chat(rs.getInt("id"),
                        rs.getInt("id_user"),
                        rs.getInt("id_friend"),
                        rs.getTimestamp("created_date"),
                        rs.getString("latest_message_text"),
                        rs.getTimestamp("latest_message_time"));
                chats.add(chat);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return chats;
    }

    public static Chat getChat(int id){
        Chat chat = new Chat();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from chats where id=?");
            preparedStatement.setInt(1,id);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                chat = new Chat(rs.getInt("id"),
                        rs.getInt("id_user"),
                        rs.getInt("id_friend"),
                        rs.getTimestamp("created_date"),
                        rs.getString("latest_message_text"),
                        rs.getTimestamp("latest_message_time"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return chat;
    }
    public static boolean addChat(Chat chat, String message){
        int rows_added = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO chats (id_user, id_friend, created_date, latest_message_text,latest_message_time) " +
                    "values (?,?,?,?,?)");

            preparedStatement.setInt(1,chat.getId_user());
            preparedStatement.setInt(2,chat.getId_friend());
            preparedStatement.setTimestamp(3, chat.getCreated_date());
            preparedStatement.setString(4, chat.getLatest_message_text());
            preparedStatement.setTimestamp(5, chat.getLatest_message_time());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("SELECT * from chats where id_user=? or id_friend=?");
            preparedStatement.setInt(1,chat.getId_user());
            preparedStatement.setInt(2,chat.getId_user());

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                chat = new Chat(rs.getInt("id"),
                        rs.getInt("id_user"),
                        rs.getInt("id_friend"),
                        rs.getTimestamp("created_date"),
                        rs.getString("latest_message_text"),
                        rs.getTimestamp("latest_message_time"));
            }

            preparedStatement = connection.prepareStatement("INSERT INTO messages (id_chat,id_sender, message, id_receiver, sended_time, read_by_receiver) " +
                    "VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1,chat.getId());
            preparedStatement.setInt(2, chat.getId_user());
            preparedStatement.setString(3,message);
            preparedStatement.setInt(4, chat.getId_friend());
            preparedStatement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setBoolean(6,false);
            rows_added = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return  rows_added > 0;
    }
    public static boolean readMessage(int id_message){
        int rows_updated = 0;
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE messages " +
                            "SET read_by_receiver=1 " +
                            "WHERE id=" + id_message
            );
            rows_updated = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_updated > 0;
    }


    public static boolean addMessage(Message message){
        int rows_added = 0;

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO messages (id_chat,id_sender, message, id_receiver, sended_time, read_by_receiver) " +
                    "VALUES (?, ?, ?, ?, ?, ?)");

            preparedStatement.setInt(1,message.getId_chat());
            preparedStatement.setInt(2, message.getId_sender());
            preparedStatement.setString(3,message.getMessage());
            preparedStatement.setInt(4, message.getId_receiver());
            preparedStatement.setTimestamp(5, message.getSended_time());
            preparedStatement.setBoolean(6,message.getRead_by_receiver());
            rows_added = preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("UPDATE chats set latest_message_text=?, latest_message_time=? where id=?");
            preparedStatement.setString(1, message.getMessage());
            preparedStatement.setTimestamp(2,message.getSended_time());
            preparedStatement.setInt(3, message.getId_chat());
            rows_added = preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rows_added > 0;
    }

    public static ArrayList<Message> getMessagesByChat(int id_chat){
        ArrayList<Message> messages = new ArrayList<>();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from messages where id_chat=? order by sended_time ASC");

            preparedStatement.setInt(1,id_chat);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("id"),
                        rs.getInt("id_chat"),
                        rs.getInt("id_sender"),
                        rs.getString("message"),
                        rs.getInt("id_receiver"),
                        rs.getTimestamp("sended_time"),
                        rs.getBoolean("read_by_receiver"));

                messages.add(message);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return messages;
    }

    public static ArrayList<Post> getPostsByUserID(int authorId) {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, title, short_content, content, post_date, author_id " +
                            "FROM posts " +
                            "WHERE author_id=? " +
                            "order by post_date DESC"
            );
            preparedStatement.setInt(1, authorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                posts.add(new Post(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("post_date"),
                        getUser(resultSet.getInt("author_id"))
                ));
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return posts;
    }

    public static ArrayList<Language> getLanguages() {
        ArrayList<Language> languages = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, name, code " +
                            "FROM languages"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                languages.add(new Language(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code")
                ));
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return languages;
    }

    public static ArrayList<Publication> getPublications() {
        ArrayList<Publication> publications = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, name, description, rating " +
                            "FROM publications"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                publications.add(new Publication(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("rating")
                ));
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return publications;
    }

    public static ArrayList<NewsItem> getNewsItems() {
        ArrayList<NewsItem> newsItems = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, title, short_content, content, post_date, picture_url, language_id, publication_id " +
                            "FROM news"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                newsItems.add(new NewsItem(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getString("picture_url"),
                        resultSet.getTimestamp("post_date"),
                        getLanguage(resultSet.getInt("language_id")),
                        getPublication(resultSet.getInt("publication_id"))
                ));
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return newsItems;
    }

    public static int countUsers() {
        int amount = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT COUNT(*) FROM users"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                amount++;
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return amount;
    }

    public static int countPosts() {
        int amount = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT COUNT(*) FROM posts"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                amount++;
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return amount;
    }

    public static User getUserByEmail(String email) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, email, password, full_name, picture_url, birthdate " +
                            "FROM users WHERE email = ? LIMIT 1"
            );
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("picture_url"),
                        resultSet.getDate("birthdate")
                );
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public static User getUser(int id) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, email, password, full_name, picture_url, birthdate " +
                            "FROM users WHERE id = ? LIMIT 1"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("picture_url"),
                        resultSet.getDate("birthdate")
                );

            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public static Post getPost(int id) {
        Post post = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, title, short_content, content, post_date, author_id " +
                            "FROM posts WHERE id = ? LIMIT 1"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                post = new Post(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("post_date"),
                        getUser(resultSet.getInt("author_id"))
                );
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return post;
    }

    public static Language getLanguage (int id) {
        Language language = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, name, code " +
                            "FROM languages " +
                            "WHERE id = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                language = new Language(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code")
                );
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return language;
    }

    public static Language getLanguageByCode (String code) {
        Language language = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, name, code " +
                            "FROM languages " +
                            "WHERE code = ?"
            );
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                language = new Language(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code")
                );
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return language;
    }

    public static Publication getPublication (int id) {
        Publication publication = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, name, description, rating " +
                            "FROM publications " +
                            "WHERE id = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                publication = new Publication(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("rating")
                );
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return publication;
    }

    public static NewsItem getNewsItem (int id) {
        NewsItem newsItem = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT n.id, n.title, n.short_content, n.content, n.post_date, n.picture_url, n.language_id, n.publication_id " +
                            "FROM news n " +
                            "INNER JOIN languages l ON l.id = n.language_id " +
                            "INNER JOIN publications p ON p.id = n.publication_id " +
                            "WHERE n.id = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                newsItem = new NewsItem(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getString("picture_url"),
                        resultSet.getTimestamp("post_date"),
                        getLanguage(resultSet.getInt("language_id")),
                        getPublication(resultSet.getInt("publication_id"))
                );
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return newsItem;
    }

    public static boolean deleteUser(int id) {
        int rows_deleted = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM users WHERE id =" + id
            );
            rows_deleted = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_deleted > 0;
    }

    public static boolean deletePost(int id, int author_id) {
        int rows_deleted = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM posts WHERE id =? AND author_id=? "
            );
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, author_id);
            rows_deleted = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_deleted > 0;
    }

    public static boolean deleteLanguage(int id) {
        int rows_deleted = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM languages WHERE id =" + id
            );
            rows_deleted = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_deleted > 0;
    }

    public static boolean deletePublication(int id) {
        int rows_deleted = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM publications WHERE id =" + id
            );
            rows_deleted = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_deleted > 0;
    }

    public static boolean deleteNewsItems(int id) {
        int rows_deleted = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM news WHERE id =" + id
            );
            rows_deleted = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_deleted > 0;
    }

    public static boolean savePost(Post post) {
        int rows_updated = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE posts " +
                            "SET title=?, short_content=?, content=? " +
                            "WHERE id =? AND author_id=? "
            );
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getShortContent());
            preparedStatement.setString(3, post.getContent());
//            preparedStatement.setTimestamp(4, post.getPostDate());
            preparedStatement.setInt(4, post.getId());
            preparedStatement.setInt(5, post.getUser().getId());
            rows_updated = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_updated > 0;
    }

    public static boolean saveUser(User user) {
        int rows_updated = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users " +
                            "SET email=?, password=?, full_name=?, picture_url=?, birthdate=? " +
                            "WHERE id =" + user.getId()
            );
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setString(4, user.getPictureURL());
            preparedStatement.setDate(5, user.getSqlBirthdate());
            rows_updated = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_updated > 0;
    }

    public static boolean saveUserInfo(int id, String email, String fullName, java.util.Date birthdate) {
        int rows_updated = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users " +
                            "SET email=?, full_name=?, birthdate=? " +
                            "WHERE id =" + id
            );
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, fullName);
            preparedStatement.setDate(3, new java.sql.Date(birthdate.getTime()));
            rows_updated = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_updated > 0;
    }

    public static boolean saveUserPicture(int id, String pictureURL) {
        int rows_updated = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users " +
                            "SET picture_url=? " +
                            "WHERE id =" + id
            );
            preparedStatement.setString(1, pictureURL);
            rows_updated = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_updated > 0;
    }

    public static boolean saveUserPassword(int id, String password) {
        int rows_updated = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users " +
                            "SET password=? " +
                            "WHERE id =" + id
            );
            preparedStatement.setString(1, password);
            rows_updated = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_updated > 0;
    }

    public static boolean saveLanguage(Language language) {
        int rows_updated = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE languages " +
                            "SET name=?, code=? " +
                            "WHERE id =" + language.getId()
            );
            preparedStatement.setString(1, language.getName());
            preparedStatement.setString(2, language.getCode());
            rows_updated = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_updated > 0;
    }

    public static boolean savePublication(Publication publication) {
        int rows_updated = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE publications " +
                            "SET name=?, description=?, rating=? " +
                            "WHERE id =" + publication.getId()
            );
            preparedStatement.setString(1, publication.getName());
            preparedStatement.setString(2, publication.getDescription());
            preparedStatement.setDouble(3, publication.getRating());
            rows_updated = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_updated > 0;
    }

    public static boolean saveNewsItem(NewsItem newsItem) {
        int rows_updated = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE news " +
                            "SET title=?, short_content=?, content=?, picture_url=?, language_id=?, publication_id=? " +
                            "WHERE id =" + newsItem.getId()
            );
            preparedStatement.setString(1, newsItem.getTitle());
            preparedStatement.setString(2, newsItem.getShortContent());
            preparedStatement.setString(3, newsItem.getContent());
            preparedStatement.setString(4, newsItem.getPictureURL());
//            preparedStatement.setTimestamp(5, newsItem.getPostDate());
            preparedStatement.setInt(5, newsItem.getLanguage().getId());
            preparedStatement.setInt(6, newsItem.getPublication().getId());
            rows_updated = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows_updated > 0;
    }
}
