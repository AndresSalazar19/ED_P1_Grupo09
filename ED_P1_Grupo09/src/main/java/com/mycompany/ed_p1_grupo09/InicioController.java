/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ed_p1_grupo09;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import modelo.*;
import tda.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author asala
 */
public class InicioController  {
    private static final int VEHICULOS_POR_PAGINA = 8;
    
    private List<Carro> carros;
    private List<Moto> motos;
    private List<Acuatico> acuaticos;
    private List<Aereo> aereos;
    private List<Pesado> pesados;
    
    @FXML
    private Pagination pagination;
       
     @FXML
    private void iniciarSesion() throws IOException {
        App.setRoot("iniciarSesion");
    }
    
     @FXML
    private void registrarse() throws IOException {
        App.setRoot("registrarse");
    }
    
    private void mostrarDetalles(Vehiculo vehiculo){
        System.out.println(vehiculo.toString());
    }
    
  private GridPane crearPagina(int pageIndex) {
    GridPane grid = new GridPane();
    int start = pageIndex * VEHICULOS_POR_PAGINA;
    int end = Math.min(start + VEHICULOS_POR_PAGINA, carros.size() + motos.size() + acuaticos.size() + aereos.size() + pesados.size());

    ArrayList<Vehiculo> vehiculos = new ArrayList<>(Vehiculo.class);
    vehiculos.addAll(carros);
    vehiculos.addAll(motos);
    vehiculos.addAll(acuaticos);
    vehiculos.addAll(aereos);
    vehiculos.addAll(pesados);

    for (int i = start; i < end; i++) {
        Vehiculo vehiculo = vehiculos.get(i);

        // Lógica para cargar la imagen según el tipo de vehículo
        Image image;
        if (vehiculo instanceof Carro) {
            image = new Image("/imagenes/im1.jpeg"); // Ejemplo de carga de imagen para carro
        } else if (vehiculo instanceof Moto) {
            image = new Image("/imagenes/im2.jpeg"); // Ejemplo de carga de imagen para moto
        } else if (vehiculo instanceof Acuatico) {
            image = vehiculo.getImage(); // Ejemplo de carga de imagen para acuático
        } else if (vehiculo instanceof Aereo) {
            image = new Image("/imagenes/im1.jpeg"); // Ejemplo de carga de imagen para aéreo
        } else if (vehiculo instanceof Pesado) {
            image = new Image("/imagenes/im1.jpeg"); // Ejemplo de carga de imagen para pesado
        } else {
            // Manejar otros tipos de vehículos si es necesario
            image = new Image("/imagenes/im1.jpeg"); // Imagen por defecto o manejo alternativo
        }

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setOnMouseClicked(event -> mostrarDetalles(vehiculo));

        // Crear etiquetas con los detalles del vehículo
        Label nombreLabel = new Label("Nombre: " + vehiculo.getModelo());
        Label kilometrajeLabel = new Label("Kilometraje: " + vehiculo.getKilometraje());
        Label precioLabel = new Label("Precio: " + vehiculo.getPrecio());
        Label negociableLabel = new Label("Es negociable: " + true);
        Label ciudadLabel = new Label("Ciudad: " + vehiculo.getCiudadv());

        VBox vbox = new VBox(10); // Espacio de 10 pixels entre las etiquetas
        vbox.getChildren().addAll(nombreLabel, kilometrajeLabel, precioLabel, negociableLabel, ciudadLabel);

        VBox imageWithDetails = new VBox(10); // Espacio de 10 pixels entre la imagen y los detalles
        imageWithDetails.getChildren().addAll(imageView, vbox);

        grid.add(imageWithDetails, i % 4, i / 4); // 4 columnas por fila
    }

    return grid;
}

     
   public void initialize() throws IOException {
       
        VehiculoManager vehiculoManager = new VehiculoManager();
        
        carros = vehiculoManager.cargarCarros();
        motos = vehiculoManager.cargarMotos();
        acuaticos = vehiculoManager.cargarAcuaticos();
        aereos = vehiculoManager.cargarAereos();
        pesados = vehiculoManager.cargarPesados();

            
        // Cargar las imágenes desde una carpeta
        pagination.setPageCount((int) Math.ceil(10 / (double) VEHICULOS_POR_PAGINA));
        pagination.setPageFactory(this::crearPagina);
        
    }
       
}
