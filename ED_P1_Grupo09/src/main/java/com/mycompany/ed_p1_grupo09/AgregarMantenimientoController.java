/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ed_p1_grupo09;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Mantenimiento;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AgregarMantenimientoController implements Initializable {

    @FXML
    private TextField descripcionTF;
    @FXML
    private TextField tipoMantenimientoTF;
    @FXML
    private Button guardarB;
    @FXML
    private Button volverB;

    private AñadirVehiculoController mainController;
    private AgregarAccidenteController mainController1;

    public void setController(AñadirVehiculoController controller) {
        this.mainController = controller;
    }
    
    public void setController1(AgregarAccidenteController agregarAccidenteController) {
        this.mainController1 = agregarAccidenteController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void guardarMantenimiento() {
        String descripcion = descripcionTF.getText();
        String tipo = tipoMantenimientoTF.getText();

        Mantenimiento mantenimiento = new Mantenimiento(descripcion, tipo);
        mainController.agregarMantenimientoIndependiente(mantenimiento);

        Stage stage = (Stage) descripcionTF.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void volver() {
        Stage stage = (Stage) descripcionTF.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private String getTipoMantenimiento(){
        String tipoMantenimiento = tipoMantenimientoTF.getText();
        return tipoMantenimiento;
    }
        
    @FXML
    private String getDescripcion(){
        String descripcion = descripcionTF.getText();
        return descripcion;
    }
}
