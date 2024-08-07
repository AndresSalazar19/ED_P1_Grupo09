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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.geometry.Insets;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import modelo.*;
import tda.*;

/**
 * FXML Controller class
 *
 * @author asala
 */
public class IniciarSesionController implements Initializable {

    @FXML
    private TextField correoTextField;
    
    @FXML
    private PasswordField  contrasenaTextField;
     
    private SistemaLogin sistemaLogin;
    private Usuario usuarioLogueado;


    @FXML
    private void registrarse() throws IOException {
        App.setRoot("registrarse");
    }
    
 @FXML
    private void iniciarSesion() throws IOException {
        String correo = correoTextField.getText();
        String contrasena = contrasenaTextField.getText();

        if (correo.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return;
        }
        
        // Lógica para validar el inicio de sesión
        Usuario usuarioLogeado = sistemaLogin.iniciarSesion(correo, contrasena);
        if (usuarioLogeado != null) {
            mostrarAlerta("Éxito", "Inicio de sesión exitoso.");
            
            InicioController.setUsuario(usuarioLogeado);
            App.setRoot("inicio");
            
        } else {
            mostrarAlerta("Error", "Correo o contraseña incorrectos.");
        }
    }
    
    
    @FXML
    private void volver() throws IOException {
        App.setRoot("inicio");
    }
    
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);


        alert.showAndWait();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sistemaLogin = new SistemaLogin(); // Inicializa el sistema de login
    }    
    
 
}
