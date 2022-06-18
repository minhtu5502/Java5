package assignment.assignment_java5.controller.user;

import assignment.assignment_java5.model.CartDetails;
import assignment.assignment_java5.model.Users;
import assignment.assignment_java5.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

@Controller
@RequestMapping("/Shop")
@PreAuthorize("isAuthenticated()")
public class CheckoutController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailsService orderDetailsService;
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;

    @RequestMapping("/CheckOut")
    public String checkOut(ModelMap model, Principal principal){

        int total = 0;
        for(CartDetails c : cartService.getCart().getCartDetails()) {
            total += c.getQuantityOrder() * Integer.parseInt(String.valueOf(c.getAgriculturalProduce().getPrice()));
        };
        Users user = userService.getUserByUsername(principal.getName());


        orderService.addOrder(user,new Date(), BigDecimal.valueOf(total));
        for(CartDetails c : cartService.getCart().getCartDetails()) {
            orderDetailsService.addOrderDetails(c.getAgriculturalProduce().getId(),c.getAgriculturalProduce().getPrice(),c.getQuantityOrder());
        };
        return "redirect:Order?ordered=true";
    }
}
