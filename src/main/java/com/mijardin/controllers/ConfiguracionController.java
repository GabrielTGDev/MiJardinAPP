package com.mijardin.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ConfiguracionController {

    @FXML
    private TextField campoUrlBD;

    @FXML
    private TextField campoUsuario;

    @FXML
    private PasswordField campoContrasena;

    @FXML
    public void probarConexion() {
        String url = campoUrlBD.getText();
        String usuario = campoUsuario.getText();
        String contrasena = campoContrasena.getText();

        if (url.isEmpty() || usuario.isEmpty()) {
            mostrarAlerta("Error", "La URL y el usuario son obligatorios para probar la conexión.");
            return;
        }

        try {
            // Simulación de prueba de conexión
            boolean conexionExitosa = probarConexionBD(url, usuario, contrasena);
            if (conexionExitosa) {
                mostrarAlerta("Éxito", "Conexión a la base de datos exitosa.");
            } else {
                mostrarAlerta("Error", "No se pudo conectar a la base de datos.");
            }
        } catch (Exception e) {
            mostrarAlerta("Error", "Ocurrió un error al probar la conexión: " + e.getMessage());
        }
    }

    @FXML
    public void guardarConfiguracion() {
        String url = campoUrlBD.getText();
        String usuario = campoUsuario.getText();
        String contrasena = campoContrasena.getText();

        if (url.isEmpty() || usuario.isEmpty()) {
            mostrarAlerta("Error", "La URL y el usuario son obligatorios para guardar la configuración.");
            return;
        }

        try {
            // Simulación de guardado de configuración
            guardarConfiguracionBD(url, usuario, contrasena);
            mostrarAlerta("Éxito", "Configuración guardada exitosamente.");
        } catch (Exception e) {
            mostrarAlerta("Error", "Ocurrió un error al guardar la configuración: " + e.getMessage());
        }
    }

    private boolean probarConexionBD(String url, String usuario, String contrasena) {
        // Aquí se implementaría la lógica real para probar la conexión a la base de datos
        return true; // Simulación de éxito
    }

    private void guardarConfiguracionBD(String url, String usuario, String contrasena) {
        // Aquí se implementaría la lógica real para guardar la configuración
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}