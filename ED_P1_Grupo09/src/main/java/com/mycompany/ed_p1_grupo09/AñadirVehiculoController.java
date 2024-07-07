/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ed_p1_grupo09;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tda.*;
import modelo.*;

/**
 * FXML Controller class
 *
 * @author asala
 */
public class AñadirVehiculoController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
/*
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
    private ComboBox<TipoVehiculo> tipoVehiCBox;

    @FXML
    private VBox motoFields;
    @FXML
    private TextField cilindrajeTF;

    @FXML
    private VBox pesadoFields;
    @FXML
    private TextField pesoMaxTF;
    @FXML
    private TextField pesoMinTF;

    @FXML
    private VBox carroFields;
    @FXML
    private TextField tipoCarroTF;

    @FXML
    private VBox acuaticoFields;
    @FXML
    private TextField tipoAcuaticoTF;

    @FXML
    private VBox aereoFields;
    @FXML
    private TextField tipoAeronaveTF;
    @FXML
    private TextField pesoMaxDespegueTF;
    @FXML
    private TextField rangoVueloTF;

    @FXML
    private Button addImagenesButton;
    
    @FXML
    private CheckBox negociableCB;

    private Usuario usuario;

    private CircularDoublyLinkedList<Image> imagenes = new CircularDoublyLinkedList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tipoVehiCBox.getItems().addAll(TipoVehiculo.values());
        tipoVehiCBox.setOnAction(e -> updateFieldsVisibility());
    }

    // Método para establecer el usuario autenticado
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
    private void getDetInternos() throws IOException {
        guardarDetallesVehi();
        App.setRoot("detallesInternos");
    }
    
    @FXML
    private boolean getNegociable() throws IOException {
        return negociableCB.isSelected();
    }

    @FXML
    private void getDetExternos() throws IOException {
        guardarDetallesVehi();
        App.setRoot("detallesExternos");
    }

    @FXML
    private void volver() throws IOException {
        App.setRoot("inicio");
    }
    
    @FXML
    private void getPesoMax() {
        // Implementar lógica para peso máximo si es necesario
    }

    @FXML
    private void getPesoMin() {
        // Implementar lógica para peso mínimo si es necesario
    }

    @FXML
    private void getTipoAcua() {
        // Implementar lógica para tipo acuático si es necesario
    }

    @FXML
    private void getTipoAeronave() {
        // Implementar lógica para tipo aeronave si es necesario
    }

    @FXML
    private void getTipoCarro() {
        // Implementar lógica para tipo de carro si es necesario
    }

    @FXML
    private void getCilindraje() {
        // Implementar lógica para cilindraje si es necesario
    }

    @FXML
    private void getPesoMaxDespegue() {
        // Implementar lógica para peso máximo despegue si es necesario
    }

    @FXML
    private void getRangoVuelo() {
        // Implementar lógica para rango de vuelo si es necesario
    }  
    
    @FXML
    private void selectTipoVehi() {
        // Implementar lógica para rango de vuelo si es necesario
    } 
    
    

    @FXML
    private void updateFieldsVisibility() {
        TipoVehiculo tipoSeleccionado = tipoVehiCBox.getValue();

        motoFields.setVisible(false);
        pesadoFields.setVisible(false);
        carroFields.setVisible(false);
        acuaticoFields.setVisible(false);
        aereoFields.setVisible(false);

        if (tipoSeleccionado != null) {
            switch (tipoSeleccionado) {
                case MOTO:
                    motoFields.setVisible(true);
                    break;
                case PESADO:
                    pesadoFields.setVisible(true);
                    break;
                case CARRO:
                    carroFields.setVisible(true);
                    break;
                case ACUATICO:
                    acuaticoFields.setVisible(true);
                    break;
                case AEREO:
                    aereoFields.setVisible(true);
                    break;
            }
        }
    }

@FXML
private void guardarDetallesVehi() {
    try {
        // Obtener valores de los campos FXML
        int kilometraje = Integer.parseInt(kilometrajeLabel.getText());
        String modelo = modeloLabel.getText();
        String ciudadVehi = ciudadLabel.getText();
        double precio = Double.parseDouble(precioLabel.getText());
        String year = añoLabel.getText();
        int capacidad = Integer.parseInt(capacidadLabel.getText());
        boolean negociable = negociableCB.isSelected();

        // Crear listas y detalles
        LinkedList<Accidente> listaAccidentes = new LinkedList<>();
        LinkedList<Mantenimiento> listaProcesos = new LinkedList<>();
        CircularDoublyLinkedList<Image> imagenes = new CircularDoublyLinkedList<>();
        Usuario usuario = null; // 
        DetallesVehiInt detallesInt = new DetallesVehiInt("", "", null, "", true, ""); // Asegúrate de pasar los valores correctos

        // Inicializar el vehículo nuevo
        Vehiculo vehiculoNuevo = null;
        TipoVehiculo tipoSeleccionado = tipoVehiCBox.getValue();
        
        
        // Crear el vehículo basado en el tipo seleccionado
        switch (tipoSeleccionado) {
            case PESADO:
                double pesoMax = Double.parseDouble(pesoMaxTF.getText());
                double pesoMin = Double.parseDouble(pesoMinTF.getText());
                vehiculoNuevo = new Pesado(kilometraje, modelo, "", "", null, ciudadVehi, precio, year, imagenes, listaAccidentes, 0, capacidad, usuario, detallesInt, pesoMax, pesoMin, negociable);
                break;
            case CARRO:
                String tipoCarro = tipoCarroTF.getText();
                vehiculoNuevo = new Carro(kilometraje, modelo, "", "", null, ciudadVehi, precio, year, imagenes, listaAccidentes, 0, capacidad, usuario, detallesInt, tipoCarro, negociable);
                break;
            case ACUATICO:
                String tipoAcuatico = tipoAcuaticoTF.getText();
                vehiculoNuevo = new Acuatico(kilometraje, modelo, "", "", null, ciudadVehi, precio, year, imagenes, listaAccidentes, 0, capacidad, usuario, detallesInt, tipoAcuatico, negociable);
                break;
            case AEREO:
                String tipoAeronave = tipoAeronaveTF.getText();
                double pesoMaxDespegue = Double.parseDouble(pesoMaxDespegueTF.getText());
                int rangoVuelo = Integer.parseInt(rangoVueloTF.getText());
                vehiculoNuevo = new Aereo(kilometraje, modelo, "", "", null, ciudadVehi, precio, year, imagenes, listaAccidentes, 0, capacidad, usuario, detallesInt, tipoAeronave, pesoMaxDespegue, rangoVuelo, negociable);
                break;
            case MOTO:
                int cilindraje = Integer.parseInt(cilindrajeTF.getText());
                vehiculoNuevo = new Moto(kilometraje, modelo, "", "", null, ciudadVehi, precio, year, imagenes, listaAccidentes, 0, capacidad, usuario, detallesInt, cilindraje, negociable);
                break;
        }

        // Aquí puedes agregar la lógica para guardar `vehiculoNuevo` en donde necesites, por ejemplo en una base de datos o una lista.
    } catch (NumberFormatException e) {
        // Manejar errores de formato de número
        e.printStackTrace();
    } catch (Exception e) {
        // Manejar otros errores
        e.printStackTrace();
    }
  }


    @FXML
    private void abrirAgregarAccidentePopup() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("agregarAccidente.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Agregar Accidente");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
        }
    }

    
    @FXML
    private void abrirProcesosPopup() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("procesos.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Procesos");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
        }
    }
    
    
    @FXML
    private void getAgregarAccidente() throws IOException {
        guardarDetallesVehi();
        App.setRoot("agregarAccidente");
    }
*/
}
