package assignment.assignment_java5.controller.user;

import assignment.assignment_java5.model.OrderDetails;
import assignment.assignment_java5.model.Orders;
import assignment.assignment_java5.model.Users;
import assignment.assignment_java5.model.typeEnum.StatusOrder;
import assignment.assignment_java5.service.CartService;
import assignment.assignment_java5.service.OrderDetailsService;
import assignment.assignment_java5.service.OrderService;
import assignment.assignment_java5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/Shop/Order")
@PreAuthorize("isAuthenticated()")
public class OrdesController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailsService orderDetailsService;
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;
    @GetMapping("")
    public String orderHistory(ModelMap model, HttpSession session, Principal principal,
                               @RequestParam(name = "ordered" ,defaultValue="null") String ordered,
                               @RequestParam(name = "statusOrder" ,defaultValue="null") String statusOrder){
        Users user = userService.getUserByUsername(principal.getName());
        List<Orders> orderList = orderService.getAllOrders(user);
        List<OrderDetails> orderDetailList = orderDetailsService.getAllOrderDetails();
        cartService.removeAll();
        model.addAttribute("View","/user/content/orderHistory");
        model.addAttribute("OrderDetail",orderDetailList);
        model.addAttribute("Order",orderList);
        model.addAttribute("ordered",ordered);
        model.addAttribute("statusOrder",statusOrder);
        model.addAttribute("StatusDone", StatusOrder.c);
        model.addAttribute("StatusCancel", StatusOrder.a);
        return "user/layout";
    }
    @GetMapping("/Edit/{id}")
    public String editConfirm(@PathVariable Integer id ) {
        orderService.updateStatus(id,StatusOrder.d);
        return "redirect:/Shop/Order?statusOrder=true";
    }
    @GetMapping("/Cancel/{id}")
    public String cancelConfirm(@PathVariable Integer id ) {
        orderService.updateStatus(id,StatusOrder.e);
        return "redirect:/Shop/Order?statusOrder=cancel";
    }
    @GetMapping("/{id}")
    public String orderDetailHistory(ModelMap model, @PathVariable Integer id){
        List<OrderDetails> orderDetailList = orderDetailsService.getById(id);
        model.addAttribute("View","/user/content/orderDetailTable");
        model.addAttribute("OrderDetail",orderDetailList);
        return "user/layout";
    }

}
