package assignment.assignment_java5.service;

import assignment.assignment_java5.model.Users;
import assignment.assignment_java5.model.typeEnum.Gender;
import assignment.assignment_java5.model.typeEnum.Role;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

public interface UserService  {
    Users addUser(Users user) throws Exception;

    Users updateUser(Users user);

    List<Users> getAllUsers();

    Users getUser(Integer userId);

    Users getUserByUsername(String username);

    List<Users> getTypeRole(Integer typeRole);

    Users deleteUser(Integer userId);

    void deleteAllUser(Integer[] userId);

    Page<Users> findPaginated(int pageNo, int pageSize);

    void saveUserToDb(MultipartFile file, String username, String fullName, String password, String email, String phone, Role role, Gender gender);

    void updateUserToDb(MultipartFile file, Long id, String username, String fullName, String password, String email, String phone, Role role, Gender gender);
}
