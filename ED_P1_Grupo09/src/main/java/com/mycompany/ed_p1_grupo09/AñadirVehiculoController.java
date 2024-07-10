package com.mycompany.ed_p1_grupo09;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import tda.*;
import modelo.*;

public class AñadirVehiculoController implements Initializable {

    @FXML
    private TextField kmTF;
    @FXML
    private TextField modeloTF;
    @FXML
    private TextField ciudadTF;
    @FXML
    private TextField precioTF;
    @FXML
    private TextField añoTF;
    @FXML
    private TextField capacidadTF;
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
    @FXML
    private ComboBox<Estado> EstadoCBox;
    @FXML
    private Button detIntBT;
    @FXML
    private Button añadirMantenimientoButton;
    @FXML
    private Button añadirAccidenteButton;
    @FXML
    private Button guardarButton;
    @FXML
    private Button volverButton;
    @FXML
    private TextField DescripcionTF;
    @FXML
    private TextField marcaTF;
    @FXML
    private VBox contenedorAccidentes;
    @FXML
    private ScrollPane scrollPaneAccidentes;
    @FXML
    private VBox contenedorMantenimientos;
    @FXML
    private ScrollPane scrollPaneMantenimientos;
    
    private Usuario usuario;
    
    private int kilometraje;
    private String modelo;
    private String descripcion;
    private String marca;
    private Estado estado;
    private String ciudad;
    private double precio;
    private int year;
    private CircularDoublyLinkedList<Image> imagenes;
    private LinkedList<Accidente> accidentes;
    private int capacidad;
    private DetallesVehiInt detallesInt;
    private Usuario vendedor;
    private boolean negociable;
    private TipoVehiculo tipoVehiculo;

    LinkedList<Mantenimiento> mantenimientos = new LinkedList<>();


    // Método para establecer el usuario autenticado
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @FXML
    private void addImagenes() {
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
    private void getDetInternos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("detallesInternos.fxml"));
            Parent root = loader.load();

