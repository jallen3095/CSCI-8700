package edu.uno.omahelp.user;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
    private UserDao userDao;
    
    @RequestMapping(path = "list/{userId}")
    public User getUserById(@PathVariable int userId) throws URISyntaxException, SQLException {
        return userDao.getUserById(userId);
    }
    
    @RequestMapping(path = "list") 
    public List<User> listAllUsers() throws URISyntaxException, SQLException {
        return userDao.listAllUsers();
    }
    
    @RequestMapping(path = "register", method = RequestMethod.POST)
    public void createUser(@RequestBody User user) throws URISyntaxException, SQLException {
        userDao.createUser(user);
    }

    @RequestMapping(path = "edit", method = RequestMethod.PUT)
    public void editUser(@RequestBody User user) throws URISyntaxException, SQLException {
        userDao.editUser(user);
    }
    
    @RequestMapping(path = "delete", method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam int userId) throws URISyntaxException, SQLException {
        userDao.deleteUser(userId);
    }

    @RequestMapping(path = "login")
    public User login(@RequestParam String email, @RequestParam String password) throws Exception {
        return userDao.login(email, password);
    }
}