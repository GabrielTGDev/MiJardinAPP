package com.mijardin;

import com.mijardin.controllers.DashboardController;
import com.mijardin.dao.PlantaDAOImpl;
import com.mijardin.services.PlantaService;
import com.mijardin.services.PlantaServiceImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        PlantaService plantaService = new PlantaServiceImpl(new PlantaDAOImpl(sessionFactory));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mijardin/DashboardView.fxml"));
        loader.setControllerFactory(param -> new DashboardController(plantaService));

        Scene scene = new Scene(loader.load(), 600, 700);
        scene.getStylesheets().add(getClass().getResource("/com/mijardin/css/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Mi Jardín - Dashboard");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}