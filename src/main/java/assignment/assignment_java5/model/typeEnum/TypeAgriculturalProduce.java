package assignment.assignment_java5.model.typeEnum;

public enum TypeAgriculturalProduce {
    Rau(0),Củ(1),Quả(2);

    private int value;

    TypeAgriculturalProduce(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
