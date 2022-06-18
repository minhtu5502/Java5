package assignment.assignment_java5.controller.user;

import assignment.assignment_java5.model.Alert;
import assignment.assignment_java5.model.CartDetails;
import assignment.assignment_java5.service.AgriculturalProduceService;
import assignment.assignment_java5.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/Shop")
public class CartController {
    @Autowired
    AgriculturalProduceService agriculturalProduceService;
    @Autowired
    CartService cartService;
    @GetMapping("/AddToCart/{id}")
    public String addToCart(ModelMap model,
                       @PathVariable Integer id , @RequestParam(name = "quantity" ,defaultValue="1") int quantity){
        boolean cart = cartService.addToCart(id,quantity);
        return "redirect:/Shop/Cart?cart="+cart;
    }
    @GetMapping("/UpdateCart/{id}")
    public String updateToCart(ModelMap model,
                            @PathVariable Integer id , @RequestParam(name = "quantity") int quantity){
        boolean cart = cartService.changeQuantity(id,quantity);
        return "redirect:/Shop/Cart?cart="+cart;
    }
    @GetMapping("/Delete/{id}")
    public String deleteCart(@PathVariable Integer id){
        cartService.remove(id);
        return "redirect:/Shop/Cart";
    }
    @GetMapping("/Cart")
    public String cart(ModelMap model,
                       @RequestParam(name = "cart" ,defaultValue="null") String cart){
        List<CartDetails> cartdetails = cartService.getCart().getCartDetails();
        int total = 0;
        for(CartDetails c : cartdetails) {
            total += c.getQuantityOrder() * Integer.parseInt(String.valueOf(c.getAgriculturalProduce().getPrice()));
        };
        model.addAttribute("View","/user/content/cart");
        model.addAttribute("cartdetails",cartdetails);
        model.addAttribute("cart",cart);
        model.addAttribute("total",total);
        model.addAttribute("Delete","/Shop/Cart/Delete/");
        return "user/layout";
    }
}
