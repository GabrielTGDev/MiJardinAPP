package com.mijardin.controllers;

import com.mijardin.entities.Planta;
import com.mijardin.services.PlantaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;

import java.util.List;
import java.util.Optional;

public class PlantaController {
    private final PlantaService plantaService;

    @FXML
    private TableView<Planta> tablaPlantas;

    public PlantaController(PlantaService plantaService) {
        this.plantaService = plantaService;
    }

    @FXML
    public void initialize() {
        cargarPlantas();
    }

    private void cargarPlantas() {
        List<Planta> plantas = plantaService.listarPlantas();
        ObservableList<Planta> observablePlantas = FXCollections.observableArrayList(plantas);
        tablaPlantas.setItems(observablePlantas);
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
}