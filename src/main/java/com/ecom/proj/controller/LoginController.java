package com.ecom.proj.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ecom.proj.global.GlobalData;
import com.ecom.proj.model.Role;
import com.ecom.proj.model.User;
import com.ecom.proj.service.RoleService;
import com.ecom.proj.service.UserService;

@Controller
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    // open login page
    @GetMapping("/login")
    public String login() {
        GlobalData.cart.clear();
        GlobalData.roles.clear();
        return "login";
    }

    // open register page
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    // registerd a user
    @PostMapping("/register")
    public String registerPost(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
        String password = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.getRoleById(2).get());
        user.setRoles(roles);
        userService.addUpdUser(user);
        request.login(user.getEmail(), password);
        return "redirect:/";
    }
}
