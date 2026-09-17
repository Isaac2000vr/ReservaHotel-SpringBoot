package com.reservahotel.repository;

import com.reservahotel.entity.ReservaHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaHotelRepository extends JpaRepository<ReservaHotel, Integer> {

    List<ReservaHotel> findByCiudadContainingIgnoreCase(String ciudad);

    List<ReservaHotel> findByEmpleadoAtiendeIdAndFechaInicioBetween(Integer empleadoId, LocalDate desde, LocalDate hasta);
}
