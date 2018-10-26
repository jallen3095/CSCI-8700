package edu.uno.omahelp.user;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/")
public class UserController {

	@Autowired
    private UserDao userDao;

    @RequestMapping("register")
    public String registerUser() {
        return "Register";
    }

    @RequestMapping("list") 
    public List<User> listAllUsers() throws URISyntaxException, SQLException {
        return userDao.listAllUsers();
    }
}