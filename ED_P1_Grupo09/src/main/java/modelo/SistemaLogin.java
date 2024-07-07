/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import tda.*;

/**
 *
 * @author asala
 */

public class SistemaLogin {
    private static final String ARCHIVO_USUARIOS = "src/main/java/archivos/usuarioArchivos.csv";
    private List<Usuario> usuarios;
    int countId;
    
    public SistemaLogin() {
        usuarios = new ArrayList<>(Usuario.class);
        cargarUsuarios();
    }

    public void registrarUsuario(String nombre, String correo, String telefono, String contrasena) {
        Usuario usuario = new Usuario(countId++, nombre, correo, telefono, contrasena);
        usuarios.addFirst(usuario);
        guardarUsuarios();
    }

    public boolean iniciarSesion(String correo, String contrasena) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(correo) && usuario.getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }
    

     private void cargarUsuarios() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_USUARIOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                    int id = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    String correo = datos[2];
                    String telefono = datos[3];
                    String contrasena = datos[4];
                    Usuario usuario = new Usuario(id, nombre, correo, telefono, contrasena);
                    usuarios.addFirst(usuario);
                    // Actualizar countId para asegurarnos de que sea único
                    if (id >= countId) {
                        countId = id + 1;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarUsuarios() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_USUARIOS))) {
            for (Usuario usuario : usuarios) {
                bw.write(usuario.getNombre() + "," + usuario.getCorreo() + "," + usuario.getTelefono() + "," + usuario.getContrasena());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
