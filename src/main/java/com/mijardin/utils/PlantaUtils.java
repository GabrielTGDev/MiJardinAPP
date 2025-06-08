package com.mijardin.utils;

import com.mijardin.entities.Planta;

public class PlantaUtils {

    public static boolean esValido(String[] data) {
        return data.length >= 4 &&
                !data[0].isEmpty() &&
                !data[1].isEmpty() &&
                esNumeroValido(data[2]) &&
                esNumeroValido(data[3]);
    }

    public static boolean esNumeroValido(String valor) {
        try {
            Integer.parseInt(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Planta crearPlantaDesdeDatos(String[] data) {
        Planta planta = new Planta();
        planta.setNombre(data[0]);
        planta.setEspecie(data[1]);
        planta.setFrecuenciaRiegoDias(Integer.parseInt(data[2]));
        planta.setFrecuenciaFertilizacionDias(Integer.parseInt(data[3]));
        return planta;
    }
}