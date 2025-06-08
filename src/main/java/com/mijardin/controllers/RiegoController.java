package com.mijardin.controllers;

import com.mijardin.entities.Riego;
import com.mijardin.services.RiegoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

        public class RiegoController {
            private final RiegoService riegoService;

            @FXML
            private TableView<Riego> tablaRiegos;

            public RiegoController(RiegoService riegoService) {
                this.riegoService = riegoService;
            }

            @FXML
            public void initialize() {
                cargarRiegos();
            }

            private void cargarRiegos() {
                List<Riego> riegos = riegoService.listarRiegos();
                ObservableList<Riego> observableRiegos = FXCollections.observableArrayList(riegos);
                tablaRiegos.setItems(observableRiegos);
            }

            @FXML
            public void crearRiego() {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Agregar Riego");
                dialog.setHeaderText("Ingrese la cantidad de agua en mililitros:");
                Optional<String> result = dialog.showAndWait();

                result.ifPresent(cantidad -> {
                    try {
                        Riego nuevoRiego = new Riego();
                        nuevoRiego.setFecha(LocalDate.now());
                        nuevoRiego.setCantidadMl(new BigDecimal(cantidad));
                        riegoService.crearRiego(nuevoRiego);
                        cargarRiegos();
                        mostrarAlerta("Riego creado", "El riego fue creado exitosamente.");
                    } catch (NumberFormatException e) {
                        mostrarAlerta("Error", "La cantidad debe ser un número válido.");
                    }
                });
            }

            @FXML
            public void actualizarRiego() {
                Riego riegoSeleccionado = tablaRiegos.getSelectionModel().getSelectedItem();
                if (riegoSeleccionado == null) {
                    mostrarAlerta("Error", "Debe seleccionar un riego para actualizar.");
                    return;
                }

                TextInputDialog dialog = new TextInputDialog(riegoSeleccionado.getCantidadMl().toString());
                dialog.setTitle("Editar Riego");
                dialog.setHeaderText("Modifique la cantidad de agua en mililitros:");
                Optional<String> result = dialog.showAndWait();

                result.ifPresent(cantidad -> {
                    try {
                        riegoSeleccionado.setCantidadMl(new BigDecimal(cantidad));
                        riegoService.actualizarRiego(riegoSeleccionado);
                        cargarRiegos();
                        mostrarAlerta("Riego actualizado", "El riego fue actualizado exitosamente.");
                    } catch (NumberFormatException e) {
                        mostrarAlerta("Error", "La cantidad debe ser un número válido.");
                    }
                });
            }

            @FXML
            public void eliminarRiego() {
                Riego riegoSeleccionado = tablaRiegos.getSelectionModel().getSelectedItem();
                if (riegoSeleccionado == null) {
                    mostrarAlerta("Error", "Debe seleccionar un riego para eliminar.");
                    return;
                }

                riegoService.eliminarRiego(riegoSeleccionado.getId().longValue());
                cargarRiegos();
                mostrarAlerta("Riego eliminado", "El riego fue eliminado exitosamente.");
            }

            private void mostrarAlerta(String titulo, String mensaje) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(titulo);
                alert.setContentText(mensaje);
                alert.showAndWait();
            }
        }