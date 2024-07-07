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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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
            System.out.println("KilometrajeLabel: " + vehiculo.getKilometraje());
            kilometrajeLabel.setText(String.valueOf(vehiculo.getKilometraje()));
            modeloLabel.setText(vehiculo.getModelo());
            ciudadLabel.setText(vehiculo.getCiudad());
            precioLabel.setText(String.valueOf(vehiculo.getPrecio()));
            yearLabel.setText(String.valueOf(vehiculo.getYear()));

            imagenes = ImageLoader.loadImagesFromFolder("src/main/resources/imagenes/vehiculos/" + vehiculo.getId());

            if (!imagenes.isEmpty()) {
                currentIndex = 0; // Empezamos desde la primera imagen
                mostrarImagenes();
            }
        }
    }

    @FXML
    private void volver() throws IOException {
        App.setRoot("inicio");
    }   
}