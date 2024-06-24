/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ed_p1_grupo09;


/**
 * FXML Controller class
 *
 * @author andres b
 */


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modelo.*;
import tda.CircularDoublyLinkedList;
import tda.LinkedList;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AñadirVehiculoController implements Initializable {

    @FXML
    private TextField kilometrajeLabel;
    @FXML
    private TextField modeloLabel;
    @FXML
    private TextField ciudadLabel;
    @FXML
    private TextField precioLabel;
    @FXML
    private TextField añoLabel;
    @FXML
    private TextField capacidadLabel;
    @FXML
    private Button addImagenesButton;
    @FXML
    private Button addProcesosButton;
    @FXML
    private Button detInternosButton;
    @FXML
    private Button detExternosButton;
    @FXML
    private Button agregarAccidenteButton;
    @FXML
    private Button volverButton;
    @FXML
    private ComboBox<TipoVehiculo> tipoVehiCBox;

    private CircularDoublyLinkedList<Image> imagenes;
    private Vehiculo vehiculo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imagenes = new CircularDoublyLinkedList<>();
        ObservableList<TipoVehiculo> tipos = FXCollections.observableArrayList(TipoVehiculo.values());
        tipoVehiCBox.setItems(tipos);
    }

    @FXML
    private void getAddImagenes() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png", "*.gif"));
        Stage stage = (Stage) addImagenesButton.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            try {
                Image image = new Image(file.toURI().toString());
                imagenes.addLast(image);
                System.out.println("Imagen añadida: " + file.getName());
            } catch (Exception e) {
            }
        }
    }

    @FXML
    private void getProcesos() throws IOException {
        storeVehicleDetails();
        App.setRoot("procesos"); // Navega a la página de procesos
    }

    @FXML
    private void getDetInternos() throws IOException {
        storeVehicleDetails();
        App.setRoot("detallesInternos"); // Navega a la página de detalles internos
    }

    @FXML
    private void getDetExternos() throws IOException {
        storeVehicleDetails();
        App.setRoot("detallesExternos"); // Navega a la página de detalles externos
    }

    @FXML
    private void volver() throws IOException {
        App.setRoot("inicio"); // Navega de vuelta a la página de inicio
    }

    @FXML
    private void storeVehicleDetails() {
        // Recolección de datos desde la UI
        int kilometraje = Integer.parseInt(kilometrajeLabel.getText());
        String modelo = modeloLabel.getText();
        String ciudad = ciudadLabel.getText();
        double precio = Double.parseDouble(precioLabel.getText());
        String year = añoLabel.getText();
        int capacidad = Integer.parseInt(capacidadLabel.getText());

        // Crear el tipo de vehículo basado en la selección del ComboBox
        TipoVehiculo tipoSeleccionado = tipoVehiCBox.getValue();

        // Crear detalles internos y externos con valores predeterminados
        DetallesVehiExt detallesExt = new DetallesVehiExt("Ejemplo de descripción", "Marca Ejemplo", false, true);
        DetallesVehiInt detallesInt = new DetallesVehiInt("Tracción Ejemplo", "Transmisión Ejemplo", TipoCombustible.GASOLINA, "ABC-123", true, "Motor Ejemplo");

        // Lista de procesos vacía por defecto
        LinkedList<Proceso> listaProcesos = new LinkedList<>();

        // Datos del vendedor (suponiendo que el vendedor ya está autenticado en tu sistema)
        Usuario vendedor = null;

        Vehiculo vehiculoNuevo = null;

        switch (tipoSeleccionado) {
            case PESADO:
                double pesoMax = 15000.0; // Peso máximo del vehículo pesado (ejemplo)
                double pesoMin = 5000.0; // Peso mínimo del vehículo pesado (ejemplo)
                vehiculoNuevo = new Pesado(kilometraje, modelo, ciudad, precio, year, imagenes, new LinkedList<>(), 0, capacidad, detallesExt, detallesInt, listaProcesos, vendedor, pesoMax, pesoMin);
                break;
            case CARRO:
                String tipoCarro = "Sedán"; // Ejemplo de tipo de carro
                vehiculoNuevo = new Carro(kilometraje, modelo, ciudad, precio, year, imagenes, new LinkedList<>(), 0, capacidad, detallesExt, detallesInt, listaProcesos, vendedor, tipoCarro);
                break;
            case ACUATICO:
                String tipoAcuatico = "Lancha"; // Ejemplo de tipo de acuático
                vehiculoNuevo = new Acuatico(kilometraje, modelo, ciudad, precio, year, imagenes, new LinkedList<>(), 0, capacidad, detallesExt, detallesInt, listaProcesos, vendedor, tipoAcuatico);
                break;
            case AEREO:
                String tipoAeronave = "Jet"; // Ejemplo de tipo de aeronave
                double pesoMaxDespegue = 5000.0; // Ejemplo de peso máximo de despegue
                int rangoVuelo = 10000; // Ejemplo de rango de vuelo en kilómetros
                vehiculoNuevo = new Aereo(kilometraje, modelo, ciudad, precio, year, imagenes, new LinkedList<>(), 0, capacidad, detallesExt, detallesInt, listaProcesos, vendedor, tipoAeronave, pesoMaxDespegue, rangoVuelo);
                break;
            case MOTO:
                int cilindraje = 600; // Ejemplo de cilindraje
                vehiculoNuevo = new Moto(kilometraje, modelo, ciudad, precio, year, imagenes, new LinkedList<>(), 0, capacidad, detallesExt, detallesInt, listaProcesos, vendedor, cilindraje);
                break;
            default:
                System.out.println("Tipo de vehículo no soportado.");
        }
    }

    @FXML
    private void getAgregarAccidente() throws IOException {
        storeVehicleDetails();
        App.setRoot("agregarAccidente"); // Navega a la página para agregar accidentes
    }
}