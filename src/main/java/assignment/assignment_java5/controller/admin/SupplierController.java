package assignment.assignment_java5.controller.admin;

import assignment.assignment_java5.model.AgriculturalProduce;
import assignment.assignment_java5.model.Supplier;
import assignment.assignment_java5.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Admin/Manage/Suppliers")
@PreAuthorize("isAuthenticated() and hasAuthority('Admin')")
public class SupplierController {
    @Autowired
    SupplierService supplierService;
    @GetMapping("")
    public String home(ModelMap model,
                       @RequestParam(name = "pageNumber",defaultValue = "1") int pageNumber,
                       @RequestParam(name = "maxRecord", defaultValue = "5" ) int maxRecord,
                       @RequestParam(name = "add", defaultValue = "false" ) boolean add,
                       @RequestParam(name = "update", defaultValue = "false" ) boolean update){
        Page<Supplier> pageSupplier = supplierService.getSuppliers(pageNumber, maxRecord);
        model.addAttribute("page", pageSupplier);
        model.addAttribute("view","/admin/contents/supplierContent/tableSupplier");
        model.addAttribute("currentPage",pageNumber);
        model.addAttribute("add",add);
        model.addAttribute("update",update);
        this.url(model);
        return "admin/layout";
    }
    @PostMapping("/Add")
    public String add(ModelMap model,
                         @RequestParam(name = "name" ) String name){
        supplierService.add(new Supplier(null,name));
        return "redirect:/Admin/Manage/Suppliers?add=true";
    }
    @PostMapping("/Update")
    public String update(ModelMap model,
                       @RequestParam(name = "id") Integer id,
                       @RequestParam(name = "name" ) String name){
        supplierService.update(new Supplier(id,name));
        return "redirect:/Admin/Manage/Suppliers?update=true";
    }
    public void url(ModelMap model) {
        model.addAttribute("PAGE", "?pageNumber=");
        model.addAttribute("BASEURL", "/Admin/Manage/Suppliers");
        model.addAttribute("BLOCK", "/delete/");
        model.addAttribute("SEARCH", "/search");
        model.addAttribute("UPDATE", "/Update");
        model.addAttribute("CREATE", "/Add");
        model.addAttribute("DELETE_ALL", "/Admin/Manage/AgriculturalProduces/Delete");
    }
}
