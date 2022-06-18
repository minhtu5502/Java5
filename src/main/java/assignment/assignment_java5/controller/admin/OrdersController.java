package assignment.assignment_java5.controller.admin;

import assignment.assignment_java5.model.OrderDetails;
import assignment.assignment_java5.model.Orders;
import assignment.assignment_java5.model.typeEnum.StatusOrder;
import assignment.assignment_java5.service.OrderDetailsService;
import assignment.assignment_java5.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/Admin/Manage/Order")
@PreAuthorize("isAuthenticated() and hasAuthority('Admin')")
public class OrdersController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailsService orderDetailsService;
    @GetMapping("/{id}")
    public String orderDetailHistory(ModelMap model, @PathVariable Integer id){
        List<OrderDetails> orderDetailList = orderDetailsService.getById(id);
        int total = 0;
        for(OrderDetails o : orderDetailList) {
            total += o.getQuantity() * Integer.parseInt(String.valueOf(o.getPrice()));
        };
        model.addAttribute("view","/admin/contents/orderContent/orderDetailTable");
        model.addAttribute("OrderDetail",orderDetailList);
        model.addAttribute("total",total);
        return "admin/layout";
    }
    @GetMapping("/Confirm")
    public String orderConfirm(ModelMap model) {
        List<Orders> orderList = orderService.getAllOrdersByStatus(StatusOrder.a);
        model.addAttribute("view", "/admin/contents/orderContent/orderTable.html");
        model.addAttribute("orderList", orderList);
        model.addAttribute("StatusDone", StatusOrder.a);
        model.addAttribute("text", "Xác Nhận");;
        model.addAttribute("Next", "/Admin/Manage/Order/Pickup");
        model.addAttribute("EDIT", "/Confirm/Edit/");
        this.url(model);
        return "admin/layout";

    }
    @GetMapping("/Confirm/Edit/{id}")
    public String editConfirm(@PathVariable Integer id ) {
        orderService.updateStatus(id,StatusOrder.b);
        return "redirect:/Admin/Manage/Order/Confirm";
    }
    @GetMapping("/Pickup")
    public String orderPickup(ModelMap model) {
        List<Orders> orderList = orderService.getAllOrdersByStatus(StatusOrder.b);

        model.addAttribute("view", "/admin/contents/orderContent/orderTable.html");
        model.addAttribute("orderList", orderList);
        model.addAttribute("StatusDone", StatusOrder.b);
        model.addAttribute("text", "Shiper đã lấy hàng");
        model.addAttribute("EDIT", "/Pickup/Edit/");
        model.addAttribute("Next", "/Admin/Manage/Order/Delivery");
        model.addAttribute("Back", "/Admin/Manage/Order/Confirm");
        this.url(model);
        return "admin/layout";

    }
    @GetMapping("/Pickup/Edit/{id}")
    public String editPickup(@PathVariable Integer id ) {
        orderService.updateStatus(id,StatusOrder.c);
        return "redirect:/Admin/Manage/Order/Pickup";
    }
    @GetMapping("/Delivery")
    public String orderDelivery(ModelMap model) {
        List<Orders> orderList = orderService.getAllOrdersByStatus(StatusOrder.c);
        model.addAttribute("view", "/admin/contents/orderContent/orderTable.html");
        model.addAttribute("orderList", orderList);
        model.addAttribute("StatusDone", StatusOrder.c);
        model.addAttribute("text", "");
        model.addAttribute("Next", "/Admin/Manage/Order/Successful");
        model.addAttribute("Back", "/Admin/Manage/Order/Pickup");
        this.url(model);
        return "admin/layout";

    }

    @GetMapping("/Successful")
    public String orderSuccessful(ModelMap model) {
        List<Orders> orderList = orderService.getAllOrdersByStatus(StatusOrder.d);

        model.addAttribute("view", "/admin/contents/orderContent/orderTable.html");

        model.addAttribute("orderList", orderList);
        model.addAttribute("StatusDone", StatusOrder.d);
        model.addAttribute("text", "");
        model.addAttribute("Next", "/Admin/Manage/Order/Cancel");
        model.addAttribute("Back", "/Admin/Manage/Order/Delivery");
        this.url(model);
        return "admin/layout";

    }

    @GetMapping("/Cancel")
    public String orderCancel(ModelMap model) {
        List<Orders> orderList = orderService.getAllOrdersByStatus(StatusOrder.e);

        model.addAttribute("view", "/admin/contents/orderContent/orderTable.html");
        model.addAttribute("orderList", orderList);
        model.addAttribute("StatusDone", StatusOrder.e);
        model.addAttribute("text", "");
        model.addAttribute("Back", "/Admin/Manage/Order/Successful");

        this.url(model);
        return "admin/layout";

    }



    public void url(ModelMap model) {
        model.addAttribute("PAGE", "?orderListNumber=");
        model.addAttribute("BASEURL", "/Admin/Manage/Order");
        model.addAttribute("SEARCH", "/search");

    }
}
