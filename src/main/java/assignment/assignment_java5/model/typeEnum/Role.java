package assignment.assignment_java5.model.typeEnum;

public enum Role {
    Admin(0),Customer(1);
    
    private int value;

    Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
