package com.mijardin.handlers;

import com.mijardin.entities.Planta;
import com.mijardin.services.PlantaService;
import com.mijardin.utils.PlantaUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVHandler {
    private final PlantaService plantaService;

    public CSVHandler(PlantaService plantaService) {
        this.plantaService = plantaService;
    }

    public List<Planta> importarDesdeCSV(String filePath) throws IOException {
        List<Planta> plantas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine(); // Leer cabecera
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (!PlantaUtils.esValido(data)) {
                    throw new IllegalArgumentException("Datos inválidos en la línea: " + line);
                }
                plantas.add(PlantaUtils.crearPlantaDesdeDatos(data));
            }
        }
        return plantas;
    }

    public void exportarACSV(String filePath, List<Planta> plantas) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Nombre,Especie,FrecuenciaRiego,FrecuenciaFertilizacion\n");
            for (Planta planta : plantas) {
                writer.write(String.format("%s,%s,%d,%d\n",
                        planta.getNombre(),
                        planta.getEspecie(),
                        planta.getFrecuenciaRiegoDias(),
                        planta.getFrecuenciaFertilizacionDias()));
            }
        }
    }
}