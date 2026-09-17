package com.reservahotel.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reserva_hotel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaHotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false, length = 150)
    private String hotel;

    @Column(nullable = false, length = 150)
    private String huesped;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false, length = 20)
    private String habitacion;

    @Column(name = "num_acompanantes", nullable = false)
    private Integer numAcompanantes = 0;

    @Column(nullable = false, length = 80)
    private String pais;

    @Column(nullable = false, length = 80)
    private String departamento;

    @Column(nullable = false, length = 80)
    private String ciudad;

    @Column(name = "hora_checkin", nullable = false)
    private LocalTime horaCheckin;

    @Column(name = "hora_checkout", nullable = false)
    private LocalTime horaCheckout;

    @ManyToOne
    @JoinColumn(name = "empleado_atiende_id", nullable = false)
    private Usuario empleadoAtiende;

    @ManyToOne
    @JoinColumn(name = "empleado_despide_id")
    private Usuario empleadoDespide;

    @Column(length = 500)
    private String descripcion;
}
