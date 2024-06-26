/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ed_p1_grupo09;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
public class InicioController {
    private static final int VEHICULOS_POR_PAGINA = 8;

    private List<Carro> carros;
    private List<Moto> motos;
    private List<Acuatico> acuaticos;
    private List<Aereo> aereos;
    private List<Pesado> pesados;

    private ArrayList<Vehiculo> vehiculosFiltrados = new ArrayList<>(Vehiculo.class);

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

    @FXML
    private void mostrarTodos() {
        filtrarVehiculos(null);
    }

    @FXML
    private void mostrarCarros() {
        filtrarVehiculos(Carro.class);
    }

    @FXML
    private void mostrarMotos() {
        filtrarVehiculos(Moto.class);
    }

    @FXML
    private void mostrarPesados() {
        filtrarVehiculos(Pesado.class);
    }

    @FXML
    private void mostrarAcuaticos() {
        filtrarVehiculos(Acuatico.class);
    }

    @FXML
    private void mostrarAereos() {
        filtrarVehiculos(Aereo.class);
    }

    private void filtrarVehiculos(Class<? extends Vehiculo> tipo) {
        vehiculosFiltrados.clear();

        if (tipo == null) {
            vehiculosFiltrados.addAll(carros);
            vehiculosFiltrados.addAll(motos);
            vehiculosFiltrados.addAll(acuaticos);
            vehiculosFiltrados.addAll(aereos);
            vehiculosFiltrados.addAll(pesados);
        } else if (tipo == Carro.class) {
            vehiculosFiltrados.addAll(carros);
        } else if (tipo == Moto.class) {
            vehiculosFiltrados.addAll(motos);
        } else if (tipo == Pesado.class) {
            vehiculosFiltrados.addAll(pesados);
        } else if (tipo == Acuatico.class) {
            vehiculosFiltrados.addAll(acuaticos);
        } else if (tipo == Aereo.class) {
            vehiculosFiltrados.addAll(aereos);
        }

        // Actualizar la paginación
        pagination.setPageCount((int) Math.ceil(vehiculosFiltrados.size() / (double) VEHICULOS_POR_PAGINA));
        pagination.setPageFactory(this::crearPagina);
    }

    private GridPane crearPagina(int pageIndex) {
        GridPane grid = new GridPane();
        int start = pageIndex * VEHICULOS_POR_PAGINA;
        int end = Math.min(start + VEHICULOS_POR_PAGINA, vehiculosFiltrados.size());

        for (int i = start; i < end; i++) {
            Vehiculo vehiculo = vehiculosFiltrados.get(i);

            // Lógica para cargar la imagen según el tipo de vehículo
            Image image = vehiculo.getImage();

            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            imageView.setOnMouseClicked(event -> {
                try {
                    mostrarDetalles(vehiculo);
                } catch (IOException e) {
                    System.err.println("Error al mostrar detalles: " + e.getMessage());
                }
            });

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

    private void mostrarDetalles(Vehiculo vehiculo) throws IOException {
        String filePath = "src/main/java/archivos/vehiculoLoggedArchivos.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(String.valueOf(vehiculo.getId()));
        } catch (IOException e) {
            System.err.println("Error al escribir en archivo: " + e.getMessage());
            throw e;
        } finally {
            App.setRoot("verDetallesVehiculo");
        }
    }

    @FXML
    private void initialize() throws IOException {
        VehiculoManager vehiculoManager = new VehiculoManager();

        carros = vehiculoManager.cargarCarros();
        motos = vehiculoManager.cargarMotos();
        acuaticos = vehiculoManager.cargarAcuaticos();
        aereos = vehiculoManager.cargarAereos();
        pesados = vehiculoManager.cargarPesados();

        // Inicialmente, mostrar todos los vehículos
        filtrarVehiculos(null);
    }
}