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
import javafx.scene.image.ImageView;
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
    private ComboBox<TipoCarro> tipoCarroCBox;
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
    @FXML
    private Label traccionLabel;
    @FXML
    private Label transmisionLabel;
    @FXML
    private Label combustibleLabel;
    @FXML
    private Label placaLabel;
    @FXML
    private Label climatizadoLabel;
    @FXML
    private Label tipoMotorLabel;
    @FXML
    private VBox imagenesVBox;
    @FXML
    private ScrollPane imagenesScrollPane;     
    
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
    private int capacidad;
    private DetallesVehiInt detallesInt;
    private static Usuario vendedor;
    private boolean negociable;
    private TipoVehiculo tipoVehiculo;

    private LinkedList<Mantenimiento> mantenimientos = new LinkedList<>();
    private LinkedList<Accidente> accidentes = new LinkedList<>();

    // Método para establecer el usuario autenticado
    public static void setUsuario(Usuario vendedor) {
       AñadirVehiculoController.vendedor = vendedor;
    }
    
    @FXML
    private void selectTipoVehi() {
        TipoVehiculo tipoSeleccionado = tipoVehiCBox.getValue();
        updateFieldsVisibility();
        System.out.println("Tipo de Vehículo seleccionado: " + tipoSeleccionado);
    }
    
    @FXML
    private void selectTipoCarro() {
    // Implementa la lógica que desees para cuando se seleccione el tipo de carro
    System.out.println("Tipo de carro seleccionado: " + tipoCarroCBox.getValue());
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
                mostrarImagenes(); // Llamar a mostrarImagenes después de agregar una imagen
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void mostrarImagenes() {
        imagenesVBox.getChildren().clear(); // Limpiar el VBox antes de agregar las imágenes

        for (int i = 0; i < imagenes.size(); i++) {
            Image image = imagenes.get(i);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100); // Ajusta el tamaño según sea necesario
            imageView.setFitHeight(100);

            Button eliminarButton = new Button("Eliminar");
            int index = i;
            eliminarButton.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmar Eliminación");
                alert.setHeaderText(null);
                alert.setContentText("¿Está seguro que desea eliminar esta imagen?");

                ButtonType buttonTypeYes = new ButtonType("Sí");
                ButtonType buttonTypeNo = new ButtonType("No");
                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == buttonTypeYes) {
                    imagenes.remove(index);
                    mostrarImagenes();
                }
            });

            HBox imageContainer = new HBox(10, imageView, eliminarButton); // Agrega un espacio entre la imagen y el botón
            imageContainer.setAlignment(Pos.CENTER_LEFT);
            imagenesVBox.getChildren().add(imageContainer);
        }

        imagenesScrollPane.setContent(imagenesVBox); // Establece el contenido del ScrollPane
        imagenesScrollPane.setFitToWidth(true); // Ajusta el ScrollPane para que se ajuste al ancho del VBox
    }



    @FXML
    private void getDetInternos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("detallesInternos.fxml"));
            Parent root = loader.load();

            DetallesInternosController controller = loader.getController();
            controller.setMainController(this);

            if (detallesInt != null) {
                controller.setDetalles(detallesInt);
            }

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
            // Validar que todos los campos estén llenos
            if (kmTF.getText().isEmpty() || modeloTF.getText().isEmpty() || ciudadTF.getText().isEmpty() || 
                precioTF.getText().isEmpty() || añoTF.getText().isEmpty() || capacidadTF.getText().isEmpty() || 
                tipoVehiCBox.getValue() == null || EstadoCBox.getValue() == null || imagenes.isEmpty() ||
                DescripcionTF.getText().isEmpty() || marcaTF.getText().isEmpty()) {
                mostrarAlerta("Error", "Todos los campos deben estar llenos y debe haber al menos una imagen.");
                return;
            }

            // Validar los campos numéricos
            int kilometraje = Integer.parseInt(kmTF.getText());
            double precio = Double.parseDouble(precioTF.getText());
            int year = Integer.parseInt(añoTF.getText());
            int capacidad = Integer.parseInt(capacidadTF.getText());

            // Cargar la lista de vehículos y obtener el siguiente ID disponible
            List<Vehiculo> vehiculos = VehiculoManager.cargarVehiculos();
            int nextId = 1;
            // Validar tipo de vehículo y sus campos específicos
            TipoVehiculo tipoSeleccionado = tipoVehiCBox.getValue();
            Vehiculo vehiculoNuevo = null;

            switch (tipoSeleccionado) {
                case PESADO:
                    if (pesoMaxTF.getText().isEmpty() || pesoMinTF.getText().isEmpty()) {
                        mostrarAlerta("Error", "Debe llenar todos los campos específicos para vehículos pesados.");
                        return;
                    }
                    double pesoMax = Double.parseDouble(pesoMaxTF.getText());
                    double pesoMin = Double.parseDouble(pesoMinTF.getText());
                    vehiculoNuevo = new Pesado(kilometraje, modeloTF.getText(), DescripcionTF.getText(), marcaTF.getText(), 
                                                EstadoCBox.getValue(), ciudadTF.getText(), precio, year, imagenes, 
                                                accidentes, nextId, capacidad, usuario, detallesInt, negociableCB.isSelected(), 
                                                mantenimientos, tipoSeleccionado, pesoMax, pesoMin);
                    break;
                case CARRO:
                    if (tipoCarroCBox.getValue() == null) {
                        mostrarAlerta("Error", "Debe seleccionar el tipo de carro.");
                        return;
                    }
                    vehiculoNuevo = new Carro(kilometraje, modeloTF.getText(), DescripcionTF.getText(), marcaTF.getText(), 
                                                EstadoCBox.getValue(), ciudadTF.getText(), precio, year, imagenes, 
                                                accidentes, nextId, capacidad, usuario, detallesInt, negociableCB.isSelected(), 
                                                mantenimientos, tipoSeleccionado, tipoCarroCBox.getValue());
                    break;
                case ACUATICO:
                    if (tipoAcuaticoTF.getText().isEmpty()) {
                        mostrarAlerta("Error", "Debe llenar el campo específico para vehículos acuáticos.");
                        return;
                    }
                    vehiculoNuevo = new Acuatico(kilometraje, modeloTF.getText(), DescripcionTF.getText(), marcaTF.getText(), 
                                                EstadoCBox.getValue(), ciudadTF.getText(), precio, year, imagenes, 
                                                accidentes, nextId, capacidad, usuario, detallesInt, negociableCB.isSelected(), 
                                                mantenimientos, tipoSeleccionado, tipoAcuaticoTF.getText());
                    break;
                case AEREO:
                    if (tipoAeronaveTF.getText().isEmpty() || pesoMaxDespegueTF.getText().isEmpty() || rangoVueloTF.getText().isEmpty()) {
                        mostrarAlerta("Error", "Debe llenar todos los campos específicos para vehículos aéreos.");
                        return;
                    }
                    double pesoMaximoDespegue = Double.parseDouble(pesoMaxDespegueTF.getText());
                    int rangoVuelo = Integer.parseInt(rangoVueloTF.getText());
                    vehiculoNuevo = new Aereo(kilometraje, modeloTF.getText(), DescripcionTF.getText(), marcaTF.getText(), 
                                                EstadoCBox.getValue(), ciudadTF.getText(), precio, year, imagenes, 
                                                accidentes, nextId, capacidad, usuario, detallesInt, negociableCB.isSelected(), 
                                                mantenimientos, tipoSeleccionado, tipoAeronaveTF.getText(), pesoMaximoDespegue, rangoVuelo);
                    break;
                case MOTO:
                    if (cilindrajeTF.getText().isEmpty()) {
                        mostrarAlerta("Error", "Debe llenar el campo específico para motocicletas.");
                        return;
                    }
                    int cilindraje = Integer.parseInt(cilindrajeTF.getText());
                    vehiculoNuevo = new Moto(kilometraje, modeloTF.getText(), DescripcionTF.getText(), marcaTF.getText(), 
                                                EstadoCBox.getValue(), ciudadTF.getText(), precio, year, imagenes, 
                                                accidentes, nextId, capacidad, usuario, detallesInt, negociableCB.isSelected(), 
                                                mantenimientos, tipoSeleccionado, cilindraje);
                    break;
                default:
                    mostrarAlerta("Error", "Tipo de vehículo desconocido.");
                    return;
            }

            // Guardar el vehículo nuevo
            vehiculos.addFirst(vehiculoNuevo);
            VehiculoManager.guardarVehiculos(vehiculos);

            // Mostrar confirmación y volver a la pantalla anterior
            mostrarAlerta("Éxito", "El vehículo ha sido guardado exitosamente.");
            volver();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Por favor, ingrese valores numéricos válidos en los campos correspondientes.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
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
                e.printStackTrace();
            }
        }

        @FXML
        private void añadirAccidente() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("agregarAccidente.fxml"));
                Parent root = loader.load();

                AgregarAccidenteController controller = loader.getController();
                controller.setController(this);

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Agregar Accidente");
                stage.setScene(new Scene(root));
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mostrarAccidentes();
        }

        @FXML
        private void añadirMantenimiento() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("agregarMantenimiento.fxml"));
                Parent root = loader.load();

                AgregarMantenimientoController controller = loader.getController();
                controller.setMainController(this);

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
            mostrarMantenimientos();
        }

        public void agregarAccidente(Accidente accidente) {
            accidentes.addFirst(accidente);
            mostrarAccidentes();
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
                Label numProcesos = new Label("Número de Procesos: " + accidente.getListaMantenimiento().size());

                accidenteBox.getChildren().addAll(accidenteDescripcion, accidenteParteAfectada, accidenteFecha, numProcesos);

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
                controller.setMainController(this);
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
        public void mostrarDetallesInt(){
            traccionLabel.setVisible(true);
            transmisionLabel.setVisible(true);
            combustibleLabel.setVisible(true);
            placaLabel.setVisible(true);
            climatizadoLabel.setVisible(true);
            tipoMotorLabel.setVisible(true);
            
            traccionLabel.setText("Tracción: " + detallesInt.getTraccion());
            transmisionLabel.setText("Transmisión: " + detallesInt.getTransmision());
            combustibleLabel.setText("Combustible" + String.valueOf(detallesInt.getCombustible()));
            placaLabel.setText("Placa" + detallesInt.getPlaca());
            if(detallesInt.isClimatizado()){
                climatizadoLabel.setText("Climatizado");
            } else{
                climatizadoLabel.setText("No Climatizado");
            }
            tipoMotorLabel.setText("Tipo Motor: " + detallesInt.getTipoMotor());
        }
        
        @Override
        public void initialize(URL url, ResourceBundle rb) {
            imagenes = new CircularDoublyLinkedList<>();

            tipoVehiCBox.getItems().addAll(TipoVehiculo.values());
            tipoCarroCBox.getItems().addAll(TipoCarro.values());
            tipoVehiCBox.setOnAction(e -> updateFieldsVisibility());

            // Inicializar el ComboBox con los valores del enum Estado
            EstadoCBox.setItems(FXCollections.observableArrayList(Estado.values()));

            mostrarAccidentes();
            mostrarMantenimientos();
            mostrarImagenes();
            
            if(detallesInt == null){
                traccionLabel.setVisible(false);
                transmisionLabel.setVisible(false);
                combustibleLabel.setVisible(false);
                placaLabel.setVisible(false);
                climatizadoLabel.setVisible(false);
                tipoMotorLabel.setVisible(false);
            } else{
                mostrarDetallesInt();
            }
        }
    }

