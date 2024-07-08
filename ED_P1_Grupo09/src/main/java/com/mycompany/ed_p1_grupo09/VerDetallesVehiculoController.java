/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ed_p1_grupo09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import modelo.ImageLoader;
import modelo.*;
import tda.*;

/**
 * FXML Controller class
 *
 * @author asala
 */
public class VerDetallesVehiculoController implements Initializable {
    private static Vehiculo vehiculo;

    @FXML
    private Label kilometrajeLabel;
    @FXML
    private Label modeloLabel;
    @FXML
    private Label ciudadLabel;
    @FXML
    private Label precioLabel;
    @FXML
    private Label yearLabel;
    @FXML
    private Label negociableLabel;
    @FXML
    private Label estadoLabel;
    @FXML
    private Label traccionLabel;
    @FXML
    private Label transmisionLabel;
    @FXML
    private Label tipoCombustibleLabel;
    @FXML
    private Label placaLabel;
    @FXML
    private Label climatizadoLabel;
    @FXML
    private Label tipoMotorLabel;
    @FXML
    private VBox contenedorAccidentes; 
    @FXML
    private HBox contenedorImagenes; 

    private CircularDoublyLinkedList<Image> imagenes = new CircularDoublyLinkedList<>(); // Inicializar la lista
    private int currentIndex;

    public static void setVehiculo(Vehiculo vehiculo) {
        VerDetallesVehiculoController.vehiculo = vehiculo;
    }
    
    @FXML
    private void getPrevious() {
        if (imagenes != null && !imagenes.isEmpty()) { // Verificar si imagenes no es nulo
            currentIndex--;
            if (currentIndex < 0) {
                currentIndex = imagenes.getSize() - 1; // Volvemos al final de la lista circular
            }
            mostrarImagenes();
        }
    }
     
    @FXML
    private void getNext() {
        if (imagenes != null && !imagenes.isEmpty()) { // Verificar si imagenes no es nulo
            currentIndex++;
            if (currentIndex >= imagenes.getSize()) {
                currentIndex = 0; // Volvemos al inicio de la lista circular
            }
            mostrarImagenes();
        }
    }

    private void mostrarImagenes() {
        contenedorImagenes.getChildren().clear(); // Limpiamos el contenedor

        // Iterar sobre las primeras tres imágenes y agregarlas al HBox
        int size = Math.min(imagenes.getSize(), 3); // Tomamos el mínimo entre el tamaño de imágenes y 3
        for (int i = 0; i < size; i++) {
            int index = (currentIndex + i) % imagenes.getSize(); // Calcular el índice ajustado circularmente
            Image image = imagenes.get(index);

            // Crear ImageView y configurar propiedades
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(150); // Ajustar el ancho de la imagen según sea necesario
            imageView.setPreserveRatio(true); // Mantener la proporción de la imagen

            // Agregar ImageView al HBox
            contenedorImagenes.getChildren().add(imageView);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarDetalles();
    }  
    
    private void mostrarDetalles() {
        if (vehiculo != null) {
            kilometrajeLabel.setText(String.valueOf(vehiculo.getKilometraje()));
            modeloLabel.setText(vehiculo.getModelo());
            ciudadLabel.setText(vehiculo.getCiudad());
            precioLabel.setText(String.valueOf(vehiculo.getPrecio()));
            yearLabel.setText(String.valueOf(vehiculo.getYear()));
            
            if (vehiculo.isNegociable()){
                negociableLabel.setText("Negociable");
            } else{
                negociableLabel.setText("Fijo");
            }
            estadoLabel.setText(String.valueOf(vehiculo.getEstado()));      
            traccionLabel.setText(vehiculo.getDetallesInt().getTraccion());
            transmisionLabel.setText(vehiculo.getDetallesInt().getTransmision());
            tipoCombustibleLabel.setText(String.valueOf(vehiculo.getDetallesInt().getCombustible()));
            placaLabel.setText(vehiculo.getDetallesInt().getPlaca());
            if (vehiculo.getDetallesInt().isClimatizado()){
                climatizadoLabel.setText("Climatizado");
            } else{
                climatizadoLabel.setText("No climatizado");
            }
            
            tipoMotorLabel.setText(vehiculo.getDetallesInt().getTipoMotor());

            
            
            imagenes = ImageLoader.loadImagesFromFolder("src/main/resources/imagenes/vehiculos/" + vehiculo.getId());

            if (!imagenes.isEmpty()) {
                currentIndex = 0; // Empezamos desde la primera imagen
                mostrarImagenes();
            }
            
            mostrarAccidentes();
        }
    }
    
    private void mostrarAccidentes() {
    contenedorAccidentes.getChildren().clear(); // Limpiar el contenedor de accidentes

    for (Accidente accidente : vehiculo.getAccidentes()) {
        HBox accidenteHBox = new HBox();
        accidenteHBox.setSpacing(10);
        accidenteHBox.setStyle("-fx-padding: 10; -fx-border-style: solid inside; -fx-border-width: 2; -fx-border-insets: 5; -fx-border-radius: 5; -fx-border-color: gray;");
        accidenteHBox.setAlignment(Pos.CENTER_LEFT); // Alinear el contenido del HBox a la izquierda

        VBox accidenteBox = new VBox();
        accidenteBox.setSpacing(5);

        Label accidenteDescripcion = new Label("Descripción: " + accidente.getDescripcion());
        Label accidenteParteAfectada = new Label("Parte Afectada: " + accidente.getParteAfectada());
        Label accidenteFecha = new Label("Fecha: " + accidente.getFechaAccidente());

        accidenteBox.getChildren().addAll(accidenteDescripcion, accidenteParteAfectada, accidenteFecha);

        // Crear un botón para ver los mantenimientos y centrarlo vertical y horizontalmente
        Button verMantenimientosBtn = new Button("Ver Procesos");
        verMantenimientosBtn.setOnAction(event -> {
            try {
                VerMantenimientosController.setAccidente(accidente);
                App.setRoot("verMantenimientos");
            } catch (IOException e) {
                System.err.println("Error al cargar mantenimientos: " + e.getMessage());
            }
        });

        VBox btnContainer = new VBox(verMantenimientosBtn);
        btnContainer.setAlignment(Pos.CENTER); // Centrar el botón vertical y horizontalmente
        btnContainer.setPrefWidth(150); // Ajustar el ancho del contenedor del botón
        btnContainer.setPrefHeight(80); // Ajustar la altura del contenedor del botón

        accidenteHBox.getChildren().addAll(accidenteBox, btnContainer);
        accidenteHBox.setAlignment(Pos.CENTER); // Asegurar que el contenido esté centrado
        contenedorAccidentes.getChildren().add(accidenteHBox);
        }
    }






      
    @FXML
    private void volver() throws IOException {
        App.setRoot("inicio");
    }   
}