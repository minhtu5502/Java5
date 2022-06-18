package assignment.assignment_java5.model;

public class Alert {
    private String icon,message;

    public Alert(String icon, String message) {
        this.icon = icon;
        this.message = message;
    }
    public Alert() {
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
