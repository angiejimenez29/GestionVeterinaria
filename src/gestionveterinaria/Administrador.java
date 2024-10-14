package gestionveterinaria;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Administrador {
    private ArrayList<Cliente> clientes;
    private ArrayList<Mascota> mascotas;
    private ArrayList<String> adminUsuarios;
    private ArrayList<String> adminContras;
    private ArrayList<String> clienteUsuarios;
    private ArrayList<String> clienteContras;
    private Cita cita;
    private Personal personal;
    private static List<String> mascotasRegistradas = new ArrayList<>();
    private Cliente clienteActual = null;
    private Map<String, Cliente> mascotaClienteMap;
    

    String asciiArt = """
    _______  _______  _______  _______ 
   |       ||       ||       ||       |
   |  _____||   _   ||    ___||_     _|
   |  |____ |  | |  ||   |___   |   |  
   |_____  ||  |_|  ||    ___|  |   |  
   _____|  ||       ||   |      |   |  
   |_______||_______||___|      |___|  
            __   __  _______  _______  _______ 
           |  | |  ||       ||       ||       |
           |  |_|  ||    ___||_     _||  _____|
           |       ||   |___   |   |  | |_____ 
           |       ||    ___|  |   |  |_____  |
            |     | |   |___   |   |   ____|  |
             |___|  |_______|  |___|  |_______|""";

    public Administrador() {
        this.clientes = new ArrayList<>();
        this.mascotas = new ArrayList<>();
        mascotaClienteMap = new HashMap<>();
        this.adminUsuarios = new ArrayList<>();
        this.adminContras = new ArrayList<>();
        this.clienteUsuarios = new ArrayList<>();
        this.clienteContras = new ArrayList<>();
        this.personal = new Personal();
        
        adminUsuarios.add("admin");
        adminContras.add("123");
        
        clienteUsuarios.add("cliente");
        clienteContras.add("456");
    }

public void iniciarPrograma() {
    Scanner scanner = new Scanner(System.in);
    int intentosRestantes;
    boolean continuar = true;

    while (continuar) {
        intentosRestantes = 3;

        while (intentosRestantes > 0) {
            System.out.println(asciiArt);
            System.out.println("\n\n\t----- Iniciar Sesion -----\n");
            System.out.print("\tUsuario: ");
            String usuario = scanner.nextLine();
            System.out.print("\tContrasena: ");
            String contrasena = scanner.nextLine();
            System.out.println("\t---------------------------");

            if (iniciarSesionAdmin(usuario, contrasena, scanner)) {
                break;
            }
            if (iniciarSesionCliente(usuario, contrasena, scanner)) {
                break;
            }
            intentosRestantes--;
            System.out.println("\nUsuario o contrasena incorrectos. \nIntentos restantes: " + intentosRestantes);
        }

        if (intentosRestantes == 0) {
            System.out.println("\nHa superado el numero maximo de intentos, intentelo mas tarde");
            continuar = false;
        }
    }
    scanner.close();
}

private boolean iniciarSesionAdmin(String usuario, String contrasena, Scanner scanner) {
    if (adminUsuarios.contains(usuario)) {
        int index = adminUsuarios.indexOf(usuario);
        if (adminContras.get(index).equals(contrasena)) {
            System.out.println("\nInicio de sesion como administrador exitoso.");
            menuAdministrador(scanner);
            return true;
        }
    }
    return false;
}

private boolean iniciarSesionCliente(String usuario, String contrasena, Scanner scanner) {
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
     
    private void registrarAdministrador(Scanner scanner){
        System.out.println("\t------ Registro ------");
        System.out.print("\tUsuario: ");
        String nuevoUsuario = scanner.nextLine();
        if (adminUsuarios.contains(nuevoUsuario)) {
            System.out.println("Este nombre de usuario ya esta en uso");
            return;
        }
        System.out.print("\tContrasena: ");
        String nuevaContrasena = scanner.nextLine();

        adminUsuarios.add(nuevoUsuario);
        adminContras.add(nuevaContrasena);
        System.out.println("Administrador registrado exitosamente.");
    }
    
    public void registrarMascota(Mascota mascota, Cliente cliente) {
    mascotas.add(mascota);
    clientes.add(cliente);
    mascotaClienteMap.put(mascota.getNombreMascota(), cliente);
}


   private void registrarClienteYMascota(Scanner scanner) {
    System.out.println("-------- Registro --------\n");
    
    // CLIENTE
    System.out.println("| Cliente |");
    System.out.print("Nombres: ");
    String nombreCliente = scanner.nextLine();
    System.out.print("Apellidos: ");
    String apellidoCliente = scanner.nextLine();
    System.out.print("Telefono: ");
    String telefono = scanner.nextLine();
    
    String usuario = generarUsuario(nombreCliente, apellidoCliente);
    Cliente cliente = new Cliente(nombreCliente, apellidoCliente, telefono);
    clientes.add(cliente);
    clienteUsuarios.add(generarUsuario(nombreCliente, apellidoCliente));
    clienteContras.add(telefono);
    
    // MASCOTAS
    while (true) {
        System.out.println("\n| Mascota |");
        System.out.print("Nombre: ");
        String nombreMascota = scanner.nextLine();
        System.out.print("Especie: ");
        String especie = scanner.nextLine();
        System.out.print("Raza: ");
        String raza = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Sexo: ");
        String sexo = "";
        while (true) {
            System.out.print("| (1) Macho  | (2) Hembra  |");
            System.out.print("-> ");
            int opcionSexo = scanner.nextInt();
            scanner.nextLine();
            if (opcionSexo == 1) {
                sexo = "Macho";
                break;
            } else if (opcionSexo == 2) {
                sexo = "Hembra";
                break;
            } else {
                System.out.println("Opcion no valida");
            }
        }
        
        boolean esCastrada = false;
        while (true) {
            System.out.print("Castrado: ");
            System.out.print("| (1) Si  | (2) No  |");
            System.out.print("-> ");
            int opcionCastracion = scanner.nextInt();
            scanner.nextLine();
            if (opcionCastracion == 1) {
                esCastrada = true;
                break;
            } else if (opcionCastracion == 2) {
                esCastrada = false;
                break;
            } else {
                System.out.println("Opcion no valida");
            }
        }

        boolean esGestante = false;
        if (!esCastrada && sexo.equals("Hembra")) {
            while (true) {
                System.out.print("Gestante: ");
                System.out.print("| (1) Si  | (2) No  |");
                System.out.print("-> ");
                int opcionGestante = scanner.nextInt();
                scanner.nextLine();
                if (opcionGestante == 1) {
                    esGestante = true;
                    break;
                } else if (opcionGestante == 2) {
                    esGestante = false;
                    break;
                } else {
                    System.out.println("Opcion no valida");
                }
            }
        }
        
        System.out.print("Color: ");
        String color = scanner.nextLine();
        System.out.print("Peso(en kg): ");
        double peso = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Fecha de nacimiento: ");
        String fechaNacimiento = scanner.nextLine();
        System.out.print("Observaciones: ");
        String observaciones = scanner.nextLine();
        
        // FECHA ACTUAL
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        String fechaRegistro = fechaActual.format(formatter);

        // Crear mascota y asignar cliente
        Mascota mascota = new Mascota(nombreMascota, especie, raza, edad, sexo, 
                color, peso, fechaNacimiento, fechaRegistro, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), observaciones, esCastrada, esGestante, cliente);
        
        mascota.gestionarVacunas(scanner);
        mascota.gestionarAlergias(scanner);
        mascota.gestionarEnfermedades(scanner);
        registrarMascota(mascota, cliente);
        cliente.agregarMascota(mascota);
        mascotasRegistradas.add(nombreMascota);
        
        System.out.println("\nMascota registrada exitosamente.");

        System.out.print("Agregar otra mascota (si/no): ");
        String respuesta = scanner.nextLine();
        if (!respuesta.equalsIgnoreCase("Si")) {
            break; // salir del bucle si el usuario no desea agregar más mascotas
        }
    }
    
    System.out.println("\nRegistro exitoso");
    System.out.println("Usuario creado: " + usuario);
    System.out.println("Contrasena: " + telefono);
}
 
    private String generarUsuario(String nombre, String apellido){
        return(nombre.trim().toLowerCase()+"."+apellido.trim().toLowerCase().replaceAll(" "," "));
    }
    
    private void menuAdministrador(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n------ Administrador ------");
            System.out.println("1. Registrar cliente y mascota");
            System.out.println("2. Registrar nuevo administrador");
            System.out.println("3. Historial de citas");
            System.out.println("4. Historial medico");  
            System.out.println("5. Gestionar citas");
            System.out.println("6. Administrar personal");
            System.out.println("7. Cerrar sesion");
            System.out.println("8. Salir del programa");
            System.out.println("---------------------------");
            System.out.print("\nSeleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\n");

            switch (opcion) {
                case 1:
                    registrarClienteYMascota(scanner);
                    break;
                case 2:
                    registrarAdministrador(scanner);
                    break;
                case 3:
                    Cita.verHistorialCitas();
                    break;
                case 4:
                    HistorialMedico.buscarHistorial(mascotas, mascotaClienteMap, scanner); 
                    break;
                case 5:
                    menuCitas(scanner);
                    break;
                case 6:
                    menuPersonal(scanner);
                    break;
                case 7:
                    System.out.println("Cerrando sesion");
                    return;
                case 8: 
                    System.out.println("Saliendo del programa...");
                    System.exit(0);
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (true);
    }

    private void menuPersonal(Scanner scanner) {
        personal.iniciarMenu();
    }
    
    private void menuCitas(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n--- Citas ---");
            System.out.println("1. Agendar cita");
            System.out.println("2. Modificar cita");
            System.out.println("3. Cancelar cita");
            System.out.println("4. Volver");
            System.out.println("------------------");
            System.out.print("\nSeleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\n");

            switch (opcion) {
                case 1:
                    Cita.agendarCita(scanner);
                    break;
                case 2:
                    Cita.modificarCita(scanner);
                    break;
                case 3:
                    Cita.cancelarCita(scanner);
                    break;
                case 4:
                    System.out.println("");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 4);
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

    public static boolean isMascotaRegistrada(String nombreMascota) {
        return mascotasRegistradas.contains(nombreMascota);
    }

private void cerrarSesionCliente(){
    System.out.println("Cerrando sesión del cliente...");
    clienteActual = null;
}
    public static void main(String[] args) {
        Administrador administrador = new Administrador();
        administrador.iniciarPrograma();
    }
}
