package assignment.assignment_java5.service;

import assignment.assignment_java5.model.Cart;
import assignment.assignment_java5.model.CartDetails;


import java.util.List;

public interface CartService {
    Cart getCart();

    boolean addToCart(Integer agriculturalProduceId, Integer quantity);

    List<CartDetails> getCarts();

    boolean changeQuantity(Integer agriculturalProduceId, Integer quantity);

    void remove(Integer agriculturalProduceId);

    void removeAll();
}
