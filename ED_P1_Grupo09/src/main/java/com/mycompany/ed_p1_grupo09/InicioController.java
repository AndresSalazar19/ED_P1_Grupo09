/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ed_p1_grupo09;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import modelo.*;
import tda.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author asala
 */public class InicioController {

    private static final int VEHICULOS_POR_PAGINA = 8;
    private List<Vehiculo> vehiculos;
    private List<Vehiculo> vehiculosFiltrados;
    private static Usuario usuarioLogeado;

    @FXML private Pagination pagination;
    @FXML private ComboBox<String> tipoVehiculoComboBox;
    @FXML private ComboBox<String> marcaComboBox;
    @FXML private ComboBox<String> modeloComboBox;
    @FXML private ComboBox<String> precioDesdeComboBox;
    @FXML private ComboBox<String> precioHastaComboBox;
    @FXML private ComboBox<String> anoDesdeComboBox;
    @FXML private ComboBox<String> anoHastaComboBox;
    @FXML private ComboBox<String> ordenarPorComboBox;

    @FXML private void iniciarSesion() throws IOException {
        App.setRoot("iniciarSesion");
    }

    public static void setUsuario(Usuario usuario) {
        usuarioLogeado = usuario;
    }

    @FXML private void registrarse() throws IOException {
        App.setRoot("registrarse");
    }

    @FXML
    private void aplicarFiltro() {
        vehiculosFiltrados.clear();

        String tipo = tipoVehiculoComboBox.getValue();
        String marca = marcaComboBox.getValue();
        String modelo = modeloComboBox.getValue();
        String precioDesde = precioDesdeComboBox.getValue();
        String precioHasta = precioHastaComboBox.getValue();
        String anoDesde = anoDesdeComboBox.getValue();
        String anoHasta = anoHastaComboBox.getValue();
        String ordenarPor = ordenarPorComboBox.getValue();

        for (Vehiculo vehiculo : vehiculos) {
            boolean matches = true;

            if (tipo != null && !tipo.equals("Todos")) {
                matches &= vehiculo.getTipoVehiculo().toString().equalsIgnoreCase(tipo);
            }
            if (marca != null && !marca.isEmpty()) {
                matches &= vehiculo.getMarca().equalsIgnoreCase(marca);
            }
            if (modelo != null && !modelo.isEmpty()) {
                matches &= vehiculo.getModelo().equalsIgnoreCase(modelo);
            }
            if (precioDesde != null && !precioDesde.isEmpty()) {
                matches &= vehiculo.getPrecio() >= Double.parseDouble(precioDesde);
            }
            if (precioHasta != null && !precioHasta.isEmpty()) {
                matches &= vehiculo.getPrecio() <= Double.parseDouble(precioHasta);
            }
            if (anoDesde != null && !anoDesde.isEmpty()) {
                matches &= vehiculo.getYear() >= Integer.parseInt(anoDesde);
            }
            if (anoHasta != null && !anoHasta.isEmpty()) {
                matches &= vehiculo.getYear() <= Integer.parseInt(anoHasta);
            }

            if (matches) {
                vehiculosFiltrados.addFirst(vehiculo);
            }
        }

        // Usar PriorityQueue para ordenar los vehículos según el criterio seleccionado
        PriorityQueue<Vehiculo> priorityQueue = null;
        if (ordenarPor != null) {
            switch (ordenarPor) {
                case "Precio Ascendente":
                    priorityQueue = new PriorityQueue<>(VehiculoComparators.compararPorPrecioAscendente);
                    break;
                case "Precio Descendente":
                    priorityQueue = new PriorityQueue<>(VehiculoComparators.compararPorPrecioDescendente);
                    break;
                case "Año Ascendente":
                    priorityQueue = new PriorityQueue<>(VehiculoComparators.compararPorAnoAscendente);
                    break;
                case "Año Descendente":
                    priorityQueue = new PriorityQueue<>(VehiculoComparators.compararPorAnoDescendente);
                    break;
                default:
                    priorityQueue = new PriorityQueue<>(VehiculoComparators.compararPorPrecioAscendente);
                    break;
            }

            if (!vehiculosFiltrados.isEmpty()) {
                for(Vehiculo vehiculo : vehiculosFiltrados){
                    priorityQueue.offer(vehiculo);
                }
                vehiculosFiltrados.clear();
                while (!priorityQueue.isEmpty()) {
                    vehiculosFiltrados.addFirst(priorityQueue.poll());
                }
            }
        }

        pagination.setPageCount((int) Math.ceil(vehiculosFiltrados.size() / (double) VEHICULOS_POR_PAGINA));
        pagination.setPageFactory(this::crearPagina);
    }

    private GridPane crearPagina(int pageIndex) {
        GridPane grid = new GridPane();
        int start = pageIndex * VEHICULOS_POR_PAGINA;
        int end = Math.min(start + VEHICULOS_POR_PAGINA, vehiculosFiltrados.size());

        if (vehiculosFiltrados.isEmpty()) {
            Label noDataLabel = new Label("No hay vehículos disponibles.");
            grid.add(noDataLabel, 0, 0);
            return grid;
        }

        for (int i = start; i < end; i++) {
            Vehiculo vehiculo = vehiculosFiltrados.get(i);
            Image image = vehiculo.getImagenes().get(0);

            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);
            imageView.setPreserveRatio(true);
            imageView.setOnMouseClicked(event -> {
                try {
                    mostrarDetalles(vehiculo);
                } catch (IOException e) {
                    System.err.println("Error al mostrar detalles: " + e.getMessage());
                }
            });

            Label nombreLabel = new Label(vehiculo.getModelo());
            nombreLabel.getStyleClass().add("label-small");
            Label detallesLabel = new Label(String.valueOf(vehiculo.getYear()) + " " + String.valueOf(vehiculo.getKilometraje()) + " Kms * " + vehiculo.getCiudad());
            detallesLabel.getStyleClass().add("label-small");
            Label precioLabel = new Label("$ " + String.valueOf(vehiculo.getPrecio()));
            precioLabel.getStyleClass().add("label-small");

            VBox vbox = new VBox(10);
            vbox.setAlignment(Pos.CENTER);
            vbox.getChildren().addAll(nombreLabel, detallesLabel, precioLabel);

            VBox imageWithDetails = new VBox(10);
            imageWithDetails.setAlignment(Pos.CENTER);
            imageWithDetails.getChildren().addAll(imageView, vbox);

            grid.add(imageWithDetails, i % 4, i / 4);
            grid.setHgap(15.0);
            grid.setVgap(15.0);
        }

        return grid;
    }

    private void mostrarDetalles(Vehiculo vehiculo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("verDetallesVehiculo.fxml"));
        Parent root = loader.load();

        VerDetallesVehiculoController detallesController = loader.getController();
        detallesController.setVehiculo(vehiculo);
        App.setRoot("verDetallesVehiculo");
    }

    @FXML
    private void initialize() throws IOException {
        SistemaApp sis = SistemaApp.getInstance();
        vehiculos = sis.getVehiculos();
        vehiculosFiltrados = new ArrayList<>(Vehiculo.class);

        tipoVehiculoComboBox.getItems().addAll("Todos", "Carro", "Moto", "Acuatico", "Aereo", "Pesado");
        marcaComboBox.getItems().addAll("Todos", "Marca2", "Marca3"); // Añade marcas reales
        modeloComboBox.getItems().addAll("Todos", "Modelo2", "Modelo3"); // Añade modelos reales
        precioDesdeComboBox.getItems().addAll("1000", "5000", "10000"); // Añade rangos de precios reales
        precioHastaComboBox.getItems().addAll("20000", "30000", "40000"); // Añade rangos de precios reales
        anoDesdeComboBox.getItems().addAll("2000", "2010", "2015"); // Añade años reales
        anoHastaComboBox.getItems().addAll("2018", "2019", "2020"); // Añade años reales
        ordenarPorComboBox.getItems().addAll("Precio Ascendente", "Precio Descendente", "Año Ascendente", "Año Descendente");

        // Inicialmente, mostrar todos los vehículos
        aplicarFiltro();
        if (usuarioLogeado != null) {
            System.out.println("Usuario logueado: " + usuarioLogeado.getId());
        } else {
            System.out.println("No hay usuario logueado.");
        }
    }
}
