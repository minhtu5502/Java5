package assignment.assignment_java5.service.Impl;

import assignment.assignment_java5.model.Users;
import assignment.assignment_java5.model.typeEnum.Role;
import assignment.assignment_java5.repository.IUsersRepository;
import assignment.assignment_java5.service.AuthenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class LoginServiceImpl implements AuthenService {
    private static final String ATT_USER_LOGIN = "login";

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private IUsersRepository usersRepository;

    @Override
    public Users login(String username, String pass) {
        System.out.println(usersRepository.findByUsernameAndPassword(username,pass).getRole().toString());
        return usersRepository.findByUsernameAndPassword(username,pass);
    }

    @Override
    public boolean isAdmin() {
        Users user = getUser();
        return user.getRole().getValue() == Role.Admin.getValue();
    }

    @Override
    public void logout() {
        request.getSession().removeAttribute(ATT_USER_LOGIN);
    }

    @Override
    public Users getUser() {
        Users user = (Users) request.getSession().getAttribute(ATT_USER_LOGIN);
        if (user == null) {
            user = new Users();
            request.getSession().setAttribute(ATT_USER_LOGIN, user);
        }
        return user;
    }
}
