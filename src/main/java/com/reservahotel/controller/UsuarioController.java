package com.reservahotel.controller;

import com.reservahotel.entity.Usuario;
import com.reservahotel.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    private boolean checkSession(HttpSession session) {
        return session.getAttribute("usuarioLogin") != null;
    }

    @GetMapping
    public String listar(HttpSession session, Model model) {
        if (!checkSession(session)) return "redirect:/login";
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "usuario/listar";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(HttpSession session, Model model) {
        if (!checkSession(session)) return "redirect:/login";
        model.addAttribute("usuario", new Usuario());
        return "usuario/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario usuario, HttpSession session) {
        if (!checkSession(session)) return "redirect:/login";
        usuarioService.guardar(usuario);
        return "redirect:/usuarios?mensaje=Usuario+guardado+exitosamente";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, HttpSession session, Model model) {
        if (!checkSession(session)) return "redirect:/login";
        usuarioService.buscarPorId(id).ifPresent(u -> model.addAttribute("usuario", u));
        return "usuario/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, HttpSession session) {
        if (!checkSession(session)) return "redirect:/login";
        usuarioService.eliminar(id);
        return "redirect:/usuarios?mensaje=Usuario+eliminado";
    }

    @GetMapping("/reporte/rol")
    public String reportePorRol(@RequestParam String rol, HttpSession session, Model model) {
        if (!checkSession(session)) return "redirect:/login";
        model.addAttribute("usuarios", usuarioService.reportesPorRol(rol));
        model.addAttribute("filtroRol", rol);
        return "usuario/listar";
    }

    @GetMapping("/reporte/nombre")
    public String reportePorNombre(@RequestParam String nombre, HttpSession session, Model model) {
        if (!checkSession(session)) return "redirect:/login";
        model.addAttribute("usuarios", usuarioService.reportesPorNombre(nombre));
        model.addAttribute("filtroNombre", nombre);
        return "usuario/listar";
    }
}
