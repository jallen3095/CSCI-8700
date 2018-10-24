package edu.uno.omahelp.user;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/")
public class UserController {

    private final UserDao userDao;

    public UserController(final UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping("register")
    public String registerUser() {
        return "Hello!";
    }

    @RequestMapping("list") 
    public List<User> listAllUsers() {
        return userDao.listAllUsers();
    }
}