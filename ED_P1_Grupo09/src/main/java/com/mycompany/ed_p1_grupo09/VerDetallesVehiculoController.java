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
    
    private List<Carro> carros;
    private List<Moto> motos;
    private List<Acuatico> acuaticos;
    private List<Aereo> aereos;
    private List<Pesado> pesados;
    
    @FXML
    private HBox contenedorImagenes; 

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
    
    
     
       public String getIdVehiculoLogged(){
     try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/archivos/vehiculoLoggedArchivos.csv"))) {
            String linea;
            linea = br.readLine();
            return linea;
        } catch (IOException e) {
            e.printStackTrace();
        }
     return "null";
    }
       
      public Vehiculo getVehiculoLogged() {
        ArrayList<Vehiculo> vehiculos = new ArrayList<>(Vehiculo.class);
        vehiculos.addAll(carros);
        vehiculos.addAll(motos);
        vehiculos.addAll(pesados);
        vehiculos.addAll(acuaticos);
        vehiculos.addAll(aereos);

        for (Vehiculo vehiculo : vehiculos) {
            if (String.valueOf(vehiculo.getId()).equals(getIdVehiculoLogged())) {
                return vehiculo;
            }
        }
        return null;
      }

    
        @Override
        public void initialize(URL url, ResourceBundle rb) {
        VehiculoManager vehiculoManager = new VehiculoManager();
        
        try {
            carros = vehiculoManager.cargarCarros();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            motos = vehiculoManager.cargarMotos();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            acuaticos = vehiculoManager.cargarAcuaticos();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            aereos = vehiculoManager.cargarAereos();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            pesados = vehiculoManager.cargarPesados();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Vehiculo vehiculo = getVehiculoLogged();
        // Cargar las imágenes desde una carpeta
        imagenes = ImageLoader.loadImagesFromFolder("src/main/resources/imagenes/" + String.valueOf(vehiculo.getId()));

        if (!imagenes.isEmpty()) {
            currentIndex = 0; // Empezamos desde la primera imagen
            mostrarImagenes();
        }
    }  
    
     @FXML
    private void volver() throws IOException {
        App.setRoot("inicio");
    }   
}
