package com.mijardin.services;

import com.mijardin.entities.Ubicacion;

import java.util.List;


public interface UbicacionService {
    Ubicacion crearUbicacion(Ubicacion ubicacion);
    Ubicacion obtenerUbicacionPorId(Long id);
    List<Ubicacion> listarUbicaciones();
    Ubicacion actualizarUbicacion(Ubicacion ubicacion);
    void eliminarUbicacion(Long id);
}
