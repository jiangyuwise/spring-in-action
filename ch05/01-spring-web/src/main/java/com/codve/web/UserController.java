package com.codve.web;

import com.codve.User;
import com.codve.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author admin
 * @date 2019/10/30 12:00
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "register", method = GET)
    public String registerForm() {
        return "registerForm";
    }

    @RequestMapping(value = "register", method = POST)
    public String register(
            @RequestPart("picture") byte[] picture,
            @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return "registerForm";
        }

        userRepository.save(user);

        return "redirect:/user/" + user.getName();
    }

    @RequestMapping(value = "/{username}", method = POST)
    public String userProfile(
            @PathVariable String username,
            Model model) {
        model.addAttribute("user", userRepository.findByUsername(username));
        return "profile";
    }
}
