package kz.bitlab.servlets.db;

public class Task {
    private Long id;
    private Course course;
    private String description;
    private String deadlineDate;
    private boolean done = false;
    public Task(){}
    public Task(Course course, String description, String deadlineDate,boolean done){
        this.course = course;
        this.description = description;
        this.deadlineDate = deadlineDate;
        this.done = done;
    }
    public Task(Long id, Course course, String description, String deadlineDate,boolean done){
        this.id = id;
        this.course = course;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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
