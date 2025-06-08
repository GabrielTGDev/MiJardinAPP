package com.mijardin.controllers;

import com.mijardin.entities.Ubicacion;
import com.mijardin.services.UbicacionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;

import java.util.List;
import java.util.Optional;

public class UbicacionController {
    private final UbicacionService ubicacionService;

    @FXML
    private TableView<Ubicacion> tablaUbicaciones;

    public UbicacionController(UbicacionService ubicacionService) {
        this.ubicacionService = ubicacionService;
    }

    @FXML
    public void initialize() {
        cargarUbicaciones();
    }

    private void cargarUbicaciones() {
        List<Ubicacion> ubicaciones = ubicacionService.listarUbicaciones();
        ObservableList<Ubicacion> observableUbicaciones = FXCollections.observableArrayList(ubicaciones);
        tablaUbicaciones.setItems(observableUbicaciones);
    }

    @FXML
    public void crearUbicacion() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Agregar Ubicación");
        dialog.setHeaderText("Ingrese el nombre de la nueva ubicación:");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(nombre -> {
            Ubicacion nuevaUbicacion = new Ubicacion();
            nuevaUbicacion.setNombre(nombre);
            ubicacionService.crearUbicacion(nuevaUbicacion);
            cargarUbicaciones();
            mostrarAlerta("Ubicación creada", "La ubicación fue creada exitosamente.");
        });
    }

    @FXML
    public void actualizarUbicacion() {
        Ubicacion ubicacionSeleccionada = tablaUbicaciones.getSelectionModel().getSelectedItem();
        if (ubicacionSeleccionada == null) {
            mostrarAlerta("Error", "Debe seleccionar una ubicación para actualizar.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog(ubicacionSeleccionada.getNombre());
        dialog.setTitle("Editar Ubicación");
        dialog.setHeaderText("Modifique el nombre de la ubicación:");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(nombre -> {
            ubicacionSeleccionada.setNombre(nombre);
            ubicacionService.actualizarUbicacion(ubicacionSeleccionada);
            cargarUbicaciones();
            mostrarAlerta("Ubicación actualizada", "La ubicación fue actualizada exitosamente.");
        });
    }

    @FXML
    public void eliminarUbicacion() {
        Ubicacion ubicacionSeleccionada = tablaUbicaciones.getSelectionModel().getSelectedItem();
        if (ubicacionSeleccionada == null) {
            mostrarAlerta("Error", "Debe seleccionar una ubicación para eliminar.");
            return;
        }

        ubicacionService.eliminarUbicacion(ubicacionSeleccionada.getId().longValue());
        cargarUbicaciones();
        mostrarAlerta("Ubicación eliminada", "La ubicación fue eliminada exitosamente.");
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}