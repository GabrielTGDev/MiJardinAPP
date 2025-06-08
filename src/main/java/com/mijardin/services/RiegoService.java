package com.mijardin.services;

import com.mijardin.entities.Riego;

import java.util.List;

public interface RiegoService {
    Riego crearRiego(Riego riego);
    Riego obtenerRiegoPorId(Long id);
    List<Riego> listarRiegos();
    Riego actualizarRiego(Riego riego);
    void eliminarRiego(Long id);
}
