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
import javafx.scene.image.Image;
import tda.*;
/**
 *
 * @author asala
 */
public class VehiculoManager {

    private static final String acuaticoArchivos = "src/main/java/archivos/acuaticoArchivos.csv";
    private static final String aereoArchivos = "src/main/java/archivos/aereoArchivos.csv";
    private static final String carroArchivos = "src/main/java/archivos/carroArchivos.csv";
    private static final String motoArchivos = "src/main/java/archivos/motoArchivos.csv";
    private static final String pesadoArchivos = "src/main/java/archivos/pesadoArchivos.csv";

    public static List<Vehiculo> cargarVehiculos(String tipo) throws IOException {
        String fileName = obtenerNombreArchivo(tipo);
        List<Vehiculo> vehiculos = new ArrayList<>(Vehiculo.class);

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Vehiculo vehiculo = crearVehiculoDesdeLinea(tipo, linea.split(","));
                vehiculos.addLast(vehiculo);
            }
        }

        return vehiculos;
    }
    
    public static List<Carro> cargarCarros() throws IOException {
    String fileName = carroArchivos;
    List<Carro> carros = new ArrayList<>(Carro.class);

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            Carro carro = (Carro) crearVehiculoDesdeLinea("carro", linea.split(","));
            carros.addLast(carro);
        }
    }

    return carros;
}

public static List<Moto> cargarMotos() throws IOException {
    String fileName = motoArchivos;
    List<Moto> motos = new ArrayList<>(Moto.class);

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            Moto moto = (Moto) crearVehiculoDesdeLinea("moto", linea.split(","));
            motos.addLast(moto);
        }
    }

    return motos;
}

public static List<Acuatico> cargarAcuaticos() throws IOException {
    String fileName = acuaticoArchivos;
    List<Acuatico> acuaticos = new ArrayList<>(Acuatico.class);

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            Acuatico acuatico = (Acuatico) crearVehiculoDesdeLinea("acuatico", linea.split(","));
            acuaticos.addLast(acuatico);
        }
    }

    return acuaticos;
}

public static List<Aereo> cargarAereos() throws IOException {
    String fileName = aereoArchivos;
    List<Aereo> aereos = new ArrayList<>(Aereo.class);

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            Aereo aereo = (Aereo) crearVehiculoDesdeLinea("aereo", linea.split(","));
            aereos.addLast(aereo);
        }
    }

    return aereos;
}

