package assignment.assignment_java5.service.Impl;

import assignment.assignment_java5.model.Users;
import assignment.assignment_java5.model.typeEnum.Gender;
import assignment.assignment_java5.model.typeEnum.Role;
import assignment.assignment_java5.repository.IUsersRepository;
import assignment.assignment_java5.service.UserService;
import assignment.assignment_java5.utils.EncrytedPasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    IUsersRepository usersRepository;
    @Override
    public Users addUser(@Valid Users user) throws Exception {
        if (usersRepository.existsByUsername(user.getUsername())){
                throw new Exception("Username đã tồn tại");
        }
        user.setId(null);
        user.setPassword(EncrytedPasswordUtils.encrytePassword(user.getPassword()));
        return usersRepository.save(user);
    }

    @Override
    public Users updateUser(Users user) {
        return usersRepository.save(user);
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Users getUser(Integer userId) {
        return usersRepository.findById(userId).get();
    }
    @Override
    public Users getUserByUsername(String userName) {
        return usersRepository.findByUsername(userName);
    }
    @Override
    public List<Users> getTypeRole(Integer typeRole) {
        return null;
    }

    @Override
    public Users deleteUser(Integer userId) {
        return null;
    }

    @Override
    public void deleteAllUser(Integer[] userId) {

    }

    @Override
    public Page<Users> findPaginated(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public void saveUserToDb(MultipartFile file, String username, String fullName, String password, String email, String phone, Role role, Gender gender) {

    }

    @Override
    public void updateUserToDb(MultipartFile file, Long id, String username, String fullName, String password, String email, String phone, Role role, Gender gender) {

    }
}
