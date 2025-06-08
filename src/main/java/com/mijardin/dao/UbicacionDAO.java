package com.mijardin.dao;

import com.mijardin.entities.Ubicacion;

import java.util.List;

public interface UbicacionDAO {
    Ubicacion save(Ubicacion ubicacion);
    Ubicacion findById(Long id);
    List<Ubicacion> findAll();
    void update(Ubicacion ubicacion);
    void delete(Ubicacion ubicacion);
}
