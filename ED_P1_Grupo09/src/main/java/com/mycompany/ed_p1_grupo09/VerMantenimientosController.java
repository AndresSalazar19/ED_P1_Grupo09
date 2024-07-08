/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ed_p1_grupo09;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tda.*;
import modelo.*;

/**
 * FXML Controller class
 *
 * @author asala
 */
public class VerMantenimientosController implements Initializable {
    private static Accidente accidente;

    @FXML
    private VBox contenedorMantenimientos;

    public static void setAccidente(Accidente accidente) {
        VerMantenimientosController.accidente = accidente;
    }

    @FXML
    private void volver() throws IOException {
        App.setRoot("verDetallesVehiculo");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarMantenimientos();
    }

    private void mostrarMantenimientos() {
        contenedorMantenimientos.getChildren().clear(); // Limpiar el contenedor de mantenimientos

        for (Mantenimiento mantenimiento : accidente.getListaMantenimiento()) {
            HBox mantenimientoHBox = new HBox();
            mantenimientoHBox.setSpacing(10);
            mantenimientoHBox.setStyle("-fx-padding: 10; -fx-border-style: solid inside; -fx-border-width: 2; -fx-border-insets: 5; -fx-border-radius: 5; -fx-border-color: gray;");
            mantenimientoHBox.setAlignment(Pos.CENTER_LEFT); // Alinear el contenido del HBox a la izquierda

            VBox mantenimientoBox = new VBox();
            mantenimientoBox.setSpacing(5);

            Label mantenimientoDescripcion = new Label("Descripción: " + mantenimiento.getDescripcion());
            Label mantenimientoTipo = new Label("Tipo: " + mantenimiento.getTipoMantenimiento());

            mantenimientoBox.getChildren().addAll(mantenimientoDescripcion, mantenimientoTipo);

            VBox btnContainer = new VBox();
            btnContainer.setAlignment(Pos.CENTER); // Centrar el contenedor vertical y horizontalmente
            btnContainer.setPrefWidth(150); // Ajustar el ancho del contenedor del botón
            btnContainer.setPrefHeight(80); // Ajustar la altura del contenedor del botón

            mantenimientoHBox.getChildren().addAll(mantenimientoBox, btnContainer);
            mantenimientoHBox.setAlignment(Pos.CENTER); // Asegurar que el contenido esté centrado
            contenedorMantenimientos.getChildren().add(mantenimientoHBox);
        }
    }
}

