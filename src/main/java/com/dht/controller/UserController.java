/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.controller;

import com.dht.pojo.User;
import com.dht.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ADMIN
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerView(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerHandler(Model model,
            @ModelAttribute(value = "user") @Valid User user,
            BindingResult result) {
        if (result.hasErrors() == true) {
            return "register";
        }

        String errMsg;
        if (user.getPassword().equals(user.getConfirmPassword())) {
            if (this.userService.addUser(user) == true) {
                return "redirect:/login";
            } else {
                errMsg = "Something wrong! Please come back later!";
            }
        } else {
            errMsg = "Password does not match!!!";
        }

        model.addAttribute("errMsg", errMsg);

        return "register";
    }

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    @RequestMapping("/user/{username}")
    public String userDetails(Model model,
            @PathVariable(name = "username") String username) {
        model.addAttribute("user", this.userService.getUserByUsername(username));
        return "user-detail";
    }
}
