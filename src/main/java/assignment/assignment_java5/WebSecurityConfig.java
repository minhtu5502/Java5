package assignment.assignment_java5;

import assignment.assignment_java5.model.Users;
import assignment.assignment_java5.model.typeEnum.Role;
import assignment.assignment_java5.repository.IUsersRepository;
import assignment.assignment_java5.service.Impl.UserServiceImpl;
import assignment.assignment_java5.service.UserDetailService;
import assignment.assignment_java5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.annotation.Resource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailService userDetailsService;
    @Autowired
    private IUsersRepository usersRepository;

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);// Cung cáp userservice cho spring security
        authProvider.setPasswordEncoder(passwordEncoder());// cung cấp password encoder
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
//                .and().authorizeRequests().antMatchers("/Shop/*").access("hasAnyRole('Admin','Customer')")
//                // Trang chỉ dành cho ADMIN
//                .authorizeRequests().antMatchers("/Admin/*").hasAuthority("Admin")
//                .and()
                .authorizeRequests().antMatchers("/", "/Shop/Home","/Shop/SignUp","/admin/**","/login/**","/user/**")
                .permitAll().anyRequest().authenticated() // Tất cả các request khác đều cần phải xác thực mới được truy cập
                 //Người dùng vào trang sai vai trò,
                // Ngoại lệ AccessDeniedException sẽ ném ra.
//                .and().authorizeRequests().and().exceptionHandling().accessDeniedPage("/403")
//                // Cho phép tất cả mọi người truy cập vào địa chỉ này
//
//                // Nếu chưa login, nó sẽ redirect tới trang /login.
                .and()
                .formLogin() // Cho phép người dùng xác thực bằng form login
                .loginPage("/Shop/Login")
                .defaultSuccessUrl("/Shop/Home?login=true",true)// login thành công thì vào request này
                .failureUrl("/Shop/Login?login=false")
                .usernameParameter("username")//
                .passwordParameter("password")
                .loginProcessingUrl("/j_spring-security_check")
                .permitAll() // Tất cả đều được truy cập vào địa chỉ này
                .and()
                .logout() // Cho phép logout
                .logoutSuccessUrl("/Shop/Home")
                .logoutUrl("/j_spring_security_logout")
                .permitAll();


    }

}
