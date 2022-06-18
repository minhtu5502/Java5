package assignment.assignment_java5.service;

import assignment.assignment_java5.model.Users;

public interface AuthenService {
    Users login(String username, String pass);
    boolean isAdmin();
    void logout();
    Users getUser();

}
