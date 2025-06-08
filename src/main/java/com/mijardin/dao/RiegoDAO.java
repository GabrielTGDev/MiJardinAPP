package com.mijardin.dao;

import com.mijardin.entities.Riego;

import java.util.List;

public interface RiegoDAO {
    Riego save(Riego riego);
    Riego findById(Long id);
    List<Riego> findAll();
    void update(Riego riego);
    void delete(Riego riego);
}
