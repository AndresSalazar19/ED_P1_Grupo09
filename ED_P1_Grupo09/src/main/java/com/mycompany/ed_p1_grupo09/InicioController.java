/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ed_p1_grupo09;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author asala
 */

public class InicioController {

    private static final int VEHICULOS_POR_PAGINA = 8;
    private List<Vehiculo> vehiculos;
    private DoublyLinkedList<Vehiculo> vehiculosFiltrados;
    private static Usuario usuarioLogeado;
    private boolean mostrar;
    
    @FXML private Pagination pagination;
    @FXML private ComboBox<String> tipoVehiculoComboBox;
    @FXML private ComboBox<String> marcaComboBox;
    @FXML private ComboBox<String> modeloComboBox;
    @FXML private ComboBox<String> precioDesdeComboBox;
    @FXML private ComboBox<String> precioHastaComboBox;
    @FXML private ComboBox<String> anoDesdeComboBox;
    @FXML private ComboBox<String> anoHastaComboBox;
    @FXML private ComboBox<String> ordenarPorComboBox;
    @FXML private Button iniciarSesionButton;
    @FXML private Button registrarseButton;
    @FXML private Button cerrarSesionButton;
    @FXML private Button misVehiculosButton;
    @FXML private Button favoritoButton;
    @FXML private Button añadirVehiculoButton;
    @FXML private Label bienvenidaLabel;

    @FXML private void iniciarSesion() throws IOException {
        App.setRoot("iniciarSesion");
    }
    
    private void actualizarEstadoUsuario() {
        if (usuarioLogeado != null) {
            iniciarSesionButton.setVisible(false);
            registrarseButton.setVisible(false);
            cerrarSesionButton.setVisible(true);
            misVehiculosButton.setVisible(true);
            favoritoButton.setVisible(true);
            añadirVehiculoButton.setVisible(true);
            bienvenidaLabel.setText("Bienvenido, " + usuarioLogeado.getNombre());
            bienvenidaLabel.setVisible(true);
        } else {
            iniciarSesionButton.setVisible(true);
            registrarseButton.setVisible(true);
            cerrarSesionButton.setVisible(false);
            misVehiculosButton.setVisible(false);
            favoritoButton.setVisible(false);
            añadirVehiculoButton.setVisible(false);
            bienvenidaLabel.setVisible(false);
        }
    }
    
    public static void setUsuario(Usuario usuario) {
        usuarioLogeado = usuario;
    }
    
