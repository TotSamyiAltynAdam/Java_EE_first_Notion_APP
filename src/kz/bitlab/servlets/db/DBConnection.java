package kz.bitlab.servlets.db;

import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBConnection {
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tech_orda_db",
                    "root",
                    "ec2-hTS-BMm-fUT");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Course getCourse(Long id){
        Course course = null;
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM courses WHERE course_id = ?");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                course = new Course();
                course.setCourse_id(resultSet.getLong("course_id"));
                course.setCourse_name(resultSet.getString("course_name"));
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return course;
    }
    public static ArrayList<Task> getTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT t.id, t.course_id, c.course_name, t.deadline, t.yes_no, t.description " +
                    "FROM tasks AS t INNER JOIN courses AS c " +
                    "ON t.course_id = c.course_id");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Course course = new Course();
                course.setCourse_name(resultSet.getString("course_name"));
                course.setCourse_id(resultSet.getLong("course_id"));

                Task task = new Task();
                task.setId(resultSet.getLong("id"));
                task.setCourse(course);
                task.setDeadlineDate(resultSet.getString("deadline"));
                task.setDone(resultSet.getBoolean("yes_no"));
                task.setDescription(resultSet.getString("description"));
                tasks.add(task);
            }
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public static ArrayList<Task> searchTasks(String key) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT t.id, t.course_id, c.course_name, t.deadline, t.yes_no, t.description " +
                            "FROM tasks AS t " +
                            "INNER JOIN courses AS c ON t.course_id = c.course_id " +
                            "WHERE LOWER(c.course_name) LIKE LOWER(?)");
            statement.setString(1,key);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Course course = new Course();
                course.setCourse_name(resultSet.getString("course_name"));
                course.setCourse_id(resultSet.getLong("course_id"));

                Task task = new Task();
                task.setId(resultSet.getLong("id"));
                task.setCourse(course);
                task.setDeadlineDate(resultSet.getString("deadline"));
                task.setDone(resultSet.getBoolean("yes_no"));
                task.setDescription(resultSet.getString("description"));
                tasks.add(task);
            }
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public static void addTask(Task task) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO tasks (course_id, deadline, yes_no, description) " +
                            "VALUES (?, ?, ?, ?)"
            );
            statement.setLong(1, task.getCourse().getCourse_id());
            statement.setString(2, task.getDeadlineDate());
            statement.setBoolean(3, task.isDone());
            statement.setString(4, task.getDescription());

            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void addCourse(Course course) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO courses (course_name) " +
                            "VALUES (?)"
            );
            statement.setString(1, course.getCourse_name());

            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static User getUser(String email) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users " +
                            "WHERE email = ?"
            );
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setFullName(resultSet.getString("full_name"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getInt("role_id"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    public static void addUser(User user){
        try{
            PreparedStatement statement = connection.prepareStatement(
              "INSERT INTO users(email,password,full_name,role_id) " +
                      "VALUES (?,?,?,?)"
            );
            statement.setString(1,user.getEmail());
            statement.setString(2,user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setInt(4,user.getRole());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Task getTask(Long id) {
        Task task = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT t.id, t.course_id, c.course_name, t.deadline, t.yes_no, t.description " +
                            "FROM tasks AS t INNER JOIN courses AS c " +
                            "ON t.course_id = c.course_id " +
                            "WHERE t.id = ?"
            );
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Course course = new Course();
                course.setCourse_id(resultSet.getLong("course_id"));
                course.setCourse_name(resultSet.getString("course_name"));

                task = new Task();
                task.setId(resultSet.getLong("id"));
                task.setCourse(course);
                task.setDeadlineDate(resultSet.getString("deadline"));
                task.setDone(resultSet.getBoolean("yes_no"));
                task.setDescription(resultSet.getString("description"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return task;
    }

    public static void updateTask(Task task) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE tasks " +
                            "SET " +
                            "course_id = ?, " +
                            "deadline = ?, " +
                             "yes_no = ?, " +
                            "description = ? " +
                            "WHERE id = ?"
            );
            statement.setLong(1, task.getCourse().getCourse_id());
            statement.setString(2, task.getDeadlineDate());
            statement.setBoolean(3, task.isDone());
            statement.setString(4, task.getDescription());
            statement.setLong(5, task.getId());
            statement.executeUpdate();

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeTask(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM tasks WHERE id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<Course> getCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM courses ORDER BY course_id ASC");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Course course = new Course();
                course.setCourse_id(resultSet.getLong("course_id"));
                course.setCourse_name(resultSet.getString("course_name"));
                courses.add(course);
            }
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }
    public static void addNews(News news){
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO news (title,content,post_date,user_id) " +
                            "VALUES (?, ?, NOW(), ?)"
            );
            statement.setString(1,news.getTitle());
            statement.setString(2, news.getContent());
            statement.setLong(3,news.getUser().getId());

            statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static ArrayList<News> getNews(){
        ArrayList<News> newsArrayList = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement(
              "SELECT n.id, n.title, n.content, n.user_id, u.full_name, n.post_date " +
                      "FROM news n " +
                      "INNER JOIN users u ON u.id = n.user_id " +
                      "ORDER BY n.post_date DESC"
            );
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                News news = new News();
                news.setId(resultSet.getLong("id"));
                news.setTitle(resultSet.getString("title"));
                news.setContent(resultSet.getString("content"));
                news.setPostDate(resultSet.getTimestamp("post_date"));

                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                news.setUser(user);

                newsArrayList.add(news);
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return newsArrayList;
    }
    public static News getNewsByID(Long id){
        News news = null;
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT n.id, n.title, n.content, n.user_id, u.full_name, n.post_date " +
                            "FROM news n " +
                            "INNER JOIN users u ON u.id = n.user_id " +
                            "WHERE n.id = ?"
            );
            statement.setLong(1,id);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                news = new News();
                news.setId(resultSet.getLong("id"));
                news.setTitle(resultSet.getString("title"));
                news.setContent(resultSet.getString("content"));
                news.setPostDate(resultSet.getTimestamp("post_date"));

                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                news.setUser(user);
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return news;
    }
    public static void updateNews(News news){
        try{
            PreparedStatement statement = connection.prepareStatement(
              "UPDATE news SET title = ?, content = ? " +
                      "WHERE id = ?"
            );
            statement.setString(1,news.getTitle());
            statement.setString(2,news.getContent());
            statement.setLong(3,news.getId());

            statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void addComment(Comment comment){
        try{
            PreparedStatement statement = connection.prepareStatement(
              "INSERT INTO comments (comment,user_id, news_id,post_date) " +
                      "VALUES (?,?,?,NOW())"
            );
            statement.setString(1,comment.getComment());
            statement.setLong(2,comment.getUser().getId());
            statement.setLong(3,comment.getNews().getId());

            statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static ArrayList<Comment> getComments(Long newsId){
        ArrayList<Comment> comments = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement(
                "SELECT c.id, c.comment, c.post_date, c.news_id, c.user_id, u.full_name " +
                        "FROM comments c " +
                        "INNER JOIN users u ON c.user_id = u.id " +
                        "WHERE c.news_id = ? " +
                        "ORDER BY c.post_date DESC"
            );
            statement.setLong(1,newsId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setFullName(resultSet.getString("full_name"));

                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setComment(resultSet.getString("comment"));
                comment.setPostDate(resultSet.getTimestamp("post_date"));
                comment.setUser(user);
                comments.add(comment);
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return comments;
    }

}
