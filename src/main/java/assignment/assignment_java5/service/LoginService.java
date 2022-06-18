package assignment.assignment_java5.service;

import assignment.assignment_java5.model.Cart;
import assignment.assignment_java5.model.CartDetails;
import assignment.assignment_java5.model.Users;

import java.util.List;

public interface LoginService {
    Users getUser();
    void Login(Integer idUser, Integer quantity);
    void logout();
}