public static List<Pesado> cargarPesados() throws IOException {
    String fileName = pesadoArchivos;
    List<Pesado> pesados = new ArrayList<>(Pesado.class);

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            Pesado pesado = (Pesado) crearVehiculoDesdeLinea("pesado", linea.split(","));
            pesados.addLast(pesado);
        }
    }

    return pesados;
}


    public static void guardarVehiculos(String tipo, List<Vehiculo> vehiculos) throws IOException {
        String fileName = obtenerNombreArchivo(tipo);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Vehiculo vehiculo : vehiculos) {
                String linea = crearLineaDesdeVehiculo(vehiculo);
                bw.write(linea);
                bw.newLine();
            }
        }
    }

    private static String obtenerNombreArchivo(String tipo) {
        switch (tipo.toLowerCase()) {
            case "acuatico":
                return acuaticoArchivos;
            case "aereo":
                return aereoArchivos;
            case "carro":
                return carroArchivos;
            case "moto":
                return motoArchivos;
            case "pesado":
                return pesadoArchivos;
            default:
                throw new IllegalArgumentException("Tipo de vehículo desconocido: " + tipo);
        }
    }

    private static Vehiculo crearVehiculoDesdeLinea(String tipo, String[] linea) {
        int id = Integer.parseInt(linea[0]);
        String kilometraje = linea[1];
        String modelo = linea[2];
        String ciudadv = linea[3];
        double precio = Double.parseDouble(linea[4]);
        String year = linea[5];
        CircularDoublyLinkedList<Image> imagenes = new CircularDoublyLinkedList<>(); // Implementa la lógica para cargar imágenes
        LinkedList<Accidente> accidentes = new LinkedList<>(); // Implementa la lógica para cargar accidentes
        int capacidad = Integer.parseInt(linea[6]);
        DetallesVehiExt detallesExt = new DetallesVehiExt(linea[7], linea[8], Boolean.parseBoolean(linea[9]), Boolean.parseBoolean(linea[10]));
        DetallesVehiInt detallesInt = new DetallesVehiInt(linea[11], linea[12], TipoCombustible.valueOf(linea[13]), linea[14], Boolean.parseBoolean(linea[15]), linea[16]);
        LinkedList<Proceso> lista = new LinkedList<>(); // Implementa la lógica para cargar procesos
        Usuario vendedor = new Usuario("Melanie","msala@gaisd.com","we21312","contrasena"); // Implementa la lógica para cargar usuario

        switch (tipo.toLowerCase()) {
            case "acuatico":
                String tipoacua = linea[18];
                return new Acuatico(id, kilometraje, modelo, ciudadv, precio, year, imagenes, accidentes, capacidad, detallesExt, detallesInt, lista, vendedor, tipoacua);
            case "aereo":
                String tipoAeronave = linea[18];
                double pesoMaximoDespegue = Double.parseDouble(linea[19]);
                int rangoVuelo = Integer.parseInt(linea[20]);
                return new Aereo(id, kilometraje, modelo, ciudadv, precio, year, imagenes, accidentes, capacidad, detallesExt, detallesInt, lista, vendedor, tipoAeronave, pesoMaximoDespegue, rangoVuelo);
            case "carro":
                String tipocarro = linea[18];
                return new Carro(id, kilometraje, modelo, ciudadv, precio, year, imagenes, accidentes, capacidad, detallesExt, detallesInt, lista, vendedor, tipocarro);
            case "moto":
                int cilindraje = Integer.parseInt(linea[18]);
                return new Moto(id, kilometraje, modelo, ciudadv, precio, year, imagenes, accidentes, capacidad, detallesExt, detallesInt, lista, vendedor, cilindraje);
            case "pesado":
                double pesoMax = Double.parseDouble(linea[18]);
                double pesoMin = Double.parseDouble(linea[19]);
                return new Pesado(id, kilometraje, modelo, ciudadv, precio, year, imagenes, accidentes, capacidad, detallesExt, detallesInt, lista, vendedor, pesoMax, pesoMin);
            default:
                throw new IllegalArgumentException("Tipo de vehículo desconocido: " + tipo);
        }
    }

    private static String crearLineaDesdeVehiculo(Vehiculo vehiculo) {
        StringBuilder sb = new StringBuilder();
        sb.append(vehiculo.getId()).append(",");
        sb.append(vehiculo.getKilometraje()).append(",");
        sb.append(vehiculo.getModelo()).append(",");
        sb.append(vehiculo.getCiudadv()).append(",");
        sb.append(vehiculo.getPrecio()).append(",");
        sb.append(vehiculo.getYear()).append(",");
        sb.append(vehiculo.getCapacidad()).append(",");
        sb.append(vehiculo.getDetallesExt().getDescripcion()).append(",");
        sb.append(vehiculo.getDetallesExt().getMarca()).append(",");
        sb.append(vehiculo.getDetallesExt().getUsado()).append(",");
        sb.append(vehiculo.getDetallesExt().getNegociable()).append(",");
        sb.append(vehiculo.getDetallesInt().getTraccion()).append(",");
        sb.append(vehiculo.getDetallesInt().getTransmission()).append(",");
        sb.append(vehiculo.getDetallesInt().getCombustible()).append(",");
        sb.append(vehiculo.getDetallesInt().getPlacade()).append(",");
        sb.append(vehiculo.getDetallesInt().getClimatizado()).append(",");
        sb.append(vehiculo.getDetallesInt().getTipoMotor()).append(",");
        sb.append(vehiculo.getVendedor().getNombre()); // Implementa la lógica para obtener el nombre del vendedor

        if (vehiculo instanceof Acuatico) {
            sb.append(",").append(((Acuatico) vehiculo).getTipoacua());
        } else if (vehiculo instanceof Aereo) {
            sb.append(",").append(((Aereo) vehiculo).getTipoAeronave());
            sb.append(",").append(((Aereo) vehiculo).getPesoMaximoDespegue());
            sb.append(",").append(((Aereo) vehiculo).getRangoVuelo());
        } else if (vehiculo instanceof Carro) {
            sb.append(",").append(((Carro) vehiculo).getTipocarro());
        } else if (vehiculo instanceof Moto) {
            sb.append(",").append(((Moto) vehiculo).getCilindraje());
        } else if (vehiculo instanceof Pesado) {
            sb.append(",").append(((Pesado) vehiculo).getPesoMax());
            sb.append(",").append(((Pesado) vehiculo).getPesoMin());
        }

        return sb.toString();
    }

    private static LinkedList<Proceso> cargarProcesos(String procesosStr) {
        LinkedList<Proceso> lista = new LinkedList<>();
        String[] procesosArray = procesosStr.split(";");
        for (String procesoStr : procesosArray) {
            String[] detalles = procesoStr.split(":");
            String descripcion = detalles[0];
            String tipo_proceso = detalles[1];
            lista.addLast(new Proceso(descripcion, tipo_proceso));
        }
        return lista;
    }

    private static String crearLineaDesdeProcesos(LinkedList<Proceso> lista) {
        StringBuilder sb = new StringBuilder();
        for (Proceso proceso : lista) {
            if (sb.length() > 0) {
                sb.append(";");
            }
            sb.append(proceso.getDescripcion()).append(":").append(proceso.getTipoProceso());
        }
        return sb.toString();
    }
}