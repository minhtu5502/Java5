package assignment.assignment_java5.service.Impl;

import assignment.assignment_java5.model.AgriculturalProduce;
import assignment.assignment_java5.model.Cart;
import assignment.assignment_java5.model.CartDetails;
import assignment.assignment_java5.service.AgriculturalProduceService;
import assignment.assignment_java5.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private static final String ATT_CART_NAME = "myCart";

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private AgriculturalProduceService agriculturalProduceService;
    private List<CartDetails> listCart = new ArrayList<>();


    @Override
    public Cart getCart() {
        Cart cart = (Cart) request.getSession().getAttribute(ATT_CART_NAME);
        if (cart == null) {
            cart = new Cart();
            cart.setCartDetails(new ArrayList<>());
            request.getSession().setAttribute(ATT_CART_NAME, cart);
        }
        return cart;
    }


    @Override
    public boolean addToCart(Integer agriculturalProduceId, Integer quantity) {
        Cart cart = getCart();
        AgriculturalProduce agriculturalProduce = agriculturalProduceService.getById(agriculturalProduceId);
        boolean flag = true;
        boolean result = false;
        if (quantity > agriculturalProduce.getQuantity()) {
            flag = false;
            result = false;
        } else {
            for (CartDetails c : cart.getCartDetails()) {
                if (agriculturalProduceId.equals(c.getAgriculturalProduce().getId())) {
                    if (c.getQuantityOrder() == agriculturalProduce.getQuantity() || (c.getQuantityOrder() + quantity) > agriculturalProduce.getQuantity()) {
                        flag = false;
                        result = false;
                    } else {
                        flag = false;
                        result = true;
                        c.setQuantityOrder(c.getQuantityOrder() + quantity);
                    }
                }
            }
        }
        if (flag) {
            result = true;
            cart.getCartDetails().add(new CartDetails(agriculturalProduce, quantity));
        }

        return result;
    }

    @Override
    public List<CartDetails> getCarts() {
        return listCart;
    }

    @Override
    public boolean changeQuantity(Integer agriculturalProduceId, Integer quantity) {
        AgriculturalProduce agriculturalProduce = agriculturalProduceService.getById(agriculturalProduceId);
        boolean result =false;
        if(quantity == 0){
            result = true;
            remove(agriculturalProduceId);
        }else {
            for (CartDetails c : getCart().getCartDetails()) {
                if (agriculturalProduceId.equals(c.getAgriculturalProduce().getId())) {
                    if (quantity > agriculturalProduce.getQuantity()) {
                        result = false;
                    } else {
                        result = true;
                        c.setQuantityOrder(quantity);
                    }
                }
            }
        }
        return result;

    }

    @Override
    public void remove(Integer agriculturalProduceId) {
        Cart cart = getCart();
        List<CartDetails> list = cart.getCartDetails();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAgriculturalProduce().getId().equals(agriculturalProduceId)) {
                //noinspection SuspiciousListRemoveInLoop
                list.remove(i);
            }
        }
    }

    @Override
    public void removeAll() {
        request.getSession().removeAttribute(ATT_CART_NAME);
    }
}
