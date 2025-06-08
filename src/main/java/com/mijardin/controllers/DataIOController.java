package com.mijardin.controllers;

import com.mijardin.entities.Planta;
import com.mijardin.services.DataIOService;
import com.mijardin.services.PlantaService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class DataIOController {
    private final DataIOService dataIOService;
    private final PlantaService plantaService;

    public DataIOController(DataIOService dataIOService, PlantaService plantaService) {
        this.dataIOService = dataIOService;
        this.plantaService = plantaService;
    }

    @FXML
    public void importarDatos() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo para importar");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos CSV", "*.csv"),
                new FileChooser.ExtensionFilter("Archivos XML", "*.xml")
        );

        File archivoSeleccionado = fileChooser.showOpenDialog(null);
        if (archivoSeleccionado != null) {
            try {
                List<Planta> plantasImportadas;
                if (archivoSeleccionado.getName().endsWith(".csv")) {
                    plantasImportadas = dataIOService.importarDesdeCSV(archivoSeleccionado);
                } else if (archivoSeleccionado.getName().endsWith(".xml")) {
                    plantasImportadas = dataIOService.importarDesdeXML(archivoSeleccionado);
                } else {
                    mostrarAlerta("Error", "Formato de archivo no soportado.");
                    return;
                }

                for (Planta planta : plantasImportadas) {
                    plantaService.crearPlanta(planta);
                }

                mostrarAlerta("Éxito", "Datos importados correctamente.");
            } catch (Exception e) {
                mostrarAlerta("Error", "Ocurrió un error al importar los datos: " + e.getMessage());
            }
        } else {
            mostrarAlerta("Información", "No se seleccionó ningún archivo.");
        }
    }

    @FXML
    public void exportarDatos() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar ubicación para exportar");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos CSV", "*.csv"),
                new FileChooser.ExtensionFilter("Archivos XML", "*.xml")
        );

        File archivo = fileChooser.showSaveDialog(null);
        if (archivo != null) {
            try {
                List<Planta> plantas = plantaService.listarPlantas();
                if (archivo.getName().endsWith(".csv")) {
                    dataIOService.exportarACSV(archivo, plantas);
                } else if (archivo.getName().endsWith(".xml")) {
                    dataIOService.exportarAXML(archivo, plantas);
                } else {
                    mostrarAlerta("Error", "Formato de archivo no soportado.");
                    return;
                }
                mostrarAlerta("Éxito", "Datos exportados correctamente.");
            } catch (Exception e) {
                mostrarAlerta("Error", "Ocurrió un error al exportar los datos: " + e.getMessage());
            }
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private List<Planta> obtenerPlantasParaExportar() {
        try {
            return plantaService.listarPlantas();
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudieron obtener las plantas para exportar: " + e.getMessage());
            return List.of();
        }
    }
}