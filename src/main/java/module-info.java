module bg.tuvarna.sit.usp_cars {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires log4j;
    requires org.hibernate.orm.core;
    requires java.persistence;

    requires java.naming;
    requires java.sql;


    exports bg.tuvarna.sit.usp_cars.application;
    opens bg.tuvarna.sit.usp_cars.application to javafx.fxml;
    exports bg.tuvarna.sit.usp_cars.presentation.controllers;
    opens bg.tuvarna.sit.usp_cars.presentation.controllers to javafx.fxml;
    exports bg.tuvarna.sit.usp_cars.presentation.models;
    opens bg.tuvarna.sit.usp_cars.presentation.models to javafx.fxml;
    opens bg.tuvarna.sit.usp_cars.data.entities to org.hibernate.orm.core;
    exports bg.tuvarna.sit.usp_cars.data.entities;

    opens bg.tuvarna.sit.usp_cars.data.access to org.hibernate.orm.core;
    exports bg.tuvarna.sit.usp_cars.data.access;
}