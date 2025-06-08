package com.mijardin.dao;

import com.mijardin.entities.Fertilizacion;

import java.util.List;

public interface FertilizacionDAO {
    Fertilizacion save(Fertilizacion fertilizacion);
    Fertilizacion findById(Long id);
    List<Fertilizacion> findAll();
    void update(Fertilizacion fertilizacion);
    void delete(Fertilizacion fertilizacion);
}
