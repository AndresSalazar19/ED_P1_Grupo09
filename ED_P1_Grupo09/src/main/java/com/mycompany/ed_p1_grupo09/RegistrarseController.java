/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ed_p1_grupo09;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import modelo.*;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.geometry.Insets;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;


/**
 * FXML Controller class
 *
 * @author asala
 */
public class RegistrarseController implements Initializable {

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField telefonoTextField;

    @FXML
    private TextField correoTextField;

    @FXML
    private PasswordField contrasenaTextField;

    private SistemaLogin sistemaLogin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sistemaLogin = new SistemaLogin(); // Inicializa el sistema de login
    }

    @FXML
    private void volver() throws IOException {
        App.setRoot("inicio"); // Navega de vuelta a la pantalla inicial
    }

    @FXML
    private void crearCuenta() throws IOException {
        String nombre = nombreTextField.getText();
        String telefono = telefonoTextField.getText();
        String correo = correoTextField.getText();
        String contrasena = contrasenaTextField.getText();
        
        if (nombre.isEmpty() || telefono.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return;
        }

        sistemaLogin.registrarUsuario(nombre, correo, telefono, contrasena);
        mostrarAlerta("Éxito", "Cuenta creada exitosamente.");
        limpiarCampos();
        
        App.setRoot("inicio"); // Navega de vuelta a la pantalla inicial
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiarCampos() {
        nombreTextField.clear();
        telefonoTextField.clear();
        correoTextField.clear();
        contrasenaTextField.clear();
    }
}
