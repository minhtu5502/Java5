package assignment.assignment_java5.model.typeEnum;

public enum Gender {
    Nam(0), Nữ(1);

    private int value;

    Gender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
