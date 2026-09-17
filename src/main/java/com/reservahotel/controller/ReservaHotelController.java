package com.reservahotel.controller;

import com.reservahotel.entity.ReservaHotel;
import com.reservahotel.service.ReservaHotelService;
import com.reservahotel.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/reservas")
public class ReservaHotelController {

    @Autowired
    private ReservaHotelService reservaHotelService;

    @Autowired
    private UsuarioService usuarioService;

    private boolean checkSession(HttpSession session) {
        return session.getAttribute("usuarioLogin") != null;
    }

    @GetMapping
    public String listar(HttpSession session, Model model) {
        if (!checkSession(session)) return "redirect:/login";
        model.addAttribute("reservas", reservaHotelService.listarTodas());
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "reservahotel/listar";
    }

    @GetMapping("/nueva")
    public String formularioNueva(HttpSession session, Model model) {
        if (!checkSession(session)) return "redirect:/login";
        model.addAttribute("reserva", new ReservaHotel());
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "reservahotel/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute ReservaHotel reserva, HttpSession session) {
        if (!checkSession(session)) return "redirect:/login";
        reservaHotelService.guardar(reserva);
        return "redirect:/reservas?mensaje=Reserva+guardada+exitosamente";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, HttpSession session, Model model) {
        if (!checkSession(session)) return "redirect:/login";
        reservaHotelService.buscarPorId(id).ifPresent(r -> model.addAttribute("reserva", r));
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "reservahotel/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, HttpSession session) {
        if (!checkSession(session)) return "redirect:/login";
        reservaHotelService.eliminar(id);
        return "redirect:/reservas?mensaje=Reserva+eliminada";
    }

    @GetMapping("/reporte/ciudad")
    public String reportePorCiudad(@RequestParam String ciudad, HttpSession session, Model model) {
        if (!checkSession(session)) return "redirect:/login";
        model.addAttribute("reservas", reservaHotelService.reportesPorCiudad(ciudad));
        model.addAttribute("filtroCiudad", ciudad);
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "reservahotel/listar";
    }

    @GetMapping("/reporte/empleado")
    public String reportePorEmpleado(
            @RequestParam Integer empleadoId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate desde,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate hasta,
            HttpSession session, Model model) {
        if (!checkSession(session)) return "redirect:/login";
        model.addAttribute("reservas", reservaHotelService.reportesPorEmpleadoYRango(empleadoId, desde, hasta));
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "reservahotel/listar";
    }
}
