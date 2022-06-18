package assignment.assignment_java5.model;

import java.util.List;

public class Cart {
    private List<CartDetails> cartDetails;

    public List<CartDetails> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(List<CartDetails> cartDetails) {
        this.cartDetails = cartDetails;
    }
}
