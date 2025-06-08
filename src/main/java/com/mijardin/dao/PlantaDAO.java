package com.mijardin.dao;

import com.mijardin.entities.Planta;

import java.util.List;

public interface PlantaDAO {
    Planta save(Planta planta);
    Planta findById(Long id);
    List<Planta> findAll();
    void update(Planta planta);
    void delete(Planta planta);
}