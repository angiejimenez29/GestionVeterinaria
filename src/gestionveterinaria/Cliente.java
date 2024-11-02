package gestionveterinaria;

import java.util.ArrayList;
import java.util.Scanner;

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
            System.out.print("\n");
            System.out.print("\n");
            String asciiArt = """
 __   ___ __   ___ __
/  |   | |_ |\\| | |_ 
\\__|___|_|__| | | |__ 
""";

System.out.println(asciiArt);
            System.out.println("\nBienvenido/a " + nombreCliente + " " + apellidoCliente);
            System.out.print("\n");
            System.out.println("1. Historial medico de mis mascotas");
            System.out.println("2. Agendar cita");
            System.out.println("3. Modificar cita");
            System.out.println("4. Cancelar cita");
            System.out.println("5. Cerrar sesion");
            System.out.println("6. Salir del programa");
            System.out.println("------------------");
            System.out.print("\n--> ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\n");

            switch (opcion) {
                case 1:
                    HistorialMedico.buscarHistorialCliente(cliente.getMascotas(), cliente, scanner);;
                    break;
                case 2:
                    Cita.agendarCita(false, nombreCliente, apellidoCliente);
                    break;
                case 3:
                    Cita.editarCita(false, nombreCliente, apellidoCliente);
                    break;
                case 4:
                    Cita.cancelarCita(false, nombreCliente, apellidoCliente);
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
    String asciiArt = """
 _  __ _____ _____ _  _ 
|_)|_ /__ | (_  | |_)/ \\
| \\|__\\_|_|___) | | \\\\_/
""";

System.out.println(asciiArt);
    System.out.println("| Cliente |");
    System.out.print("Nombre: ");
    String nombreCliente = scanner.nextLine();
    System.out.print("Apellido: ");
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
    System.out.print("\n");
    System.out.println("Usuario creado: " + usuario);
    System.out.println("Contrasena: " + telefono);
            System.out.print("\n");
            System.out.print("\n");
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
        System.out.print("\n");
        return;
    }

    String asciiArt = """
 _  _____ __    _  _        _  __ __ _ ___ _
|_|(_  | /__|\\||_||_)   |V||_|(_ /  / \\ | |_|
| |__)_|_\\_|| || || \\   | || |__)\\__\\_/ | | |
""";

System.out.println(asciiArt);
System.out.print("\n");
System.out.println("------- Clientes -------");
    for (int i = 0; i < clientes.size(); i++) {
        Cliente c = clientes.get(i);
        System.out.print("\n");
        System.out.println((i + 1) + ". " + c.getNombre() + " " + c.getApellido());
    }
    }

    public static void mostrarTablaClientesMascotas() {
    System.out.printf("%-30s %-15s %-15s %-15s %-15s\n", "Cliente", "Mascota", "Raza", "Especie", "Telefono");
    System.out.println("-------------------------------------------------------------------------------------------------");

    for (Cliente cliente : clientes) {
        if (cliente.getMascotas() == null || cliente.getMascotas().isEmpty()) {
            System.out.printf("%-30s %-15s %-15s %-15s %-15s\n", 
                              cliente.getNombre() + " " + cliente.getApellido(), 
                              "", "", "", cliente.getTelefono());
            continue;
        }

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
        return clientes;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getApellido() {
        return apellido;
    }

    public void agregarMascota(Mascota mascota){
        if (mascotas == null) {
            mascotas = new ArrayList<>();
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
