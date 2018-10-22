package edu.uno.omahelp.user;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class UserController {

    @RequestMapping("/")
    public String registerUser() {
        return "Hello!";
    }

    //@RequestMapping("/user/login")
    //public String loginUser() {}
}