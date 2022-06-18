package assignment.assignment_java5.controller.admin;

import assignment.assignment_java5.model.AgriculturalProduce;
import assignment.assignment_java5.model.Users;
import assignment.assignment_java5.model.typeEnum.StatusAgriculturalProduce;
import assignment.assignment_java5.model.typeEnum.TypeAgriculturalProduce;
import assignment.assignment_java5.service.AgriculturalProduceService;
import assignment.assignment_java5.service.SupplierService;
import assignment.assignment_java5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;

@Controller
@RequestMapping("/Admin/Manage/AgriculturalProduces")
@PreAuthorize("isAuthenticated() and hasAuthority('Admin')")
public class AgriculturalProduceController {
    @Autowired
    AgriculturalProduceService agriculturalProduceService;
    @Autowired
    SupplierService supplierService;
    @Autowired
    UserService userService;
    @GetMapping("")
    public String home(ModelMap model,
                       @RequestParam(name = "pageNumber",defaultValue = "1") int pageNumber,
                       @RequestParam(name = "maxRecord", defaultValue = "5" ) int maxRecord,
                       @RequestParam(name = "add", defaultValue = "false" ) boolean add,
                       @RequestParam(name = "update", defaultValue = "false" ) boolean update,
                       Principal principal,
                       HttpSession session){
        Page<AgriculturalProduce> pageAgriculture = agriculturalProduceService.getByPage(pageNumber, maxRecord);
        model.addAttribute("page", pageAgriculture);
        model.addAttribute("view","/admin/contents/agriculturalProduceContent/tableAgriculturalProduces");
        model.addAttribute("currentPage",pageNumber);
        model.addAttribute("add",add);
        model.addAttribute("update",update);
        this.url(model);

        Users user = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", user);

        return "admin/layout";
    }
    @GetMapping("/New")
    public String newAgriculture(ModelMap model){
        model.addAttribute("view","/admin/contents/agriculturalProduceContent/formCreateAgriculturalProduces");
        this.url(model);
        model.addAttribute("CREATE", "/Add");
        model.addAttribute("Supplier", supplierService.getAll());

        return "admin/layout";
    }
    @GetMapping("/Edit/{id}")
    public String editAgriculture(ModelMap model, @PathVariable Integer id){
        model.addAttribute("view","/admin/contents/agriculturalProduceContent/formEditAgriculturalProduces");
        this.url(model);
        model.addAttribute("UDPATE", "/Update");
        model.addAttribute("agricultural", agriculturalProduceService.getById(id));
        model.addAttribute("Supplier", supplierService.getAll());

        return "admin/layout";
    }
    @PostMapping(value = "/Add")
    public String addAgriculture(@RequestParam("name") String name,
                                 @RequestParam("weight") double weight,
                                 @RequestParam("supplier") Integer supplierId,
                                 @RequestParam("quantity") Integer quantity,
                                 @RequestParam("price") BigDecimal price,
                                 @RequestParam("type") TypeAgriculturalProduce type,
                                 @RequestParam("status") StatusAgriculturalProduce status,
                                 @RequestParam("image") MultipartFile image,
                                 @RequestParam("nutrition") String nutrition
                                 ){
        this.agriculturalProduceService.saveEarPhoneToDb(image,name,weight,supplierId,price,quantity,type,status,nutrition);
        return "redirect:/Admin/Manage/AgriculturalProduces?add=true";
    }
    @PostMapping(value = "/Update")
    public String updateAgriculture(@RequestParam("id") Integer id,
                                 @RequestParam("name") String name,
                                 @RequestParam("weight") double weight,
                                 @RequestParam("supplier") Integer supplierId,
                                 @RequestParam("quantity") Integer quantity,
                                 @RequestParam("price") BigDecimal price,
                                 @RequestParam("type") TypeAgriculturalProduce type,
                                 @RequestParam("status") StatusAgriculturalProduce status,
                                 @RequestParam("image") MultipartFile image,
                                 @RequestParam("nutrition") String nutrition
    ){
        this.agriculturalProduceService.updateEarPhoneToDb(image,id,name,weight,supplierId,price,quantity,type,status,nutrition);
        return "redirect:/Admin/Manage/AgriculturalProduces?update=true";
    }

    public void url(ModelMap model) {
        model.addAttribute("ADD", "/New");
        model.addAttribute("PAGE", "?pageNumber=");
        model.addAttribute("BASEURL", "/Admin/Manage/AgriculturalProduces");
        model.addAttribute("BLOCK", "/delete/");
        model.addAttribute("SEARCH", "/search");
        model.addAttribute("EDIT", "/Edit/");
        model.addAttribute("DELETE_ALL", "/Admin/Manage/AgriculturalProduces/Delete");
    }
}
