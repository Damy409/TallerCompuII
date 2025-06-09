package org.demo1.controller;

import org.demo1.model.User;
import org.demo1.model.Role;
import org.demo1.repository.RoleRepository;
import org.demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Mostrar formulario
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);
        return "register";
    }

    // Procesar formulario
    @PostMapping("/register")
    public String processRegister(@ModelAttribute("user") User user) {
        // Encriptar la contraseña antes de guardar
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/login?success";
    }



    @GetMapping("/")
    public String base(Model model) {
        return "base";
    }
}
