package gestionveterinaria;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;


public class Cliente {
    private Scanner scanner = new Scanner(System.in);
    private String nombre;
    private String apellido;
    private String telefono;
    private ArrayList<Mascota> mascotas;
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static ArrayList<String> clienteUsuarios = new ArrayList<>(); // Hacer estática
    private static ArrayList<String> clienteContras = new ArrayList<>(); 
    private Cliente clienteActual = null;

    public Cliente(String nombre, String apellido, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.mascotas = new ArrayList<>();
    }
    
    public Cliente(){
        this.mascotas = new ArrayList<>();
    }
    
    private void menuCliente(Scanner scanner, String nombreCliente, String apellidoCliente, Cliente cliente) {
        int opcion;
        boolean seguir = true;
        while(seguir){
            System.out.println("\n----- Cliente -----");
            System.out.println("\n" + nombreCliente + " " + apellidoCliente);
            System.out.println("1. Historial medico de mis mascotas");
            System.out.println("2. Agendar cita");
            System.out.println("3. Modificar cita");
            System.out.println("4. Cancelar cita");
            System.out.println("5. Cerrar sesion");
            System.out.println("6. Salir del programa");
            System.out.println("------------------");
            System.out.print("\nSeleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\n");

            switch (opcion) {
                case 1:
                    HistorialMedico.buscarHistorialCliente(cliente.getMascotas(), cliente, scanner);;
                    break;
                case 2:
                    Cita.agendarCita(scanner);
                    break;
                case 3:
                    Cita.modificarCita(scanner);
                    break;
                case 4:
                    Cita.cancelarCita(scanner);
                    break;
                case 5:
                    cerrarSesionCliente();
                    seguir=false;               
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    System.exit(0);
                default:
                    System.out.println("Opcion no valida");
            }
    }
    }
    
    public void registroCliente(){
    System.out.println("-------- Registro --------\n");
    System.out.println("| Cliente |");
    System.out.print("Nombres: ");
    String nombreCliente = scanner.nextLine();
    System.out.print("Apellidos: ");
    String apellidoCliente = scanner.nextLine();
    System.out.print("Telefono: ");
    String telefono = scanner.nextLine();

    for(Cliente c : clientes){
        if(c.getTelefono().equals(telefono)){
            System.out.println("Este cliente ya esta registrado");
            return;
        }
    }
    
    String usuario = generarUsuario(nombreCliente, apellidoCliente);
    Cliente nuevoCliente = new Cliente(nombreCliente, apellidoCliente, telefono);
    clientes.add(nuevoCliente);
    clienteUsuarios.add(generarUsuario(nombreCliente, apellidoCliente));
    clienteContras.add(telefono);
    
    System.out.println("\nRegistro exitoso");
    System.out.println("Usuario creado: " + usuario);
    System.out.println("Contrasena: " + telefono);
    
    }
    
    private String generarUsuario(String nombre, String apellido){
        return(nombre.trim().toLowerCase()+"."+apellido.trim().toLowerCase().replaceAll(" "," "));
    }
    
    public boolean iniciarSesionCliente(String usuario, String contrasena, Scanner scanner) {
        if (clienteUsuarios.contains(usuario)) {
            int index = clienteUsuarios.indexOf(usuario);
            if (index >= 0 && clienteContras.get(index).equals(contrasena)) {
                clienteActual = clientes.get(index);
                System.out.println("\nInicio de sesion como cliente exitoso.");
                menuCliente(scanner, clienteActual.getNombre(), clienteActual.getApellido(), clienteActual);
                return true;
            }
        }
        return false;
    }
    
    private void cerrarSesionCliente(){
        System.out.println("Cerrando sesión del cliente...");
        clienteActual = null;
    }
    
    public static void mostrarClientes() {
    if (clientes.isEmpty()) {
        System.out.println("No hay clientes registrados.");
        return;
    }

    System.out.println("--- Asignar mascota a un cliente ---");
    for (int i = 0; i < clientes.size(); i++) {
        Cliente c = clientes.get(i);
        System.out.println((i + 1) + ". " + c.getNombre() + " " + c.getApellido());
    }
    }

    public static void mostrarTablaClientesMascotas() {
    // Encabezados de la tabla
    System.out.printf("%-30s %-15s %-15s %-15s %-15s\n", "Cliente", "Mascota", "Raza", "Especie", "Teléfono");
    System.out.println("-------------------------------------------------------------------------------------------------");

    // Recorrer la lista de clientes y sus mascotas
    for (Cliente cliente : clientes) {
        // Si el cliente no tiene mascotas, continúa con el siguiente
        if (cliente.getMascotas() == null || cliente.getMascotas().isEmpty()) {
            System.out.printf("%-30s %-15s %-15s %-15s %-15s\n", 
                              cliente.getNombre() + " " + cliente.getApellido(), 
                              "No tiene mascotas", "", "", cliente.getTelefono());
            continue;
        }

        // Mostrar la información de las mascotas
        for (Mascota mascota : cliente.getMascotas()) {
            System.out.printf("%-30s %-15s %-15s %-15s %-15s\n", 
                              cliente.getNombre() + " " + cliente.getApellido(), 
                              mascota.getNombreMascota(), 
                              mascota.getRaza(), 
                              mascota.getEspecie(), 
                              cliente.getTelefono());
        }
    }
}
    public static ArrayList<Cliente> getClientes() {
        return clientes; // Proporciona acceso a la lista de clientes
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getApellido() {
        return apellido;
    }

    public void agregarMascota(Mascota mascota){
        if (mascotas == null) {
            mascotas = new ArrayList<>(); // Asegurarse de que la lista esté inicializada
        }
        mascotas.add(mascota);
    }
    
    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public String getTelefono() {
        return telefono;
    }
    
}
