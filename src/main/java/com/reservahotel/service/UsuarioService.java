package com.reservahotel.service;

import com.reservahotel.entity.Usuario;
import com.reservahotel.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminar(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> iniciarSesion(String email, String clave) {
        return usuarioRepository.findByEmailAndClave(email, clave);
    }

    public String recuperarClave(String email) throws Exception {
        Optional<Usuario> userOpt = usuarioRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            Usuario usuario = userOpt.get();
            String claveTemp = "temp" + (1000 + (int)(Math.random() * 9000));
            usuario.setClave(claveTemp);
            usuarioRepository.save(usuario);
            return "Nueva clave temporal asignada: " + claveTemp;
        } else {
            throw new Exception("El correo no se encuentra registrado.");
        }
    }

    public List<Usuario> reportesPorRol(String rol) {
        return usuarioRepository.findByRol(rol);
    }

    public List<Usuario> reportesPorNombre(String nombre) {
        return usuarioRepository.findByNombreContainingIgnoreCase(nombre);
    }
}
