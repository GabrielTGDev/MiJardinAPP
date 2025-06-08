package com.mijardin.controllers;

import com.mijardin.entities.Planta;
import com.mijardin.services.PlantaService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class PlantaController {
    private PlantaService plantaService;

    @FXML
    private TableView<Planta> tablaPlantas;

    @FXML
    private TableColumn<Planta, String> colNombre;
    @FXML
    private TableColumn<Planta, String> colEspecie;
    @FXML
    private TableColumn<Planta, LocalDate> colFechaAdquisicion;
    @FXML
    private TableColumn<Planta, String> colUbicacion;
    @FXML
    private TableColumn<Planta, LocalDate> colUltimoRiego;
    @FXML
    private TableColumn<Planta, LocalDate> colProximoRiego;
    @FXML
    private TableColumn<Planta, LocalDate> colUltimaFertilizacion;
    @FXML
    private TableColumn<Planta, LocalDate> colProximaFertilizacion;

    public PlantaController() {
    }

    public PlantaController(PlantaService plantaService) {
        this.plantaService = plantaService;
    }

    @FXML
    public void initialize() {


        cargarPlantas();
    }

    private void cargarPlantas() {
        configurarColumnas();
        List<Planta> plantas = plantaService.listarPlantas();
        ObservableList<Planta> observablePlantas = FXCollections.observableArrayList(plantas);
        tablaPlantas.setItems(observablePlantas);
        tablaPlantas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tablaPlantas.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private void configurarColumnas() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEspecie.setCellValueFactory(new PropertyValueFactory<>("especie"));
        colFechaAdquisicion.setCellValueFactory(new PropertyValueFactory<>("fechaAdquisicion"));
        colUbicacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUbicacion().getNombre()));
        colUltimoRiego.setCellValueFactory(new PropertyValueFactory<>("ultimoRiegoFecha"));
        colProximoRiego.setCellValueFactory(cellData -> new SimpleObjectProperty<>(
                cellData.getValue().getUltimoRiegoFecha().plusDays(cellData.getValue().getFrecuenciaRiegoDias())));
        colUltimaFertilizacion.setCellValueFactory(new PropertyValueFactory<>("ultimaFertilizacionFecha"));
        colProximaFertilizacion.setCellValueFactory(cellData -> new SimpleObjectProperty<>(
                cellData.getValue().getUltimaFertilizacionFecha().plusDays(cellData.getValue().getFrecuenciaFertilizacionDias())));
    }

    @FXML
    public void crearPlanta() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Agregar Planta");
        dialog.setHeaderText("Ingrese el nombre de la nueva planta:");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(nombre -> {
            Planta nuevaPlanta = new Planta();
            nuevaPlanta.setNombre(nombre);
            plantaService.crearPlanta(nuevaPlanta);
            cargarPlantas();
            mostrarAlerta("Planta creada", "La planta fue creada exitosamente.");
        });
    }

    @FXML
    public void actualizarPlanta() {
        Planta plantaSeleccionada = tablaPlantas.getSelectionModel().getSelectedItem();
        if (plantaSeleccionada == null) {
            mostrarAlerta("Error", "Debe seleccionar una planta para actualizar.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog(plantaSeleccionada.getNombre());
        dialog.setTitle("Editar Planta");
        dialog.setHeaderText("Modifique el nombre de la planta:");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(nombre -> {
            plantaSeleccionada.setNombre(nombre);
            plantaService.actualizarPlanta(plantaSeleccionada);
            cargarPlantas();
            mostrarAlerta("Planta actualizada", "La planta fue actualizada exitosamente.");
        });
    }

    @FXML
    public void eliminarPlanta() {
        Planta plantaSeleccionada = tablaPlantas.getSelectionModel().getSelectedItem();
        if (plantaSeleccionada == null) {
            mostrarAlerta("Error", "Debe seleccionar una planta para eliminar.");
            return;
        }

        plantaService.eliminarPlanta(plantaSeleccionada.getId().longValue());
        cargarPlantas();
        mostrarAlerta("Planta eliminada", "La planta fue eliminada exitosamente.");
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void setPlanta(Planta planta) {
        // Aquí puedes inicializar los campos de la vista con los datos de la planta
        System.out.println("Planta seleccionada: " + planta.getNombre());
    }

    public void setPlantaService(PlantaService plantaService) {
        this.plantaService = plantaService;
    }
}