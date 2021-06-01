package ua.kpi.tef.toys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ua.kpi.tef.toys.pojo.User;
import ua.kpi.tef.toys.service.UserService;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User createUser(User user) {
        return userService.createUser(user);
    }

    public User findUserById(Long id) {
        return userService.findUserById(id);
    }

    public List<User> findAllUsers(){
        return userService.findAll();
    }

    public User updateUser(User user, String email, String lastName, String firstName, String username){
        userService.updateUser(user, email, lastName, firstName, username);
        return user;
    }

    public void deleteById(Long id){
        userService.deleteById(id);
    }

}
