package com.mijardin.services;

import com.mijardin.dao.RiegoDAO;
import com.mijardin.entities.Riego;

import java.util.List;

public class RiegoServiceImpl implements RiegoService{
    private final RiegoDAO riegoDAO;

    public RiegoServiceImpl(RiegoDAO riegoDAO) {
        this.riegoDAO = riegoDAO;
    }

    @Override
    public Riego crearRiego(Riego riego) {
        return riegoDAO.save(riego);
    }

    @Override
    public Riego obtenerRiegoPorId(Long id) {
        return riegoDAO.findById(id);
    }

    @Override
    public List<Riego> listarRiegos() {
        return riegoDAO.findAll();
    }

    @Override
    public Riego actualizarRiego(Riego riego) {
        riegoDAO.update(riego);
        return riego;
    }

    @Override
    public void eliminarRiego(Long id) {
        Riego riego = riegoDAO.findById(id);
        if (riego != null) {
            riegoDAO.delete(riego);
        }
    }
}
