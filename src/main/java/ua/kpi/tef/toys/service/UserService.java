package ua.kpi.tef.toys.service;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.kpi.tef.toys.pojo.User;
import ua.kpi.tef.toys.pojo.enums.RoleType;
import ua.kpi.tef.toys.repository.UserRepository;

import java.util.List;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User createUser(User user) {
        return User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                .role(RoleType.ROLE_USER)
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username).orElseThrow(()
                -> new UsernameNotFoundException("user " + username + " not found!"));
    }

    public User findUserById(Long id) {
        return userRepository
                .findUserById(id)
                .orElseThrow(() -> new UsernameNotFoundException("user with id " + id + " not found"));
    }

    public User findById(Long id){
        return userRepository.getOne(id);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void updateUser(User user, String email, String lastName, String firstName, String username){
        user.setEmail(email);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setUsername(username);
        userRepository.save(user);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
}