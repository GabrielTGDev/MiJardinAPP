package com.mijardin.controllers;

import com.mijardin.entities.Fertilizacion;
import com.mijardin.services.FertilizacionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

        public class FertilizacionController {
            private final FertilizacionService fertilizacionService;

            @FXML
            private TableView<Fertilizacion> tablaFertilizaciones;

            public FertilizacionController(FertilizacionService fertilizacionService) {
                this.fertilizacionService = fertilizacionService;
            }

            @FXML
            public void initialize() {
                cargarFertilizaciones();
            }

            private void cargarFertilizaciones() {
                List<Fertilizacion> fertilizaciones = fertilizacionService.listarFertilizaciones();
                ObservableList<Fertilizacion> observableFertilizaciones = FXCollections.observableArrayList(fertilizaciones);
                tablaFertilizaciones.setItems(observableFertilizaciones);
            }

            @FXML
            public void crearFertilizacion() {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Agregar Fertilización");
                dialog.setHeaderText("Ingrese el tipo de fertilizante:");
                Optional<String> result = dialog.showAndWait();

                result.ifPresent(tipo -> {
                    Fertilizacion nuevaFertilizacion = new Fertilizacion();
                    nuevaFertilizacion.setFecha(LocalDate.now());
                    nuevaFertilizacion.setTipo(tipo);
                    fertilizacionService.crearFertilizacion(nuevaFertilizacion);
                    cargarFertilizaciones();
                    mostrarAlerta("Fertilización creada", "La fertilización fue creada exitosamente.");
                });
            }

            @FXML
            public void actualizarFertilizacion() {
                Fertilizacion fertilizacionSeleccionada = tablaFertilizaciones.getSelectionModel().getSelectedItem();
                if (fertilizacionSeleccionada == null) {
                    mostrarAlerta("Error", "Debe seleccionar una fertilización para actualizar.");
                    return;
                }

                TextInputDialog dialog = new TextInputDialog(fertilizacionSeleccionada.getTipo());
                dialog.setTitle("Editar Fertilización");
                dialog.setHeaderText("Modifique el tipo de fertilizante:");
                Optional<String> result = dialog.showAndWait();

                result.ifPresent(tipo -> {
                    fertilizacionSeleccionada.setTipo(tipo);
                    fertilizacionService.actualizarFertilizacion(fertilizacionSeleccionada);
                    cargarFertilizaciones();
                    mostrarAlerta("Fertilización actualizada", "La fertilización fue actualizada exitosamente.");
                });
            }

            @FXML
            public void eliminarFertilizacion() {
                Fertilizacion fertilizacionSeleccionada = tablaFertilizaciones.getSelectionModel().getSelectedItem();
                if (fertilizacionSeleccionada == null) {
                    mostrarAlerta("Error", "Debe seleccionar una fertilización para eliminar.");
                    return;
                }

                fertilizacionService.eliminarFertilizacion(fertilizacionSeleccionada.getId().longValue());
                cargarFertilizaciones();
                mostrarAlerta("Fertilización eliminada", "La fertilización fue eliminada exitosamente.");
            }

            private void mostrarAlerta(String titulo, String mensaje) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(titulo);
                alert.setContentText(mensaje);
                alert.showAndWait();
            }
        }