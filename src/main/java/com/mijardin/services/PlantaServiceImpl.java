package com.mijardin.services;

import com.mijardin.dao.PlantaDAO;
import com.mijardin.entities.Planta;

import java.util.List;

public class PlantaServiceImpl implements PlantaService {
    private final PlantaDAO plantaDAO;

    public PlantaServiceImpl(PlantaDAO plantaDAO) {
        this.plantaDAO = plantaDAO;
    }

    @Override
    public Planta crearPlanta(Planta planta) {
        return plantaDAO.save(planta);
    }

    @Override
    public Planta obtenerPlantaPorId(Long id) {
        return plantaDAO.findById(id);
    }

    @Override
    public List<Planta> listarPlantas() {
        return plantaDAO.findAll();
    }

    @Override
    public Planta actualizarPlanta(Planta planta) {
        plantaDAO.update(planta);
        return planta;
    }

    @Override
    public void eliminarPlanta(Long id) {
        Planta planta = plantaDAO.findById(id);
        if (planta != null) {
            plantaDAO.delete(planta);
        }
    }
}