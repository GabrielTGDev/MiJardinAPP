package com.mijardin.services;

import com.mijardin.dao.UbicacionDAO;
import com.mijardin.entities.Ubicacion;

import java.util.List;

public class UbicacionServiceImpl implements UbicacionService {
    private final UbicacionDAO ubicacionDAO;

    public UbicacionServiceImpl(UbicacionDAO ubicacionDAO) {
        this.ubicacionDAO = ubicacionDAO;
    }

    @Override
    public Ubicacion crearUbicacion(Ubicacion ubicacion) {
        return ubicacionDAO.save(ubicacion);
    }

    @Override
    public Ubicacion obtenerUbicacionPorId(Long id) {
        return ubicacionDAO.findById(id);
    }

    @Override
    public List<Ubicacion> listarUbicaciones() {
        return ubicacionDAO.findAll();
    }

    @Override
    public Ubicacion actualizarUbicacion(Ubicacion ubicacion) {
        ubicacionDAO.update(ubicacion);
        return ubicacion;
    }

    @Override
    public void eliminarUbicacion(Long id) {
        Ubicacion ubicacion = ubicacionDAO.findById(id);
        if (ubicacion != null) {
            ubicacionDAO.delete(ubicacion);
        }
    }
}
