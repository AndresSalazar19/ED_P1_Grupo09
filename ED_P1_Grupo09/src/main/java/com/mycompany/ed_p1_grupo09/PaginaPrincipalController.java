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
import modelo.*;
import tda.*;

/**
 * FXML Controller class
 *
 * @author asala
 */

public class PaginaPrincipalController implements Initializable {
    
    @FXML
    private Label correoLabel;
    
    public void setCorreo(){
      try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/archivos/loggedArchivos.csv"))) {
            String linea;
            linea = br.readLine();
            correoLabel.setText(linea);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String getCorreo(){
     try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/archivos/loggedArchivos.csv"))) {
            String linea;
            linea = br.readLine();
            return linea;
        } catch (IOException e) {
            e.printStackTrace();
        }
     return "null";
    }
      
     @FXML
    private void añadirVehiculo() throws IOException {
        App.setRoot("añadirVehiculo");
    }
     @FXML
    private void cerrarSesion() throws IOException {
        App.setRoot("Inicio");
    }
    
    @FXML
    private HBox contenedorImagenes; // Este será el contenedor en el FXML

    private CircularDoublyLinkedList<Image> imagenes;
    private int currentIndex;
    
    
        @Override
    public void initialize(URL url, ResourceBundle rb) {
         // Cargar las imágenes desde una carpeta
       
        System.out.println(getCorreo());
        setCorreo();
          
        imagenes = ImageLoader.loadImagesFromFolder("src/main/resources/imagenes");

        if (!imagenes.isEmpty()) {
            currentIndex = 0; // Empezamos desde la primera imagen
            mostrarImagenes();
        }
    }
    

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




}
