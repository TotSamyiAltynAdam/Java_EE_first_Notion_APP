package kz.bitlab.servlets.db;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static final List<Task> TASK_LIST = new ArrayList<>();
    static long id = 4L;
    static {
        TASK_LIST.add(
                new Task(
                        1L,
                        "JSP",
                        "Java Servlet Page",
                        "08.04.2023",
                        true
                )
        );
        TASK_LIST.add(
                new Task(
                        2L,
                        "Advanced Programming",
                        "To solve the problem which is related to matrix in Discrete" +
                                "Mathematics by using recursion",
                        "11.04.2023",
                        false
                )
        );
        TASK_LIST.add(
                new Task(
                        3L,
                        "Software Engineering",
                        "Learn Java Spring and set up MongoDB",
                        "21.04.2023",
                        false
                )
        );
        TASK_LIST.add(
                new Task(
                        4L,
                        "Leetcode",
                        "Solve the problem in the leetcode",
                        "09.04.2023",
                        false
                )
        );
    }

    public static List<Task> getAllTasks() {
        return TASK_LIST;
    }
    public static void addTask(Task task){
        task.setId(++id);
        TASK_LIST.add(task);
    }
    public static Task getTask(Long id){
        return TASK_LIST
                .stream()
                .filter(task -> task.getId()==id)
                .findFirst()
                .orElse(null);
    }
    public static void updateTask(Task updatedTask){
        for(int i = 0;i<TASK_LIST.size();i++){
            if(TASK_LIST.get(i).getId().equals(updatedTask.getId())) {
                TASK_LIST.set(i, updatedTask);
                break;
            }
        }
    }
    public static void removeTask(Long id){
        for(int i = 0;i< TASK_LIST.size();i++){
            if(TASK_LIST.get(i).getId().equals(id)) {
                TASK_LIST.remove(i);
                break;
            }
        }
    }
}
