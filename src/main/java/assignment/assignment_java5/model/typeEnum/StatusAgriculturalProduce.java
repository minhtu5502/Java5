package assignment.assignment_java5.model.typeEnum;

public enum StatusAgriculturalProduce {
    active("Đang bán"), invalid("Ngừng bán");

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    StatusAgriculturalProduce(String value) {

        this.value = value;
    }
}
