module BarBestial {

    requires javafx.fxml;
    requires javafx.controls;
    requires java.desktop;
    requires org.json;
    requires java.sql;
    requires java.mail;

    requires java.activation;
    requires javafx.web;
    requires org.twitter4j.core;

    opens packVista.sesion;
    opens packVista;
}