            DetallesInternosController controller = loader.getController();
            controller.setMainController(this);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Detalles Internos");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private boolean getNegociable() throws IOException {
        return negociableCB.isSelected();
    }


    @FXML
    private void volver() throws IOException {
        App.setRoot("inicio");
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
        kilometraje = Integer.parseInt(kmTF.getText());
        modelo = modeloTF.getText();
        ciudad = ciudadTF.getText();
        precio = Double.parseDouble(precioTF.getText());
        year = Integer.parseInt(añoTF.getText());
        capacidad = Integer.parseInt(capacidadTF.getText());
        negociable = negociableCB.isSelected();
        descripcion = DescripcionTF.getText();
        marca = marcaTF.getText();
        estado = EstadoCBox.getValue();

        // Inicializar el vehículo nuevo
        Vehiculo vehiculoNuevo = null;
        TipoVehiculo tipoSeleccionado = tipoVehiCBox.getValue();

        // Crear el vehículo basado en el tipo seleccionado
         switch (tipoSeleccionado) {
            case PESADO:
                double pesoMax = Double.parseDouble(pesoMaxTF.getText());
                double pesoMin = Double.parseDouble(pesoMinTF.getText());
                vehiculoNuevo = new Pesado(kilometraje, modelo, descripcion, marca, estado, ciudad, precio, year, imagenes, accidentes, 1, capacidad, vendedor, detallesInt, negociable, mantenimientos, tipoVehiculo, pesoMax, pesoMin);
                break;
            case CARRO:
                TipoCarro tipoCarro = TipoCarro.valueOf(tipoCarroTF.getText());
                vehiculoNuevo = new Carro(kilometraje, modelo, descripcion, marca, estado, ciudad, precio, year, imagenes, accidentes, 1, capacidad, vendedor, detallesInt, negociable, mantenimientos, tipoVehiculo, tipoCarro);
                 break;
            case ACUATICO:
                String tipoAcuatico = tipoAcuaticoTF.getText();
                vehiculoNuevo = new Acuatico(kilometraje, modelo, descripcion, marca, estado, ciudad, precio, year, imagenes, accidentes, 1, capacidad, vendedor, detallesInt, negociable, mantenimientos, tipoVehiculo, tipoAcuatico);
                break;
            case AEREO:
                String tipoAeronave = tipoAeronaveTF.getText();
                double pesoMaximoDespegue = Double.parseDouble(pesoMaxDespegueTF.getText());
                int rangoVuelo = Integer.parseInt(rangoVueloTF.getText());
                vehiculoNuevo = new Aereo(kilometraje, modelo, descripcion, marca, estado, ciudad, precio, year, imagenes, accidentes, 1, capacidad, vendedor, detallesInt, negociable, mantenimientos, tipoVehiculo, tipoAeronave, pesoMaximoDespegue,rangoVuelo);
                break;
            case MOTO:
                int cilindraje = Integer.parseInt(cilindrajeTF.getText());
                vehiculoNuevo = new Moto(kilometraje, modelo, descripcion, marca, estado, ciudad, precio, year, imagenes, accidentes, 1, capacidad, vendedor, detallesInt, negociable, mantenimientos, tipoVehiculo, cilindraje);
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
    private void añadirAccidente() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("agregarAccidente.fxml"));
            Parent root = loader.load();

            AgregarAccidenteController controller = loader.getController();
            // No necesitas pasar un vehículo aquí si solo quieres abrir el popup

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Agregar Accidente");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    @FXML
    public void agregarMantenimientoIndependiente(Mantenimiento mantenimiento) {
        mantenimientos.addFirst(mantenimiento);
        System.out.println("Mantenimiento añadido: " + mantenimiento.getDescripcion());
    }

   @FXML
    private void añadirMantenimiento() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("agregarMantenimiento.fxml"));
            Parent root = loader.load();

            AgregarMantenimientoController controller = loader.getController();
            controller.setController(this);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Agregar Mantenimiento");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mostrarMantenimientos();
    }

    public void agregarMantenimiento(Mantenimiento mantenimiento) {
            mantenimientos.addFirst(mantenimiento);
    }

    @FXML
    private void selectEstado() {
        Estado estadoSeleccionado = EstadoCBox.getValue();
        System.out.println("Estado seleccionado: " + estadoSeleccionado);
    }
    
    @FXML
    public void setDetallesInternos(DetallesVehiInt detalles) {
        this.detallesInt = detalles;
    }

    
    
    // Métodos para obtener los valores de los campos de texto

    @FXML
    private String getKm() {
        return kmTF.getText();
    }

    @FXML
    private String getModelo() {
        return modeloTF.getText();
    }

    @FXML
    private String getCiudad() {
        return ciudadTF.getText();
    }

    @FXML
    private String getPrecio() {
        return precioTF.getText();
    }

    @FXML
    private String getYear() {
        return añoTF.getText();
    }

    @FXML
    private String getCapacidad() {
        return capacidadTF.getText();
    }

    @FXML
    private String getDescripcion() {
        return DescripcionTF.getText();
    }

    @FXML
    private String getMarca() {
        return marcaTF.getText();
    }

    @FXML
    private String getPesoMax() {
        return pesoMaxTF.getText();
    }

    @FXML
    private String getPesoMin() {
        return pesoMinTF.getText();
    }

    @FXML
    private String getTipoAcua() {
        return tipoAcuaticoTF.getText();
    }

    @FXML
    private String getTipoAeronave() {
        return tipoAeronaveTF.getText();
    }

    @FXML
    private String getTipoCarro() {
        return tipoCarroTF.getText();
    }

    @FXML
    private String getCilindraje() {
        return cilindrajeTF.getText();
    }

    @FXML
    private String getPesoMaxDespegue() {
        return pesoMaxDespegueTF.getText();
    }

    @FXML
    private String getRangoVuelo() {
        return rangoVueloTF.getText();
    }

    @FXML
    private TipoVehiculo selectTipoVehi() {
        return tipoVehiCBox.getValue();
    }
    
    private void mostrarAccidentes() {
        contenedorAccidentes.getChildren().clear();

        if (accidentes.isEmpty()) {
            Label noAccidentesLabel = new Label("No hay accidentes.");
            contenedorAccidentes.getChildren().add(noAccidentesLabel);
            return;
        }

        for (Accidente accidente : accidentes) {
            HBox accidenteHBox = new HBox();
            accidenteHBox.setSpacing(10);
            accidenteHBox.setStyle("-fx-padding: 10; -fx-border-style: solid inside; -fx-border-width: 2; -fx-border-insets: 5; -fx-border-radius: 5; -fx-border-color: gray;");
            accidenteHBox.setAlignment(Pos.CENTER_LEFT);

            VBox accidenteBox = new VBox();
            accidenteBox.setSpacing(5);

            Label accidenteDescripcion = new Label("Descripción: " + accidente.getDescripcion());
            Label accidenteParteAfectada = new Label("Parte Afectada: " + accidente.getParteAfectada());
            Label accidenteFecha = new Label("Fecha: " + accidente.getFechaAccidente());

            accidenteBox.getChildren().addAll(accidenteDescripcion, accidenteParteAfectada, accidenteFecha);

            Button editarAccidenteBtn = new Button("Editar Accidente");
            editarAccidenteBtn.setOnAction(event -> {
                abrirEditarAccidentePopup(accidente);
            });

            Button eliminarAccidenteBtn = new Button("X");
            eliminarAccidenteBtn.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            eliminarAccidenteBtn.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmar Eliminación");
                alert.setHeaderText(null);
                alert.setContentText("¿Está seguro que desea eliminar este accidente?");

                ButtonType buttonTypeYes = new ButtonType("Sí");
                ButtonType buttonTypeNo = new ButtonType("No");
                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == buttonTypeYes) {
                    accidentes.remove(accidente);
                    mostrarAccidentes();
                }
            });

            HBox btnContainer = new HBox(editarAccidenteBtn, eliminarAccidenteBtn);
            btnContainer.setAlignment(Pos.CENTER_RIGHT);
            btnContainer.setSpacing(10);
            btnContainer.setPrefWidth(200);
            btnContainer.setPrefHeight(80);

            HBox.setHgrow(btnContainer, Priority.ALWAYS);

            accidenteHBox.getChildren().addAll(accidenteBox, btnContainer);
            accidenteHBox.setAlignment(Pos.CENTER);
            contenedorAccidentes.getChildren().add(accidenteHBox);
        }

        scrollPaneAccidentes.setContent(contenedorAccidentes);
        scrollPaneAccidentes.setFitToWidth(true);
        scrollPaneAccidentes.setFitToHeight(true);
    }

    private void abrirEditarAccidentePopup(Accidente accidente) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("agregarAccidente.fxml"));
            Parent root = loader.load();

            AgregarAccidenteController controller = loader.getController();
            controller.setController(this);
            controller.setAccidente(accidente);
            controller.mostrarProcesos();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Editar Accidente");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actualizarAccidente() {
        mostrarAccidentes();
    }

    public void agregarAccidente(Accidente accidente) {
        accidentes.addFirst(accidente);
        mostrarAccidentes();
    }
    
    private void mostrarMantenimientos() {
        contenedorMantenimientos.getChildren().clear();

        if (mantenimientos.isEmpty()) {
            Label noMantenimientosLabel = new Label("No hay mantenimientos.");
            contenedorMantenimientos.getChildren().add(noMantenimientosLabel);
            return;
        }

        for (Mantenimiento mantenimiento : mantenimientos) {
            HBox mantenimientoHbox = new HBox();
            mantenimientoHbox.setSpacing(10);
            mantenimientoHbox.setStyle("-fx-padding: 10; -fx-border-style: solid inside; -fx-border-width: 2; -fx-border-insets: 5; -fx-border-radius: 5; -fx-border-color: gray;");
            mantenimientoHbox.setAlignment(Pos.CENTER_LEFT);

            VBox mantenimientoBox = new VBox();
            mantenimientoBox.setSpacing(5);

            Label mantenimientoDescripcion = new Label("Descripción: " + mantenimiento.getDescripcion());
            Label mantenimientoTipoMantenimiento = new Label("Tipo de Mantenimiento: " + mantenimiento.getTipoMantenimiento());

            mantenimientoBox.getChildren().addAll(mantenimientoDescripcion, mantenimientoTipoMantenimiento);

            Button editarMantenimientoBtn = new Button("Editar Mantenimiento");
            editarMantenimientoBtn.setOnAction(event -> {
                abrirEditarMantenimientoPopup(mantenimiento);
            });

            Button eliminarMantenimientoBtn = new Button("X");
            eliminarMantenimientoBtn.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            eliminarMantenimientoBtn.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmar Eliminación");
                alert.setHeaderText(null);
                alert.setContentText("¿Está seguro que desea eliminar este mantenimiento?");

                ButtonType buttonTypeYes = new ButtonType("Sí");
                ButtonType buttonTypeNo = new ButtonType("No");
                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == buttonTypeYes) {
                    mantenimientos.remove(mantenimiento);
                    mostrarMantenimientos();
                }
            });

            HBox btnContainer = new HBox(editarMantenimientoBtn, eliminarMantenimientoBtn);
            btnContainer.setAlignment(Pos.CENTER_RIGHT);
            btnContainer.setSpacing(10);
            btnContainer.setPrefWidth(200);
            btnContainer.setPrefHeight(80);

            HBox.setHgrow(btnContainer, Priority.ALWAYS);

            mantenimientoHbox.getChildren().addAll(mantenimientoBox, btnContainer);
            mantenimientoHbox.setAlignment(Pos.CENTER);
            contenedorMantenimientos.getChildren().add(mantenimientoHbox);
        }

        scrollPaneMantenimientos.setContent(contenedorMantenimientos);
        scrollPaneMantenimientos.setFitToWidth(true);
        scrollPaneMantenimientos.setFitToHeight(true);
    }

    private void abrirEditarMantenimientoPopup(Mantenimiento mantenimiento) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("agregarMantenimiento.fxml"));
            Parent root = loader.load();

            AgregarMantenimientoController controller = loader.getController();
            controller.setController(this);
            controller.setMantenimiento(mantenimiento);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Editar Mantenimiento");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actualizarMantenimiento() {
        mostrarMantenimientos();
    }



    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accidentes = new LinkedList<>();
        
        Accidente acci = new Accidente(1,"des","parteafec",LocalDate.of(2024, 7, 9), mantenimientos);
        accidentes.addFirst(acci);
                
        mostrarAccidentes();
        mostrarMantenimientos();

        
        tipoVehiCBox.getItems().addAll(TipoVehiculo.values());
        tipoVehiCBox.setOnAction(e -> updateFieldsVisibility());

        // Inicializar el ComboBox con los valores del enum Estado
        EstadoCBox.setItems(FXCollections.observableArrayList(Estado.values()));

    }
    
}
