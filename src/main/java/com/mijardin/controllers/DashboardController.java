package com.mijardin.controllers;

import com.mijardin.services.FertilizacionService;
import com.mijardin.services.PlantaService;
import com.mijardin.services.RiegoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.List;

public class DashboardController {
    private final PlantaService plantaService;
    private final RiegoService riegoService;
    private final FertilizacionService fertilizacionService;

    @FXML
    private Label totalPlantasLabel;

    @FXML
    private Label plantasRiegoLabel;

    @FXML
    private Label plantasFertilizacionLabel;

    @FXML
    private Label mensajeEstadoLabel;

    @FXML
    private ListView<String> riegosPendientesList;

    @FXML
    private ListView<String> fertilizacionesPendientesList;

    public DashboardController(PlantaService plantaService, RiegoService riegoService, FertilizacionService fertilizacionService) {
        this.plantaService = plantaService;
        this.riegoService = riegoService;
        this.fertilizacionService = fertilizacionService;
    }

    @FXML
    public void initialize() {
        cargarResumen();
        cargarAlertas();
    }

    private void cargarResumen() {
        int totalPlantas = plantaService.listarPlantas().size();
        int plantasRiego = riegoService.listarRiegos().size(); // Filtrar por riegos pendientes
        int plantasFertilizacion = fertilizacionService.listarFertilizaciones().size(); // Filtrar por fertilizaciones pendientes

        totalPlantasLabel.setText(String.valueOf(totalPlantas));
        plantasRiegoLabel.setText(String.valueOf(plantasRiego));
        plantasFertilizacionLabel.setText(String.valueOf(plantasFertilizacion));

        if (plantasRiego == 0 && plantasFertilizacion == 0) {
            mensajeEstadoLabel.setText("¡Todo en orden, tus plantas están felices!");
        } else {
            mensajeEstadoLabel.setText("¡Atención! Hay tareas pendientes.");
        }
    }

    private void cargarAlertas() {
        List<String> riegosPendientes = List.of("Planta 1 - Hoy", "Planta 2 - Mañana"); // Simulación
        List<String> fertilizacionesPendientes = List.of("Planta 3 - En 3 días"); // Simulación

        ObservableList<String> riegosObservable = FXCollections.observableArrayList(riegosPendientes);
        ObservableList<String> fertilizacionesObservable = FXCollections.observableArrayList(fertilizacionesPendientes);

        riegosPendientesList.setItems(riegosObservable);
        fertilizacionesPendientesList.setItems(fertilizacionesObservable);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void crearPlanta() {
        mostrarAlerta("Añadir Nueva Planta", "Funcionalidad para añadir una nueva planta aún no implementada.");
    }

    @FXML
    private void verPlantas() {
        mostrarAlerta("Ver Todas las Plantas", "Funcionalidad para ver todas las plantas aún no implementada.");
    }
}