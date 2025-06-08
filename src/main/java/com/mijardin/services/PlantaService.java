package com.mijardin.services;

import com.mijardin.entities.Planta;

import java.util.List;

public interface PlantaService {
    Planta crearPlanta(Planta planta);
    Planta obtenerPlantaPorId(Long id);
    List<Planta> listarPlantas();
    Planta actualizarPlanta(Planta planta);
    void eliminarPlanta(Long id);
}