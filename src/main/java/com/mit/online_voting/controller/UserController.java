package com.mit.online_voting.controller;

import com.mit.online_voting.model.User;
import com.mit.online_voting.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Display all users (Admin only)
    @GetMapping("/list")
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users"; // users.html page
    }

    // Registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // register.html
    }

    // Register user
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        userService.registerUser(user);
        return "redirect:/login"; // redirect to login.html
    }

    // Login validation (if not handled separately)
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        User user = userService.validateLogin(username, password);
        if (user != null) {
            if (user.getRole().equalsIgnoreCase("ADMIN")) {
                return "redirect:/admin/dashboard";
            } else {
                return "redirect:/vote";
            }
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }
}
