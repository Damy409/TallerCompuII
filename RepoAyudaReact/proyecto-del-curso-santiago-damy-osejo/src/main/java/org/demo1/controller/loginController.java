package org.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
public class loginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}