    @FXML
    private void cerrarSesion() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar cierre de sesión");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres cerrar sesión?");

        ButtonType buttonTypeYes = new ButtonType("Sí");
        ButtonType buttonTypeNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeYes) {
            usuarioLogeado = null;
            actualizarEstadoUsuario();
            try {
                App.setRoot("inicio");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    @FXML private void mostrarMisVehiculos() throws IOException {
        vehiculos = usuarioLogeado.getMisVehiculos();
        mostrar = true;
        aplicarFiltro();
        mostrar = false;
    }
    
    @FXML private void filtrarFavoritos() throws IOException {
        System.out.println("VEHICULOSSS FAV FILTRADOS");
        vehiculos = usuarioLogeado.getVehiculosFavoritos();
        mostrar = true;
        aplicarFiltro();
        mostrar = false;
    }
    
    @FXML private void añadirVehiculos() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("añadirVehiculo.fxml"));
        Parent root = loader.load();

        AñadirVehiculoController añadirController = loader.getController();
        añadirController.setUsuario(usuarioLogeado);
        App.setRoot("añadirVehiculo");
    }
    
    @FXML private void registrarse() throws IOException {
        App.setRoot("registrarse");
    }

    @FXML
    private void aplicarFiltro() {
        vehiculosFiltrados.clear();

        for (Vehiculo vehiculo : vehiculos) {
            if (filtrarVehiculo(vehiculo)) {
                if (mostrar || (usuarioLogeado == null) || (vehiculo.getVendedor().getId() != usuarioLogeado.getId())) {
                    vehiculosFiltrados.addFirst(vehiculo);
                }
            }
        }

        ordenarVehiculos();
        actualizarPaginacion();
    }

    private boolean filtrarVehiculo(Vehiculo vehiculo) {
        String tipo = tipoVehiculoComboBox.getValue();
        String marca = marcaComboBox.getValue();
        String modelo = modeloComboBox.getValue();
        String precioDesde = precioDesdeComboBox.getValue();
        String precioHasta = precioHastaComboBox.getValue();
        String anoDesde = anoDesdeComboBox.getValue();
        String anoHasta = anoHastaComboBox.getValue();

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
        return matches;
    }

    private void ordenarVehiculos() {
        String ordenarPor = ordenarPorComboBox.getValue();
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
                for (Vehiculo vehiculo : vehiculosFiltrados) {
                    priorityQueue.offer(vehiculo);
                }
                vehiculosFiltrados.clear();
                while (!priorityQueue.isEmpty()) {
                    vehiculosFiltrados.addFirst(priorityQueue.poll());
                }
            }
        }
    }

    private void actualizarPaginacion() {
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
        detallesController.setUsuario(usuarioLogeado);
        detallesController.setListaVehiculos(vehiculosFiltrados);

        App.setRoot("verDetallesVehiculo");
    }

    private <T> void agregarValorPersonalizado(ComboBox<T> comboBox) {
        T valorActual = comboBox.getValue();
        if (valorActual != null && !comboBox.getItems().contains(valorActual)) {
            comboBox.getItems().add(valorActual);
        }
    }
    
    @FXML
    public void actualizarVehiculos() {
        System.out.println("ACTUALIZANDOO");
        try {
            SistemaApp sis = SistemaApp.getInstance();
            sis.setVehiculos(null);
            
            vehiculos = sis.getVehiculos();
            System.out.println("tamaño" + vehiculos.size());
            vehiculosFiltrados.clear();
            for(Vehiculo vehiculo: vehiculos){
                vehiculosFiltrados.addFirst(vehiculo);
            }
            aplicarFiltro();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() throws IOException {
        System.out.println("Iniciando");
        SistemaApp sis = SistemaApp.getInstance();
        vehiculos = sis.getVehiculos();
        vehiculosFiltrados = new DoublyLinkedList<>();
                
        tipoVehiculoComboBox.getItems().addAll("Todos", "Carro", "Moto", "Acuatico", "Aereo", "Pesado");
        ordenarPorComboBox.getItems().addAll("Precio Ascendente", "Precio Descendente", "Año Ascendente", "Año Descendente");

        marcaComboBox.setEditable(true);
        modeloComboBox.setEditable(true);
        precioDesdeComboBox.setEditable(true);
        precioHastaComboBox.setEditable(true);
        anoDesdeComboBox.setEditable(true);
        anoHastaComboBox.setEditable(true);

        // Añadir listeners para agregar valores personalizados
        marcaComboBox.setOnAction(event -> agregarValorPersonalizado(marcaComboBox));
        modeloComboBox.setOnAction(event -> agregarValorPersonalizado(modeloComboBox));
        precioDesdeComboBox.setOnAction(event -> agregarValorPersonalizado(precioDesdeComboBox));
        precioHastaComboBox.setOnAction(event -> agregarValorPersonalizado(precioHastaComboBox));
        anoDesdeComboBox.setOnAction(event -> agregarValorPersonalizado(anoDesdeComboBox));
        anoHastaComboBox.setOnAction(event -> agregarValorPersonalizado(anoHastaComboBox));

        // Inicialmente, mostrar todos los vehículos
        aplicarFiltro();
        actualizarEstadoUsuario();
        if (usuarioLogeado != null) {
            System.out.println("Usuario logueado: " + usuarioLogeado.getId());
            sis.cargarVehiculosAUsuario(usuarioLogeado);
        } else {
            System.out.println("No hay usuario logueado.");
        }
    }
}
