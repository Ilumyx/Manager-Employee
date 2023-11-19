package Object;

public class Manager extends User{
    private String ExpInYear;
    private int projectID;

    public Manager() {
    }

    public Manager(int id, String fullName, String email, String password, String expInYear, int projectID) {
        super(id, fullName, email, password);
        ExpInYear = expInYear;
        this.projectID = projectID;
    }

    public String getExpInYear() {
        return ExpInYear;
    }

    public void setExpInYear(String expInYear) {
        ExpInYear = expInYear;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
}
