package com.mijardin;

import com.mijardin.controllers.DashboardController;
import com.mijardin.services.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Configurar Hibernate
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        // Crear servicios
        PlantaServiceImpl plantaService = new PlantaServiceImpl(new com.mijardin.dao.PlantaDAOImpl(sessionFactory));
        RiegoServiceImpl riegoService = new RiegoServiceImpl(new com.mijardin.dao.RiegoDAOImpl(sessionFactory));
        FertilizacionServiceImpl fertilizacionService = new FertilizacionServiceImpl(new com.mijardin.dao.FertilizacionDAOImpl(sessionFactory));

        // Configurar FXMLLoader para Dashboard
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mijardin/DashboardView.fxml"));
        fxmlLoader.setControllerFactory(param -> new DashboardController(plantaService, riegoService, fertilizacionService));

        // Configurar escena
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Mi Jardín - Dashboard");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}