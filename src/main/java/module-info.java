module com.mijardin {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires java.naming;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    opens com.mijardin to javafx.fxml;
    exports com.mijardin;
}