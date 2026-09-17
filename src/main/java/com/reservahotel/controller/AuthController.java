package com.reservahotel.controller;

import com.reservahotel.entity.Usuario;
import com.reservahotel.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String clave, HttpSession session, Model model) {
        Optional<Usuario> usuarioOpt = usuarioService.iniciarSesion(email, clave);
        if (usuarioOpt.isPresent()) {
            session.setAttribute("usuarioLogin", usuarioOpt.get());
            return "redirect:/";
        } else {
            model.addAttribute("error", "Email o clave incorrectos.");
            return "auth/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?mensaje=Sesion+finalizada";
    }

    @GetMapping("/recuperar")
    public String showRecuperarForm() {
        return "auth/recuperar";
    }

    @PostMapping("/recuperar")
    public String recuperarClave(@RequestParam String email, Model model) {
        try {
            String resultado = usuarioService.recuperarClave(email);
            model.addAttribute("mensaje", resultado);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "auth/recuperar";
    }
}
