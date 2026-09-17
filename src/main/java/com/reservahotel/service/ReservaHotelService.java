package com.reservahotel.service;

import com.reservahotel.entity.ReservaHotel;
import com.reservahotel.repository.ReservaHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaHotelService {

    @Autowired
    private ReservaHotelRepository reservaHotelRepository;

    public List<ReservaHotel> listarTodas() {
        return reservaHotelRepository.findAll();
    }

    public Optional<ReservaHotel> buscarPorId(Integer id) {
        return reservaHotelRepository.findById(id);
    }

    public ReservaHotel guardar(ReservaHotel reserva) {
        return reservaHotelRepository.save(reserva);
    }

    public void eliminar(Integer id) {
        reservaHotelRepository.deleteById(id);
    }

    public List<ReservaHotel> reportesPorCiudad(String ciudad) {
        return reservaHotelRepository.findByCiudadContainingIgnoreCase(ciudad);
    }

    public List<ReservaHotel> reportesPorEmpleadoYRango(Integer empleadoId, LocalDate desde, LocalDate hasta) {
        return reservaHotelRepository.findByEmpleadoAtiendeIdAndFechaInicioBetween(empleadoId, desde, hasta);
    }
}
