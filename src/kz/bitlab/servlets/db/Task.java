package kz.bitlab.servlets.db;

public class Task {
    private Long id = 4L;
    private String name;
    private String description;
    private String deadlineDate;
    private boolean done = false;
    public Task(){}
    public Task(String name, String description, String deadlineDate,boolean done){
        this.name = name;
        this.description = description;
        this.deadlineDate = deadlineDate;
        this.done = done;
    }
    public Task(Long id,String name, String description, String deadlineDate,boolean done){
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadlineDate = deadlineDate;
        this.done = done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isDone() {
        return done;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
