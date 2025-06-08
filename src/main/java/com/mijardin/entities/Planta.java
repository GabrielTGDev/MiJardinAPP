package com.mijardin.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "plantas")
public class Planta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "especie")
    private String especie;

    @Column(name = "fecha_adquisicion")
    private LocalDate fechaAdquisicion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ubicacion_id")
    private Ubicacion ubicacion;

    @Column(name = "requerimientos_luz_desc")
    private String requerimientosLuzDesc;

    @Column(name = "frecuencia_riego_dias")
    private Integer frecuenciaRiegoDias;

    @Column(name = "frecuencia_fertilizacion_dias")
    private Integer frecuenciaFertilizacionDias;

    @Column(name = "ultimo_riego_fecha")
    private LocalDate ultimoRiegoFecha;

    @Column(name = "ultima_fertilizacion_fecha")
    private LocalDate ultimaFertilizacionFecha;

    @Lob
    @Column(name = "notas", columnDefinition = "TEXT")
    private String notas;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @OneToMany(mappedBy = "planta")
    private Set<Fertilizacion> fertilizaciones = new LinkedHashSet<>();

    @OneToMany(mappedBy = "planta")
    private Set<Riego> riegos = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public LocalDate getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(LocalDate fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getRequerimientosLuzDesc() {
        return requerimientosLuzDesc;
    }

    public void setRequerimientosLuzDesc(String requerimientosLuzDesc) {
        this.requerimientosLuzDesc = requerimientosLuzDesc;
    }

    public Integer getFrecuenciaRiegoDias() {
        return frecuenciaRiegoDias;
    }

    public void setFrecuenciaRiegoDias(Integer frecuenciaRiegoDias) {
        this.frecuenciaRiegoDias = frecuenciaRiegoDias;
    }

    public Integer getFrecuenciaFertilizacionDias() {
        return frecuenciaFertilizacionDias;
    }

    public void setFrecuenciaFertilizacionDias(Integer frecuenciaFertilizacionDias) {
        this.frecuenciaFertilizacionDias = frecuenciaFertilizacionDias;
    }

    public LocalDate getUltimoRiegoFecha() {
        return ultimoRiegoFecha;
    }

    public void setUltimoRiegoFecha(LocalDate ultimoRiegoFecha) {
        this.ultimoRiegoFecha = ultimoRiegoFecha;
    }

    public LocalDate getUltimaFertilizacionFecha() {
        return ultimaFertilizacionFecha;
    }

    public void setUltimaFertilizacionFecha(LocalDate ultimaFertilizacionFecha) {
        this.ultimaFertilizacionFecha = ultimaFertilizacionFecha;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Set<Fertilizacion> getFertilizaciones() {
        return fertilizaciones;
    }

    public void setFertilizaciones(Set<Fertilizacion> fertilizaciones) {
        this.fertilizaciones = fertilizaciones;
    }

    public Set<Riego> getRiegos() {
        return riegos;
    }

    public void setRiegos(Set<Riego> riegos) {
        this.riegos = riegos;
    }

}