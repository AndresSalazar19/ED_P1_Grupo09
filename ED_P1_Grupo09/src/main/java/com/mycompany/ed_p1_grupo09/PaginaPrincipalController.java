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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import modelo.*;
import tda.*;


/**
 * FXML Controller class
 *
 * @author asala
 */

public class PaginaPrincipalController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    /*
    private List<Usuario> usuarios;

        
    @FXML
    private Label correoLabel;
    @FXML
    private Label nombreLabel;
    
    
    public String getCorreo(){
     try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/archivos/loggedArchivos.csv"))) {
            String linea;
            linea = br.readLine();
            return linea;
        } catch (IOException e) {
            e.printStackTrace();
        }
     return "null";
    }
    
     private void cargarUsuarios() {
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
    }
     
    public Usuario cargarUsuarioLogged(){
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/archivos/loggedArchivos.csv"))) {
            String correo;
            correo = br.readLine();
            
            for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(correo)){
                return usuario;
            }
        }
        return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
        
 
    }
    
    
    @FXML
    private void misVehiculos() throws IOException {
        App.setRoot("misVehiculos");
    }
    
    @FXML
    private void añadirVehiculo() throws IOException {
        App.setRoot("añadirVehiculo");
    }
    
    @FXML
    private void cerrarSesion() throws IOException {
        App.setRoot("Inicio");
    }
    

    
        @Override
    public void initialize(URL url, ResourceBundle rb) {
         // Cargar las imágenes desde una carpeta
       
     
        
        usuarios = new ArrayList<>(Usuario.class);
        cargarUsuarios();
        
        Usuario usuariocargado = cargarUsuarioLogged();
        nombreLabel.setText(usuariocargado.getNombre());
        nombreLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 12");
        correoLabel.setText(usuariocargado.getCorreo());
        correoLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 12");

    }
    */
}
