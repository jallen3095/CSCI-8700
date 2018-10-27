package edu.uno.omahelp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("user/")
public class UserController {

	@Autowired
    private UserDao userDao;

    @RequestMapping("register")
    public boolean createUser(@RequestParam int userId,
                              @RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestParam String email,
                              @RequestParam String password,
                              @RequestParam boolean admin) throws URISyntaxException, SQLException {
        return userDao.createUser(userId, firstName, lastName, email, password, admin);
    }

    @RequestMapping("list") 
    public List<User> listAllUsers() throws URISyntaxException, SQLException {
        return userDao.listAllUsers();
    }
}