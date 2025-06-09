package org.demo1.controller;

import org.demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.demo1.model.User;

@Controller
@RequestMapping("/admin")
public class adminController {

    @Autowired
    private UserService userService; 

    @GetMapping("/createUser")
    public String createUser(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.findAll());
        return "admin/user";
    }

    @PostMapping("/createUser")
    public String postUser(@RequestParam User user, Model model){
        userService.save(user);
        return "redirect:admin/user";
    }

    
    @GetMapping("/createRole")
    public String createRole(Model model){
        return "admin/role";
    }

    @PostMapping("/createRole")
    public String postRole(Model model){
        return "redirect:admin/role";
    }

    @GetMapping("/createPermission")
    public String createPermission(Model model){
        return "admin/permission";
    }

    @PostMapping("/createPermission")
    public String postPermission(Model model){
        return "redirect:admin/permission";
    }

    





}
