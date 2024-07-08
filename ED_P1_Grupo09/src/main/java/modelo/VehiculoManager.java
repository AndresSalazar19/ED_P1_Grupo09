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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.image.Image;
import tda.*;
/**
 *
 * @author asala
 */
public class VehiculoManager {
    private static final String vehiculoArchivos = "src/main/java/archivos/vehiculoArchivos.csv";
    private static final String accidenteArchivos = "src/main/java/archivos/accidenteArchivos.csv";
    private static final String usuarioArchivos = "src/main/java/archivos/usuarioArchivos.csv";

    public static List<Vehiculo> cargarVehiculos() throws IOException {
        List<Vehiculo> vehiculos = new ArrayList<>(Vehiculo.class);

        try (BufferedReader br = new BufferedReader(new FileReader(vehiculoArchivos))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Vehiculo vehiculo = crearVehiculoDesdeLinea(linea.split(","));
                vehiculos.addFirst(vehiculo);
            }
        }

        return vehiculos;
    }
    
      public static List<Carro> cargarCarros() throws IOException {
        return filtrarVehiculosPorTipo(Carro.class);
    }

    public static List<Moto> cargarMotos() throws IOException {
        return filtrarVehiculosPorTipo(Moto.class);
    }

    public static List<Acuatico> cargarAcuaticos() throws IOException {
        return filtrarVehiculosPorTipo(Acuatico.class);
    }

    public static List<Aereo> cargarAereos() throws IOException {
        return filtrarVehiculosPorTipo(Aereo.class);
    }

    public static List<Pesado> cargarPesados() throws IOException {
        return filtrarVehiculosPorTipo(Pesado.class);
    }

    private static <T extends Vehiculo> List<T> filtrarVehiculosPorTipo(Class<T> tipo) throws IOException {
        List<Vehiculo> todosVehiculos = cargarVehiculos();
        List<T> vehiculosFiltrados = new ArrayList<>(tipo);

        for (Vehiculo vehiculo : todosVehiculos) {
            if (tipo.isInstance(vehiculo)) {
                vehiculosFiltrados.addLast(tipo.cast(vehiculo));
            }
        }

        return vehiculosFiltrados;
    }
    
    private static Vehiculo crearVehiculoDesdeLinea(String[] linea) throws IOException {
        int id = Integer.parseInt(linea[0]);
        int kilometraje = Integer.parseInt(linea[1]);

        String modelo = linea[2];
        String descripcion = linea[3];
        String marca = linea[4];
        Estado estado = Estado.valueOf(linea[5]);
        String ciudad = linea[6];
        double precio = Double.parseDouble(linea[7]);
        String year = linea[8];
        
        CircularDoublyLinkedList<Image> imagenes = cargarImagenes(linea[0]);
                                    
        LinkedList<Accidente> accidentes = cargarAccidentes(linea[9]);
    
        int capacidad = Integer.parseInt(linea[10]);
        String[] detallesIntArr = linea[11].replace("(", "").replace(")", "").split("~");
    
        DetallesVehiInt detallesInt = new DetallesVehiInt(detallesIntArr[0], detallesIntArr[1], TipoCombustible.valueOf(detallesIntArr[2]), detallesIntArr[3], Boolean.parseBoolean(detallesIntArr[4]), detallesIntArr[5]);

        Usuario vendedor = cargarUsuario(Integer.parseInt(linea[12]));       
        boolean negociable = Boolean.parseBoolean(linea[13]);
        LinkedList<Mantenimiento> mantenimientos = cargarMantenimientos(linea[14]);
        TipoVehiculo tipoVehiculo = TipoVehiculo.valueOf(linea[15]);

        switch (tipoVehiculo) {
            case ACUATICO:
                String tipoacua = linea[16];
                return new Acuatico(kilometraje, modelo, descripcion, marca, estado, ciudad, precio, year, imagenes, accidentes, id, capacidad, vendedor, detallesInt, negociable ,mantenimientos, tipoVehiculo ,tipoacua);
            case AEREO:
                String tipoAeronave = linea[16];
                double pesoMaximoDespegue = Double.parseDouble(linea[17]);
                int rangoVuelo = Integer.parseInt(linea[18]);
                return new Aereo(kilometraje, modelo, descripcion, marca, estado, ciudad, precio, year, imagenes, accidentes, id, capacidad, vendedor, detallesInt, negociable,mantenimientos, tipoVehiculo, tipoAeronave, pesoMaximoDespegue, rangoVuelo);
            case CARRO:
                TipoCarro tipocarro = TipoCarro.valueOf(linea[16]);
                return new Carro(kilometraje, modelo, descripcion, marca, estado, ciudad, precio, year, imagenes, accidentes, id, capacidad, vendedor, detallesInt, negociable,mantenimientos, tipoVehiculo, tipocarro);
            case MOTO:
                int cilindraje = Integer.parseInt(linea[16]);
                return new Moto(kilometraje, modelo, descripcion, marca, estado, ciudad, precio, year, imagenes, accidentes, id, capacidad, vendedor, detallesInt, negociable,mantenimientos,tipoVehiculo, cilindraje);
            case PESADO:
                double pesoMax = Double.parseDouble(linea[16]);
                double pesoMin = Double.parseDouble(linea[17]);
                return new Pesado(kilometraje, modelo, descripcion, marca, estado, ciudad, precio, year, imagenes, accidentes, id, capacidad, vendedor, detallesInt, negociable,mantenimientos, tipoVehiculo, pesoMax, pesoMin);
            default:
                throw new IllegalArgumentException("Tipo de vehículo desconocido: " + tipoVehiculo);
        }
    }

    private static CircularDoublyLinkedList<Image> cargarImagenes(String imagenesStr) {
        CircularDoublyLinkedList<Image> imagenes = ImageLoader.loadImagesFromFolder("src/main/resources/imagenes/vehiculos/" + imagenesStr);
        return imagenes;
    }

    private static LinkedList<Accidente> cargarAccidentes(String accidentesStr) throws IOException {
    LinkedList<Accidente> accidentes = new LinkedList<>();
    if (accidentesStr.replace("[", "").replace("]", "").isEmpty()) {
        return accidentes;
    }
    
    String[] idAccidentes = accidentesStr.replace("[", "").replace("]", "").split(";");
    for (String idAccidente : idAccidentes) {
        accidentes.addFirst(cargarAccidente(Integer.parseInt(idAccidente)));
    }
    return accidentes;
}


    private static Accidente cargarAccidente(int idAccidente) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader br = new BufferedReader(new FileReader(accidenteArchivos))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (Integer.parseInt(datos[0]) == idAccidente) {
                    String descripcion = datos[1];
                    String parteAfectada = datos[2];
                    LocalDate fecha = LocalDate.parse(datos[3], formatter);
                    LinkedList<Mantenimiento> mantenimientos = cargarMantenimientos(datos[4]);
                    return new Accidente(idAccidente, descripcion, parteAfectada, fecha, mantenimientos);
                }
            }
        }
        return null;
    }

    private static Usuario cargarUsuario(int idVendedor) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(usuarioArchivos))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (Integer.parseInt(datos[0]) == idVendedor) {
                    String nombre = datos[1];
                    String correo = datos[2];
                    String telefono = datos[3];
                    String contrasena = datos[4];
                    return new Usuario(idVendedor, nombre, correo, telefono, contrasena);
                }
            }
        }
        return null;
    }

    private static LinkedList<Mantenimiento> cargarMantenimientos(String mantenimientosStr) {
        LinkedList<Mantenimiento> lista = new LinkedList<>();
        if (!mantenimientosStr.equals("[]")){
        String[] mantenimientosArray = mantenimientosStr.replace("[", "").replace("]", "").split(";");
            for (String mantenimientostr : mantenimientosArray) {
                String[] detalles = mantenimientostr.replace("(","").replace(")","").split("~");
                String descripcion = detalles[0];
                String tipo_mantenimiento = detalles[1];
                lista.addFirst(new Mantenimiento(descripcion, tipo_mantenimiento));
            }
            return lista;
        } else {
            return lista;
        }
    }

    public static void guardarVehiculos(List<Vehiculo> vehiculos) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(vehiculoArchivos))) {
            for (Vehiculo vehiculo : vehiculos) {
                String linea = crearLineaDesdeVehiculo(vehiculo);
                bw.write(linea);
                bw.newLine();
            }
        }
    }

    private static String crearLineaDesdeVehiculo(Vehiculo vehiculo) {
        StringBuilder sb = new StringBuilder();
        sb.append(vehiculo.getId()).append(",");
        sb.append(vehiculo.getKilometraje()).append(",");
        sb.append(vehiculo.getModelo()).append(",");
        sb.append(vehiculo.getDescripcion()).append(",");
        sb.append(vehiculo.getMarca()).append(",");
        sb.append(vehiculo.getEstado()).append(",");
        sb.append(vehiculo.getCiudad()).append(",");
        sb.append(vehiculo.getPrecio()).append(",");
        sb.append(vehiculo.getYear()).append(",");
        sb.append(crearLineaDesdeImagenes(vehiculo.getImagenes())).append(",");
        sb.append(crearLineaDesdeAccidentes(vehiculo.getAccidentes())).append(",");
        sb.append(vehiculo.getCapacidad()).append(",");
        sb.append("(")
          .append(vehiculo.getDetallesInt().getTraccion()).append(",")
          .append(vehiculo.getDetallesInt().getTransmision()).append(",")
          .append(vehiculo.getDetallesInt().getCombustible()).append(",")
          .append(vehiculo.getDetallesInt().getPlaca()).append(",")
          .append(vehiculo.getDetallesInt().isClimatizado()).append(",")
          .append(vehiculo.getDetallesInt().getTipoMotor())
          .append("),");
        sb.append(vehiculo.getVendedor().getId()).append(",");
        sb.append(vehiculo.isNegociable()).append(",");
        sb.append(crearLineaDesdeMantenimientos(vehiculo.getMantenimientos())).append(",");
        sb.append(vehiculo.getTipoVehiculo());

        if (vehiculo instanceof Acuatico) {
            sb.append(",").append(((Acuatico) vehiculo).getTipoacua());
        } else if (vehiculo instanceof Aereo) {
            sb.append(",").append(((Aereo) vehiculo).getTipoAeronave());
            sb.append(",").append(((Aereo) vehiculo).getPesoMaximoDespegue());
            sb.append(",").append(((Aereo) vehiculo).getRangoVuelo());
        } else if (vehiculo instanceof Carro) {
            sb.append(",").append(String.valueOf(((Carro) vehiculo).getTipoCarro()));
        } else if (vehiculo instanceof Moto) {
            sb.append(",").append(((Moto) vehiculo).getCilindraje());
        } else if (vehiculo instanceof Pesado) {
            sb.append(",").append(((Pesado) vehiculo).getPesoMax());
            sb.append(",").append(((Pesado) vehiculo).getPesoMin());
        }

        return sb.toString();
    }

    private static String crearLineaDesdeImagenes(CircularDoublyLinkedList<Image> imagenes) {
        // Implementa la lógica para crear la línea de imágenes
        return "img1.jpg;img2.jpg"; // Ejemplo
    }

    private static String crearLineaDesdeAccidentes(LinkedList<Accidente> accidentes) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Accidente accidente : accidentes) {
            if (sb.length() > 1) {
                sb.append(",");
            }
            sb.append(accidente.getId());
        }
        sb.append("]");
        return sb.toString();
    }

    private static String crearLineaDesdeMantenimientos(LinkedList<Mantenimiento> mantenimientos) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Mantenimiento mantenimiento : mantenimientos) {
            if (sb.length() > 1) {
                sb.append(";");
            }
            sb.append("(")
              .append(mantenimiento.getDescripcion()).append(",")
              .append(mantenimiento.getTipoMantenimiento())
              .append(")");
        }
        sb.append("]");
        return sb.toString();
    }
}
