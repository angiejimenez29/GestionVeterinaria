package gestionveterinaria;

import java.util.ArrayList;
import java.util.Scanner;

public class Administrador {
    private ArrayList<Cliente> clientes;
    private ArrayList<Mascota> mascotas;
    private ArrayList<String> adminUsuarios;
    private ArrayList<String> adminContras;
    private Cita cita;
    private Personal personal;
    

    String asciiArt = """
    _______  _______  _______  _______ 
    |       ||       ||       ||       |
    |  _____||   _   ||    ___||_     _|
    |  |____ |  | |  ||   |___   |   |  
    |_____  ||  |_|  ||    ___|  |   |  
    _____| ||       ||   |      |   |  
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
        this.adminUsuarios = new ArrayList<>();
        this.adminContras = new ArrayList<>();
    }

    public void iniciarPrograma() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println(asciiArt);
            System.out.println("\n");
            System.out.println("1. Iniciar sesion como Administrador");
            System.out.println("2. Acceder como Cliente");
            System.out.println("3. Salir");
            System.out.print("\nSeleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    iniciarSesionAdministrador(scanner);
                    break;
                case 2:
                    menuCliente(scanner);
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 3);
        scanner.close();
    }

    private void iniciarSesionAdministrador(Scanner scanner) {
        System.out.print("Tiene una cuenta como administrador (S/N): ");
        String respuesta = scanner.nextLine().toUpperCase();

        if (respuesta.equals("N")) {
            crearCuentaAdministrador(scanner);
        } else {
            if (adminUsuarios.isEmpty()) {
                System.out.println("\nNo existe ninguna cuenta de administrador.");
                System.out.println("Debe crear una cuenta de administrador primero.");
                crearCuentaAdministrador(scanner);
                return;
            }

            boolean exito = false;
            while (!exito) {
                System.out.print("\nUsuario: ");
                String usuario = scanner.nextLine();
                System.out.print("Contrasena: ");
                String contra = scanner.nextLine();

                if (adminUsuarios.contains(usuario) && adminContras.contains(contra)) {
                    int index = adminUsuarios.indexOf(usuario);
                    if (adminContras.get(index).equals(contra)) {
                        System.out.println("\nInicio de sesion exitoso.");
                        exito = true; 
                    }
                } else {
                    System.out.println("\nUsuario o contrasena incorrectos.");
                    System.out.print("Desea intentar de nuevo? (S/N): ");
                    String reintentar = scanner.nextLine().toUpperCase();

                    if (!reintentar.equals("S")) {
                        System.out.println("");
                        break; 
                    }
                }
            }
        }
        menuAdministrador(scanner);
    }

    private void crearCuentaAdministrador(Scanner scanner) {
        System.out.print("\n --- Crear cuenta ---");
        System.out.print("\nUsuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Contrasena: ");
        String contra = scanner.nextLine();
        adminUsuarios.add(usuario);
        adminContras.add(contra);
        System.out.println("Cuenta de administrador creada");
    }

    private void registrarClienteYMascota(Scanner scanner) {
        System.out.print("\n ----- Registro -----");
        System.out.print("\nNombre del cliente: ");
        String nombreCliente = scanner.nextLine();
        System.out.print("Telefono del cliente: ");
        String telefono = scanner.nextLine();
        Cliente cliente = new Cliente(nombreCliente, telefono);
        clientes.add(cliente);

        System.out.print("Nombre de la mascota: ");
        String nombreMascota = scanner.nextLine();
        System.out.print("Especie de la mascota: ");
        String especie = scanner.nextLine();
        System.out.print("Edad de la mascota: ");
        int edad = scanner.nextInt();
        scanner.nextLine();
        Mascota mascota = new Mascota(nombreMascota, especie, edad);
        mascotas.add(mascota);

        System.out.println("Registro exitoso");
    }

    private void menuAdministrador(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n------ Administrador ------");
            System.out.println("1. Registrar cliente y mascota");
            System.out.println("2. Historial de citas");
            System.out.println("3. Historial medico");  
            System.out.println("4. Gestionar citas");
            System.out.println("5. Administrar personal");
            System.out.println("6. Cerrar sesion");
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
                    Cita.verHistorialCitas();
                    break;
                case 3:
                    System.out.println("--- Historial medico ---");
                    break;
                case 4:
                    menuCitas(scanner);
                    break;
                case 5:
                    menuPersonal(scanner);
                    break;
                case 6:
                    System.out.println("");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 6);
    }

    private void menuPersonal(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n--- Personal ---");
            System.out.println("1. Agregar personal");
            System.out.println("2. Ver personal");
            System.out.println("3. Volver");
            System.out.println("--------------------");
            System.out.print("\nSeleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\n");

            switch (opcion) {
                case 1:
                    Personal.agregarPersonal(scanner);
                    break;
                case 2:
                    Personal.verPersonal();
                    break;
                case 3:
                    System.out.println("");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 3);
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

    private void menuCliente(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n--- Cliente ---");
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

    public static void main(String[] args) {
        Administrador gestionVeterinaria = new Administrador();
        gestionVeterinaria.iniciarPrograma();
    }
}
