package com.mijardin.services;

import com.mijardin.entities.Fertilizacion;

import java.util.List;

public interface FertilizacionService {
    Fertilizacion crearFertilizacion(Fertilizacion fertilizacion);
    Fertilizacion obtenerFertilizacionPorId(Long id);
    List<Fertilizacion> listarFertilizaciones();
    Fertilizacion actualizarFertilizacion(Fertilizacion fertilizacion);
    void eliminarFertilizacion(Long id);
}
