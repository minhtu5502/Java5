package assignment.assignment_java5.controller.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Admin")
@PreAuthorize("isAuthenticated() and hasAuthority('Admin')")
public class HomeController {
    @GetMapping("/Home")
    public String home(ModelMap model){
        model.addAttribute("view","/admin/contents/home");
        this.url(model);
        return "admin/layout";
    }

    public void url(ModelMap model) {
//        model.addAttribute("ADD", "/New");
        model.addAttribute("BASEURL", "/Admin/Home");
//        model.addAttribute("BLOCK", "/delete/");
        model.addAttribute("SEARCH", "/search");
//        model.addAttribute("EDIT", "/Edit/");
//        model.addAttribute("DELETE_ALL", "/Admin/Manage/AgriculturalProduces/Delete");
    }
}

