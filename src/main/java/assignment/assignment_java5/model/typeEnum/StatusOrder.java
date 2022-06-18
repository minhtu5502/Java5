package assignment.assignment_java5.model.typeEnum;

public enum StatusOrder {
    a("Chờ xác nhận"),b("Chờ Lấy Hàng"),c("Đang Giao Hàng"),
    d("Đã Nhận"),e("Đã Huỷ");

    private String value;

    StatusOrder(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
