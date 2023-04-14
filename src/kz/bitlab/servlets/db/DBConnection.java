package kz.bitlab.servlets.db;

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

    public static ArrayList<Task> getTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tasks");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getLong("id"));
                task.setName(resultSet.getString("name"));
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
                    "INSERT INTO tasks (name, deadline, yes_no, description) " +
                            "VALUES (?, ?, ?, ?)"
            );
            statement.setString(1, task.getName());
            statement.setString(2, task.getDeadlineDate());
            statement.setBoolean(3, task.isDone());
            statement.setString(4, task.getDescription());

            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Task getTask(Long id) {
        Task task = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM tasks WHERE id = ? LIMIT 1"
            );
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                task = new Task();
                task.setId(resultSet.getLong("id"));
                task.setName(resultSet.getString("name"));
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
                            "name = ?, " +
                            "deadline = ?, " +
                            "yes_no = ?, " +
                            "description = ? " +
                            "WHERE id = ?"
            );
            statement.setString(1,task.getName());
            statement.setString(2,task.getDeadlineDate());
            statement.setBoolean(3,task.isDone());
            statement.setString(4,task.getDescription());
            statement.setLong(5,task.getId());
            statement.executeUpdate();

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void removeTask(Long id){
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM tasks WHERE id = ?"
            );
            statement.setLong(1,id);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
