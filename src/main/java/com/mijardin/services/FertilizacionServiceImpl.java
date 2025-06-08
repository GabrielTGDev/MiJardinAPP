package com.mijardin.services;

import com.mijardin.dao.FertilizacionDAO;
import com.mijardin.entities.Fertilizacion;

import java.util.List;

public class FertilizacionServiceImpl implements FertilizacionService {
    private final FertilizacionDAO fertilizacionDAO;

    public FertilizacionServiceImpl(FertilizacionDAO fertilizacionDAO) {
        this.fertilizacionDAO = fertilizacionDAO;
    }

    @Override
    public Fertilizacion crearFertilizacion(Fertilizacion fertilizacion) {
        return fertilizacionDAO.save(fertilizacion);
    }

    @Override
    public Fertilizacion obtenerFertilizacionPorId(Long id) {
        return fertilizacionDAO.findById(id);
    }

    @Override
    public List<Fertilizacion> listarFertilizaciones() {
        return fertilizacionDAO.findAll();
    }

    @Override
    public Fertilizacion actualizarFertilizacion(Fertilizacion fertilizacion) {
        fertilizacionDAO.update(fertilizacion);
        return fertilizacion;
    }

    @Override
    public void eliminarFertilizacion(Long id) {
        Fertilizacion fertilizacion = fertilizacionDAO.findById(id);
        if (fertilizacion != null) {
            fertilizacionDAO.delete(fertilizacion);
        }
    }
}
