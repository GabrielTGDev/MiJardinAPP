package com.mijardin.controllers;

import com.mijardin.entities.Planta;
import com.mijardin.services.PlantaService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class DashboardController {

    private final PlantaService plantaService;

    @FXML
    private Label plantsNeedingWateringLabel;

    @FXML
    private Label plantsNeedingFertilizationLabel;

    @FXML
    private TableView<Planta> wateringTable;

    @FXML
    private TableColumn<Planta, String> wateringPlantNameColumn;

    @FXML
    private TableColumn<Planta, LocalDate> wateringDateColumn;

    @FXML
    private TableView<Planta> fertilizationTable;

    @FXML
    private TableColumn<Planta, String> fertilizationPlantNameColumn;

    @FXML
    private TableColumn<Planta, LocalDate> fertilizationDateColumn;

    public DashboardController(PlantaService plantaService) {
        this.plantaService = plantaService;
    }

    @FXML
    public void initialize() {
        cargarDatosDashboard();
    }

    /**
     * Carga los datos iniciales del dashboard, incluyendo contadores y tablas.
     */
    private void cargarDatosDashboard() {
        actualizarContadores();
        cargarTablas();
    }

    /**
     * Actualiza los contadores de plantas que necesitan riego y fertilización hoy.
     */
    private void actualizarContadores() {
        // Actualizar etiquetas con el conteo de plantas que necesitan riego y fertilización hoy
        long plantasRiegoHoy = plantaService.listarPlantas().stream()
                .filter(planta -> planta.getUltimoRiegoFecha() != null &&
                        (
                                planta.getUltimoRiegoFecha().plusDays(planta.getFrecuenciaRiegoDias()).isEqual(LocalDate.now())
                                || planta.getUltimoRiegoFecha().plusDays(planta.getFrecuenciaRiegoDias()).isBefore(LocalDate.now())
                        )
                )
                .count();

        long plantasFertilizacionHoy = plantaService.listarPlantas().stream()
                .filter(planta -> planta.getUltimaFertilizacionFecha() != null &&
                        (
                                planta.getUltimaFertilizacionFecha().plusDays(planta.getFrecuenciaFertilizacionDias()).isEqual(LocalDate.now())
                                || planta.getUltimaFertilizacionFecha().plusDays(planta.getFrecuenciaFertilizacionDias()).isBefore(LocalDate.now())
                        )
                )
                .count();

        plantsNeedingWateringLabel.setText(String.valueOf(plantasRiegoHoy));
        plantsNeedingFertilizationLabel.setText(String.valueOf(plantasFertilizacionHoy));
    }

    /**
     * Carga las tablas de riego y fertilización con las plantas que necesitan atención hoy.
     */
    private void cargarTablas() {
        // Inicializamos las columnas de las tablas
        wateringPlantNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        wateringDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(
                cellData.getValue().getUltimoRiegoFecha().plusDays(cellData.getValue().getFrecuenciaRiegoDias())));

        // Inicializamos las columnas de la tabla de fertilización
        fertilizationPlantNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        fertilizationDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(
                cellData.getValue().getUltimaFertilizacionFecha().plusDays(cellData.getValue().getFrecuenciaFertilizacionDias())));

        // Cargamos las plantas que necesitan riego y fertilización hoy
        List<Planta> plantas = plantaService.listarPlantas();

        List<Planta> plantasRiego = plantas.stream()
                .filter(planta -> planta.getUltimoRiegoFecha() != null &&
                        planta.getUltimoRiegoFecha().plusDays(planta.getFrecuenciaRiegoDias()).isAfter(LocalDate.now()))
                .toList();

        List<Planta> plantasFertilizacion = plantas.stream()
                .filter(planta -> planta.getUltimaFertilizacionFecha() != null &&
                        planta.getUltimaFertilizacionFecha().plusDays(planta.getFrecuenciaFertilizacionDias()).isAfter(LocalDate.now()))
                .toList();

        wateringTable.setItems(FXCollections.observableArrayList(plantasRiego));
        wateringTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        wateringTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        fertilizationTable.setItems(FXCollections.observableArrayList(plantasFertilizacion));
        fertilizationTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        fertilizationTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Agregar manejador de doble clic para wateringTable
        wateringTable.setRowFactory(tv -> {
            TableRow<Planta> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Planta plantaSeleccionada = row.getItem();
                    abrirDetallesPlanta(plantaSeleccionada);
                }
            });
            return row;
        });

        // Agregar manejador de doble clic para fertilizationTable
        fertilizationTable.setRowFactory(tv -> {
            TableRow<Planta> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Planta plantaSeleccionada = row.getItem();
                    abrirDetallesPlanta(plantaSeleccionada);
                }
            });
            return row;
        });
    }

    /**
     * Abre la vista PlantaView con los detalles de la planta seleccionada.
     */
    private void abrirDetallesPlanta(Planta planta) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mijardin/PlantasView.fxml"));
            loader.setControllerFactory(param -> new PlantaController(plantaService)); // Inyecta el servicio

            Stage stage = new Stage();
            Scene scene = new Scene(loader.load(), 900, 400);
            scene.getStylesheets().add(getClass().getResource("/com/mijardin/css/plantlist.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Detalles de la Planta");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}