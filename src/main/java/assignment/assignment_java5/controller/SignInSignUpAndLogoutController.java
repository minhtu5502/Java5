package assignment.assignment_java5.controller;

import assignment.assignment_java5.model.Users;
import assignment.assignment_java5.model.typeEnum.Role;
import assignment.assignment_java5.service.AuthenService;
import assignment.assignment_java5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("")
public class SignInSignUpAndLogoutController {
    @Autowired
    AuthenService authenService;
    @Autowired
    UserService userService;
    @PostMapping("/Shop/Login")
    public String login( ModelMap model,
            @RequestParam(name = "login") boolean login){
       if(login == false){
           model.addAttribute("login",login);
           return "signInAndSignUp/login";
       }
        return "redirect:/Shop/Home";
    }
    @GetMapping("/Shop/Login")
    public String loginPage(ModelMap model,  @RequestParam(name = "login", defaultValue = "true") boolean login,
                            @RequestParam(name = "signUp", defaultValue = "false") boolean signUp){
        model.addAttribute("login",login);
        model.addAttribute("signUp",signUp);
        return "signInAndSignUp/login";
    }
    @GetMapping("/Shop/SignUp")
    public String signUpPage(ModelMap model){

        return "signInAndSignUp/signUp";
    }
    @PostMapping("/Shop/SignUp")
    public String signUp(ModelMap model, @ModelAttribute("user") @Valid Users user) throws Exception {
        user.setRole(Role.Customer);
        userService.addUser(user);
        return "redirect:/Shop/Login?signUp=true";
    }
    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/Shop/Home";
    }
}
