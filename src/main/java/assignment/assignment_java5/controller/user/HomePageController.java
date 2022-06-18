package assignment.assignment_java5.controller.user;

import assignment.assignment_java5.model.AgriculturalProduce;
import assignment.assignment_java5.model.typeEnum.TypeAgriculturalProduce;
import assignment.assignment_java5.service.AgriculturalProduceService;
import assignment.assignment_java5.service.AuthenService;
import assignment.assignment_java5.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/Shop")
public class HomePageController {
    @Autowired
    AgriculturalProduceService agriculturalProduceService;
    @Autowired
    SupplierService supplierService;


    @GetMapping("/Home")
    public String home(ModelMap model,
                       @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
                       @RequestParam(name = "login", defaultValue = "false") boolean login,
                       @RequestParam(name = "type", required = false) TypeAgriculturalProduce type) {
        if (type == TypeAgriculturalProduce.Rau) {
            List<AgriculturalProduce> listAgriculture = agriculturalProduceService.getByType(type);
            model.addAttribute("listAgriculture", listAgriculture);
        } else if (type == TypeAgriculturalProduce.Củ) {
            List<AgriculturalProduce> listAgriculture = agriculturalProduceService.getByType(type);
            model.addAttribute("listAgriculture", listAgriculture);

        } else if (type == TypeAgriculturalProduce.Quả) {
            List<AgriculturalProduce> listAgriculture = agriculturalProduceService.getByType(type);
            model.addAttribute("listAgriculture", listAgriculture);
        } else {
            Page<AgriculturalProduce> pageAgriculture = agriculturalProduceService.getByPage(pageNumber, 6);
            List<AgriculturalProduce> listAgriculture = pageAgriculture.getContent();
            model.addAttribute("listAgriculture", listAgriculture);
            model.addAttribute("page", pageAgriculture);
        }
        model.addAttribute("View", "/user/content/homePage");
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("login", login);
        model.addAttribute("AddCart", "/AddToCart");
        model.addAttribute("Item", "/AgriculturalProduce/");
        this.url(model);
        return "user/layout";
    }

    @RequestMapping("/AgriculturalProduce/{id}")
    public String item(ModelMap model, @PathVariable Integer id) {
        model.addAttribute("View", "/user/content/itemPage");
        model.addAttribute("item", agriculturalProduceService.getById(id));
        this.url(model);
        return "user/layout";
    }

    public void url(ModelMap model) {
        model.addAttribute("PAGE", "?pageNumber=");
        model.addAttribute("HomeURL", "/Home");
        model.addAttribute("SEARCH", "/search");
        model.addAttribute("BASEURL", "/Shop/Home");
    }
}
