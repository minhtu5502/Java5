package assignment.assignment_java5.service;

import assignment.assignment_java5.model.Users;
import assignment.assignment_java5.repository.IUsersRepository;
import assignment.assignment_java5.utils.EncrytedPasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private IUsersRepository usersRepository;

    EncrytedPasswordUtils encrytedPasswordUtils = new EncrytedPasswordUtils();
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Users users = usersRepository.findByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException(username);
        }
        return User.withUsername(users.getUsername()).password(users.getPassword()).authorities(users.getRole().toString()).build();
    }
}
