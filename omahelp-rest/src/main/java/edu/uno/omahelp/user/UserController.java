package edu.uno.omahelp.user;

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
        return "Hello!";
    }

    @RequestMapping("test")
    public String test() {
    	return userDao.test();
    }
    
    @RequestMapping("list") 
    public List<User> listAllUsers() {
        return userDao.listAllUsers();
    }

}