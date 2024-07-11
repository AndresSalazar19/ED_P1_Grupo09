/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ed_p1_grupo09;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import modelo.*;
import tda.*;

/**
 * FXML Controller class
 *
 * @author asala
 */
public class MisVehiculosController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    /*
    private static final int VEHICULOS_POR_PAGINA = 8;

    private List<Carro> carros = new ArrayList<>(Carro.class);
    private List<Moto> motos = new ArrayList<>(Moto.class);
    private List<Acuatico> acuaticos = new ArrayList<>(Acuatico.class);
    private List<Aereo> aereos = new ArrayList<>(Aereo.class);
    private List<Pesado> pesados = new ArrayList<>(Pesado.class);
    private List<Usuario> usuarios = new ArrayList<>(Usuario.class);
    
    private ArrayList<Vehiculo> vehiculosFiltrados = new ArrayList<>(Vehiculo.class);

    @FXML
    private Pagination pagination;
    
     @FXML
    private void volver() throws IOException {
        App.setRoot("paginaPrincipal");
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

        pagination.setPageCount((int) Math.ceil(vehiculosFiltrados.size() / (double) VEHICULOS_POR_PAGINA));
        pagination.setPageFactory(this::crearPagina);
    }

    private GridPane crearPagina(int pageIndex) {
        GridPane grid = new GridPane();
        int start = pageIndex * VEHICULOS_POR_PAGINA;
        int end = Math.min(start + VEHICULOS_POR_PAGINA, vehiculosFiltrados.size());

        for (int i = start; i < end; i++) {
            Vehiculo vehiculo = vehiculosFiltrados.get(i);

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

            Label nombreLabel = new Label("Nombre: " + vehiculo.getModelo());
            Label kilometrajeLabel = new Label("Kilometraje: " + vehiculo.getKilometraje());
            Label precioLabel = new Label("Precio: " + vehiculo.getPrecio());
            Label negociableLabel = new Label("Es negociable: " + true);
            Label ciudadLabel = new Label("Ciudad: " + vehiculo.getCiudad());

            VBox vbox = new VBox(10);
            vbox.getChildren().addAll(nombreLabel, kilometrajeLabel, precioLabel, negociableLabel, ciudadLabel);

            VBox imageWithDetails = new VBox(10);
            imageWithDetails.getChildren().addAll(imageView, vbox);

            grid.add(imageWithDetails, i % 4, i / 4);
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

    public Usuario getUsuarioLogueado() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/archivos/usuarioArchivos.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 4) {
                    String nombre = datos[0];
                    String correo = datos[1];
                    String telefono = datos[2];
                    String contrasena = datos[3];
                    Usuario usuario = new Usuario(nombre, correo, telefono, contrasena);
                    usuarios.addFirst(usuario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/archivos/loggedArchivos.csv"))) {
            String correo = br.readLine();
            for (Usuario usuario : usuarios) {
                if (usuario.getCorreo().equals(correo)) {
                    return usuario;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VehiculoManager vehiculoManager = new VehiculoManager();
        Usuario usuarioLogged = getUsuarioLogueado();
        try {
            carros = vehiculoManager.cargarMisCarros(usuarioLogged);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            motos = vehiculoManager.cargarMisMotos(usuarioLogged);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            acuaticos = vehiculoManager.cargarMisAcuaticos(usuarioLogged);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            aereos = vehiculoManager.cargarMisAereos(usuarioLogged);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            pesados = vehiculoManager.cargarMisPesados(usuarioLogged);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        filtrarVehiculos(null);
    }
*/
    
}