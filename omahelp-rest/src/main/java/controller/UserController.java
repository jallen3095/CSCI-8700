package controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class UserController {

    @RequestMapping("/user/register")
    public String registerUser() {
        return "Hello!";
    }

    //@RequestMapping("/user/login")
    //public String loginUser() {}
}