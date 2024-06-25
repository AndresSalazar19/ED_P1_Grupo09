/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ed_p1_grupo09;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import modelo.ImageLoader;
import tda.*;

/**
 * FXML Controller class
 *
 * @author asala
 */
public class VerDetallesVehiculoController implements Initializable {
    
    @FXML
    private HBox contenedorImagenes; // Este será el contenedor en el FXML

    private CircularDoublyLinkedList<Image> imagenes;
    private int currentIndex;
    
    @FXML
    private void getPrevious() {
        if (!imagenes.isEmpty()) {
            currentIndex--;
            if (currentIndex < 0) {
                currentIndex = imagenes.getSize() - 1; // Volvemos al final de la lista circular
            }
            mostrarImagenes();
        }
    }
     
     @FXML
    private void getNext() {
        if (!imagenes.isEmpty()) {
            currentIndex++;
            if (currentIndex >= imagenes.getSize()) {
                currentIndex = 0; // Volvemos al inicio de la lista circular
            }
            mostrarImagenes();
        }
    }
     private void mostrarImagenes() {
        contenedorImagenes.getChildren().clear(); // Limpiamos el contenedor

        // Iterar sobre todas las imágenes y agregarlas al HBox
        int size = imagenes.getSize();
        for (int i = 0; i < size; i++) {
            int index = (currentIndex + i) % size; // Calcular el índice ajustado circularmente
            Image image = imagenes.get(index);

            // Crear ImageView y configurar propiedades
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(200); // Ajustar el ancho de la imagen según sea necesario
            imageView.setPreserveRatio(true); // Mantener la proporción de la imagen

            // Agregar ImageView al HBox
            contenedorImagenes.getChildren().add(imageView);
        }
    }
    
    
    
        @Override
        public void initialize(URL url, ResourceBundle rb) {
         // Cargar las imágenes desde una carpeta
          
        imagenes = ImageLoader.loadImagesFromFolder("src/main/resources/imagenes");

        if (!imagenes.isEmpty()) {
            currentIndex = 0; // Empezamos desde la primera imagen
            mostrarImagenes();
        }
    }  
    
}
