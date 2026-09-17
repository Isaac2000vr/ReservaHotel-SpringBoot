package com.reservahotel.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(HttpSession session) {
        if (session.getAttribute("usuarioLogin") == null) {
            return "redirect:/login";
        }
        return "index";
    }
}
