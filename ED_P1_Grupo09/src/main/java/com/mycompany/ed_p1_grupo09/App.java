package com.mycompany.ed_p1_grupo09;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;



import modelo.*;
import tda.*;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Scene sceneAñadirVehiculo;
    private static Scene sceneAgregarMantenimiento;
    private static Scene sceneAgregarAccidente;
    private static Scene sceneDetallesInternos;
    private static Scene sceneInicioSesion;
    private static Scene sceneRegistrarse;
    private static Scene sceneVerDetalles;
    private static Scene sceneInicio;
        
    @Override
    public void start(Stage stage) throws IOException {
           scene = new Scene(loadFXML("inicio"), 1000, 800);

           String css = getClass().getResource("/styles/styles.css").toExternalForm();
           scene.getStylesheets().add(css);
           
           sceneAñadirVehiculo = new Scene(loadFXML("añadirVehiculo"),1000,800);
           String css1 = getClass().getResource("/styles/añadirvehiculo.css").toExternalForm();
           sceneAñadirVehiculo.getStylesheets().add(css1);
           
           sceneAgregarMantenimiento = new Scene(loadFXML("agregarMantenimiento"),448,305);
           String css2 = getClass().getResource("/styles/agregarmantenimiento.css").toExternalForm();
           sceneAgregarMantenimiento.getStylesheets().add(css2);
           
           sceneAgregarAccidente = new Scene(loadFXML("agregarAccidente"),448,305);
           String css3 = getClass().getResource("/styles/agregaraccidente.css").toExternalForm();
           sceneAgregarAccidente.getStylesheets().add(css3);
           
           sceneDetallesInternos = new Scene(loadFXML("detallesInternos"),448,305);
           String css4 = getClass().getResource("/styles/detallesinternos.css").toExternalForm();
           sceneDetallesInternos.getStylesheets().add(css4);
           
           sceneInicioSesion = new Scene(loadFXML("iniciarSesion"),1000,800);
           String css5 = getClass().getResource("/styles/inicioSesion.css").toExternalForm();
           sceneInicioSesion.getStylesheets().add(css5);
           
           sceneRegistrarse = new Scene(loadFXML("registrarse"),1000,800);
           String css6 = getClass().getResource("/styles/registro.css").toExternalForm();
           sceneRegistrarse.getStylesheets().add(css6);
           
           sceneVerDetalles = new Scene(loadFXML("verDetallesVehiculo"),1000,800);
           String css7 = getClass().getResource("/styles/verDetallesVehi.css").toExternalForm();
           sceneVerDetalles.getStylesheets().add(css7);
           
           sceneInicio = new Scene(loadFXML("Inicio"),1000,800);
           String css8 = getClass().getResource("/styles/Inicio.css").toExternalForm();
           sceneInicio.getStylesheets().add(css8);

           stage.setScene(scene);
           stage.setTitle("Gear Max");
           stage.show();
       }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
 
    public static void main(String[] args) {
        String tilin = "ESOTILIN";
        System.out.println("Hello Wolrd");
        launch();
    }

}
