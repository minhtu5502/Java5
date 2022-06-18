package assignment.assignment_java5.repository;

import assignment.assignment_java5.model.Users;
import assignment.assignment_java5.model.typeEnum.Gender;
import assignment.assignment_java5.model.typeEnum.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsersRepository extends JpaRepository<Users,Integer> {
    void deleteByIdIn(List<Integer> id);//xoá nhiều theo Id
    List<Users> findAllByRole(Role role);//tìm theo role
    List<Users> findAllByGender(Gender gender);//tìm theo giới tính
    Users findByUsernameAndPassword(String username, String password);
    Users findByUsername(String username);
    boolean existsByUsername(String username);
}
