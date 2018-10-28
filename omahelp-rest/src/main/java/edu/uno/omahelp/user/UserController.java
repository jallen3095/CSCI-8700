package edu.uno.omahelp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public boolean createUser(@RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestParam String email,
                              @RequestParam String password,
                              @RequestParam boolean admin) throws URISyntaxException, SQLException {
        return (userDao.createUser(firstName, lastName, email, password, admin) > 0);
    }

    /****** NOT CURRENTLY WORKING ******/
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public boolean update(@RequestBody User user) throws URISyntaxException, SQLException {
        return userDao.update(user);
    }

    @RequestMapping("login")
    public User login(@RequestParam String email, @RequestParam String password) throws Exception {
        return userDao.login(email, password);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET) 
    public List<User> listAllUsers() throws URISyntaxException, SQLException {
        return userDao.listAllUsers();
    }
}