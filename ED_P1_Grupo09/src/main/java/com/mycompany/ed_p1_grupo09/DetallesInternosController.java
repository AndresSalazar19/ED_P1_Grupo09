/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ed_p1_grupo09;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.TipoCombustible;

import java.net.URL;
import java.util.ResourceBundle;import modelo.DetallesVehiInt;
;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class DetallesInternosController implements Initializable {

    @FXML
    private TextField TraccionTF;
    @FXML
    private TextField TransmisionTF;
    @FXML
    private TextField PlacaTF;
    @FXML
    private ComboBox<TipoCombustible> TipoCombustibleCOMB;
    @FXML
    private CheckBox ClimatizadoCB;
    @FXML
    private Button guardarB;
    @FXML
    private Button volverB;
    @FXML
    private TextField TipoMotorTF;

    private AñadirVehiculoController mainController;

    public void setMainController(AñadirVehiculoController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializar el ComboBox con los valores del enum TipoCombustible
        TipoCombustibleCOMB.getItems().addAll(TipoCombustible.values());
    }

   @FXML
    private void guardarDetallesInt() {
        String traccion = TraccionTF.getText();
        String transmision = TransmisionTF.getText();
        String placa = PlacaTF.getText();
        TipoCombustible tipoCombustible = TipoCombustibleCOMB.getValue();
        boolean climatizado = ClimatizadoCB.isSelected();
        String tipoMotor = TipoMotorTF.getText();

        DetallesVehiInt detalles = new DetallesVehiInt(traccion, transmision, tipoCombustible, placa, climatizado, tipoMotor);

        if (mainController != null) {
            mainController.setDetallesInternos(detalles);
        }

        Stage stage = (Stage) guardarB.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void volver() {
        Stage stage = (Stage) volverB.getScene().getWindow();
        stage.close();
    }

    @FXML
    private String getTraccion() {
        return TraccionTF.getText();
    }

    @FXML
    private String getTransmision() {
        return TransmisionTF.getText();
    }

    @FXML
    private String getPlaca() {
        return PlacaTF.getText();
    }
    
    @FXML
    private String getTipoMotor() {
        return TipoMotorTF.getText();
    }

    @FXML
    private TipoCombustible selectTipoCombustible() {
        return TipoCombustibleCOMB.getValue();
    }

    @FXML
    private boolean isClimatizado() {
        return ClimatizadoCB.isSelected();
    }
}