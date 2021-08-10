package ru.job4j.gc;

public class User {

    private String name;
    private String homedir;
    private int groupID;

    public User (String name, String homedir, int groupID) {
        this.name = name;
        this.homedir = homedir;
        this.groupID = groupID;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed %s %s %d%n", name, homedir, groupID);
    }

    public String getHomedir() {
        return homedir;
    }

    public void setHomedir(String homedir) {
        this.homedir = homedir;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

}
