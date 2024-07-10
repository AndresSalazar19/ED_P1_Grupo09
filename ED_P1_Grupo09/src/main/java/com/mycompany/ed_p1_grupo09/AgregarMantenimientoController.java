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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AgregarMantenimientoController {

    @FXML
    private Label tituloLabel;
    @FXML
    private TextField descripcionTextField;
    @FXML
    private TextField tipoMantenimientoTextField;

    private AñadirVehiculoController mainController;
    private Mantenimiento mantenimientoActual;

    @FXML
    private void guardar() {
        if (descripcionTextField.getText().isEmpty() || tipoMantenimientoTextField.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Todos los campos deben estar llenos.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Guardado");
        alert.setHeaderText(null);
        alert.setContentText("¿Está seguro que desea guardar este mantenimiento?");

        ButtonType buttonTypeYes = new ButtonType("Sí");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeYes) {
            String descripcion = descripcionTextField.getText();
            String tipoMantenimiento = tipoMantenimientoTextField.getText();

            if (mantenimientoActual == null) {
                Mantenimiento nuevoMantenimiento = new Mantenimiento(descripcion, tipoMantenimiento);
                mainController.agregarMantenimiento(nuevoMantenimiento);
            } else {
                mantenimientoActual.setDescripcion(descripcion);
                mantenimientoActual.setTipoMantenimiento(tipoMantenimiento);
                mainController.actualizarMantenimiento();
            }

            Stage stage = (Stage) descripcionTextField.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void volver() {
        Stage stage = (Stage) descripcionTextField.getScene().getWindow();
        stage.close();
    }

    public void setController(AñadirVehiculoController controller) {
        this.mainController = controller;
    }
    
    public void setMantenimiento(Mantenimiento mantenimiento) {
        this.mantenimientoActual = mantenimiento;
        tituloLabel.setText("Editar Mantenimiento");
        descripcionTextField.setText(mantenimiento.getDescripcion());
        tipoMantenimientoTextField.setText(mantenimiento.getTipoMantenimiento());
    }
    
    
}