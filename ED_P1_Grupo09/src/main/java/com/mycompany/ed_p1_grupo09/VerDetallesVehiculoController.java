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
import javafx.scene.control.ScrollPane;
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
    private static DoublyLinkedList<Vehiculo> listaVehiculos;
    private static Vehiculo vehiculo;
    private static Usuario usuario;
    private DoublyNodeList<Vehiculo> currentNode;

    @FXML
    private Button verMantenimientosBtn; 
    @FXML
    private Label descripcionLabel;
    @FXML
    private Label tipoVehiculoLabel;
    @FXML
    private Label numeroMantenimientosLabel;
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
    private Label extraLabel1; // Etiqueta adicional para detalles específicos
    @FXML
    private Label extraLabel2; // Etiqueta adicional para detalles específicos
    @FXML
    private Button favoritoButton;
    @FXML
    private Button editarVehiculoButton;
    @FXML
    private Button flechaIzquierdaButton;
    @FXML
    private Button flechaDerechaButton;
    @FXML
    private VBox contenedorAccidentes;
    @FXML
    private HBox contenedorImagenes;
    @FXML
    private ScrollPane scrollPaneAccidentes;

    
    private CircularDoublyLinkedList<Image> imagenes = new CircularDoublyLinkedList<>();
    private int currentIndex;

    public static void setVehiculo(Vehiculo vehiculo) {
    VerDetallesVehiculoController.vehiculo = vehiculo;
    }

    public static void setUsuario(Usuario usuario) {
        VerDetallesVehiculoController.usuario = usuario;
    }

    public static void setListaVehiculos(DoublyLinkedList<Vehiculo> vehiculos) {
        VerDetallesVehiculoController.listaVehiculos = vehiculos;
    }


    @FXML
    private void irAlAnterior() {
        if (currentNode != null && currentNode.getPrevious() != null) {
            currentNode = currentNode.getPrevious();
            vehiculo = currentNode.getContent();
            mostrarDetalles(vehiculo);
            actualizarFlechas();
        }
    }

    @FXML
    private void irAlSiguiente() {
        if (currentNode != null && currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
            vehiculo = currentNode.getContent();
            mostrarDetalles(vehiculo);
            actualizarFlechas();
        }
    }

    private void actualizarFlechas() {
        flechaIzquierdaButton.setVisible(currentNode != null && currentNode.getPrevious() != null);
        flechaDerechaButton.setVisible(currentNode != null && currentNode.getNext() != null);
    }

    @FXML
    private void getPrevious() {
        if (imagenes != null && !imagenes.isEmpty()) {
            currentIndex--;
            if (currentIndex < 0) {
                currentIndex = imagenes.getSize() - 1;
            }
            mostrarImagenes();
        }
    }

    @FXML
    private void getNext() {
        if (imagenes != null && !imagenes.isEmpty()) {
            currentIndex++;
            if (currentIndex >= imagenes.getSize()) {
                currentIndex = 0;
            }
            mostrarImagenes();
        }
    }

    private void mostrarImagenes() {
        contenedorImagenes.getChildren().clear();

        int size = Math.min(imagenes.getSize(), 1);
        for (int i = 0; i < size; i++) {
            int index = (currentIndex + i) % imagenes.getSize();
            Image image = imagenes.get(index);

            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(300);
            imageView.setFitWidth(400);
            imageView.setPreserveRatio(true);

            contenedorImagenes.getChildren().add(imageView);
        }
    }

    @FXML
    private void seleccionarFavorito(){
        System.out.println("SUPERRRR");
    }

    @FXML
    private void editarVehiculo(){
        System.out.println("Raime Hakke");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (listaVehiculos != null) {
            currentNode = listaVehiculos.getNode(vehiculo);
            System.out.println(currentNode);
            actualizarFlechas();
            mostrarDetalles(vehiculo);
            if (usuario != null) {
                if (usuario.getId() == vehiculo.getVendedor().getId()) {
                    editarVehiculoButton.setVisible(true);
                    favoritoButton.setVisible(false);
                } else {
                    favoritoButton.setVisible(true);
                    editarVehiculoButton.setVisible(false);
                }
            } else {
                favoritoButton.setVisible(false);
                editarVehiculoButton.setVisible(false);
            }

        }
    }


    private void mostrarDetalles(Vehiculo vehiculo) {
        if (vehiculo != null) {
            tipoVehiculoLabel.setText(String.valueOf(vehiculo.getTipoVehiculo()));
            numeroMantenimientosLabel.setText(String.valueOf(vehiculo.getMantenimientos().size()));
            kilometrajeLabel.setText(String.valueOf(vehiculo.getKilometraje()));
            modeloLabel.setText(vehiculo.getModelo());
            ciudadLabel.setText(vehiculo.getCiudad());
            precioLabel.setText(String.valueOf(vehiculo.getPrecio()));
            yearLabel.setText(String.valueOf(vehiculo.getYear()));
            descripcionLabel.setText(vehiculo.getDescripcion());

            negociableLabel.setText(vehiculo.isNegociable() ? "Negociable" : "Fijo");
            estadoLabel.setText(String.valueOf(vehiculo.getEstado()));
            traccionLabel.setText(vehiculo.getDetallesInt().getTraccion());
            transmisionLabel.setText(vehiculo.getDetallesInt().getTransmision());
            tipoCombustibleLabel.setText(String.valueOf(vehiculo.getDetallesInt().getCombustible()));
            placaLabel.setText(vehiculo.getDetallesInt().getPlaca());
            climatizadoLabel.setText(vehiculo.getDetallesInt().isClimatizado() ? "Climatizado" : "No climatizado");
            tipoMotorLabel.setText(vehiculo.getDetallesInt().getTipoMotor());

            // Mostrar detalles específicos
            switch (vehiculo.getTipoVehiculo()) {
                case CARRO:
                    extraLabel1.setText("Tipo de Carro: " + ((Carro) vehiculo).getTipoCarro());
                    extraLabel2.setText("");
                    break;
                case MOTO:
                    extraLabel1.setText("Cilindraje: " + ((Moto) vehiculo).getCilindraje());
                    extraLabel2.setText("");
                    break;
                case PESADO:
                    extraLabel1.setText("Peso Máximo: " + ((Pesado) vehiculo).getPesoMax());
                    extraLabel2.setText("Peso Mínimo: " + ((Pesado) vehiculo).getPesoMin());
                    break;
                case ACUATICO:
                    extraLabel1.setText("Tipo de Acuático: " + ((Acuatico) vehiculo).getTipoacua());
                    extraLabel2.setText("");
                    break;
                case AEREO:
                    extraLabel1.setText("Tipo de Aeronave: " + ((Aereo) vehiculo).getTipoAeronave());
                    extraLabel2.setText("Peso Máximo de Despegue: " + ((Aereo) vehiculo).getPesoMaximoDespegue() + "\n Rango de Vuelo: " + ((Aereo) vehiculo).getRangoVuelo());
                    break;
                default:
                    extraLabel1.setText("");
                    extraLabel2.setText("");
                    break;
            }

            imagenes = ImageLoader.loadImagesFromFolder("src/main/resources/imagenes/vehiculos/" + vehiculo.getId());
            if (!imagenes.isEmpty()) {
                currentIndex = 0;
                mostrarImagenes();
            }

            mostrarAccidentes();

            if (!vehiculo.getMantenimientos().isEmpty()) {
                verMantenimientosBtn.setVisible(true);
            } else {
                verMantenimientosBtn.setVisible(false);
            }
        }
    }

      @FXML
    private void verMantenimientos() throws IOException {
        VerMantenimientosController.setVehiculo(vehiculo);
        App.setRoot("verMantenimientos");
    }

    private void mostrarAccidentes() {
        contenedorAccidentes.getChildren().clear();

        if (vehiculo.getAccidentes().isEmpty()) {
            Label noAccidentesLabel = new Label("No hay accidentes.");
            contenedorAccidentes.getChildren().add(noAccidentesLabel);
            return;
        }

        for (Accidente accidente : vehiculo.getAccidentes()) {
            HBox accidenteHBox = new HBox();
            accidenteHBox.setSpacing(10);
            accidenteHBox.setStyle("-fx-padding: 10; -fx-border-style: solid inside; -fx-border-width: 2; -fx-border-insets: 5; -fx-border-radius: 5; -fx-border-color: gray;");
            accidenteHBox.setAlignment(Pos.CENTER_LEFT);

            VBox accidenteBox = new VBox();
            accidenteBox.setSpacing(5);

            Label accidenteDescripcion = new Label("Descripción: " + accidente.getDescripcion());
            Label accidenteParteAfectada = new Label("Parte Afectada: " + accidente.getParteAfectada());
            Label accidenteFecha = new Label("Fecha: " + accidente.getFechaAccidente());

            accidenteBox.getChildren().addAll(accidenteDescripcion, accidenteParteAfectada, accidenteFecha);

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
            btnContainer.setAlignment(Pos.CENTER);
            btnContainer.setPrefWidth(150);
            btnContainer.setPrefHeight(80);

            accidenteHBox.getChildren().addAll(accidenteBox, btnContainer);
            accidenteHBox.setAlignment(Pos.CENTER);
            contenedorAccidentes.getChildren().add(accidenteHBox);
        }

        scrollPaneAccidentes.setContent(contenedorAccidentes);
        scrollPaneAccidentes.setFitToWidth(true);
        scrollPaneAccidentes.setFitToHeight(true);
    }

    @FXML
    private void volver() throws IOException {
        App.setRoot("inicio");
    }
}