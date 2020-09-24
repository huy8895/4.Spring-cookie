package com.cookie.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cookie.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")
public class LoginController {

    /*add user in model attribute*/
    @ModelAttribute("user")
    public User setUpUserForm() {
        return new User();
    }

    @RequestMapping("/login")
    public String Index( Model model) {
        Cookie cookie = new Cookie("setUser", "");
        model.addAttribute("cookieValue", cookie);
        return "login";
    }

    @PostMapping("/dologin")
    public String doLogin(@ModelAttribute("user") User user,
                          Model model,
                          @CookieValue(value = "setUser", defaultValue = "") String setUser,
                          HttpServletResponse response, HttpServletRequest request) {

        System.out.println(user.getEmail() +" "+ user.getPassword());
        Cookie cookie = new Cookie("setUser",setUser);
        if (user.getEmail().equals("huy8895")&&user.getPassword().equals("123456")){
            model.addAttribute("cookieValue", cookie);
            return "home";
        }
        model.addAttribute("message", "Login failed. Try again.");
        model.addAttribute("cookieValue", cookie);
        return "login";
    }

    @GetMapping("/dologout")
    public String dologout(HttpServletRequest request,HttpServletResponse response){
//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie:cookies){
//            System.out.println("cookie name:" + cookie.getName());
//            cookie.setMaxAge(0);
//            response.addCookie(cookie);
//        }
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(0);
        return "redirect:/login";
    }

    @GetMapping("/edit")
    public String showEdit(){
        return "edit";
    }
}