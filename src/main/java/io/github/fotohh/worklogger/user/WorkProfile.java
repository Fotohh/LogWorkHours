package io.github.fotohh.worklogger.user;

public class WorkProfile {

    private String username;
    private String password;
    private long workHours;

    public long getWorkHours() {
        return workHours;
    }

    public void setWorkHours(long workHours) {
        this.workHours = workHours;
    }

    public void addWorkHours(long workHours){
        this.workHours += workHours;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public WorkProfile() {
    }

    public WorkProfile(String username, String password, long time){
        this.username = username;
        this.password = password;
        this.workHours = time;
    }
}
