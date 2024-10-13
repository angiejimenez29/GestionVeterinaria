package gestionveterinaria;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Administrador {
    private ArrayList<Cliente> clientes;
    private ArrayList<Mascota> mascotas;
    private ArrayList<String> adminUsuarios;
    private ArrayList<String> adminContras;
    private ArrayList<String> clienteUsuarios;
    private ArrayList<String> clienteContras;
    private Cita cita;
    private Personal personal;
    

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
        this.adminUsuarios = new ArrayList<>();
        this.adminContras = new ArrayList<>();
        this.clienteUsuarios = new ArrayList<>();
        this.clienteContras = new ArrayList<>();
        
        adminUsuarios.add("admin");
        adminContras.add("123");
        
        clienteUsuarios.add("cliente");
        clienteContras.add("456");
    }

    public void iniciarPrograma() {
        Scanner scanner = new Scanner(System.in);
        int intentosRestantes;
        boolean continuar = true;

        
        while(continuar){
            intentosRestantes = 3;
            
            while(intentosRestantes > 0){ 
                System.out.println(asciiArt);
                System.out.print("\n\n");
                System.out.println("\t----- Iniciar Sesion -----");
                System.out.print("\n");

                System.out.print("\tUsuario: ");
                String usuario = scanner.nextLine();
                System.out.print("\tContrasena: ");
                String contrasena = scanner.nextLine();
                System.out.println("\t---------------------------");

                if (adminUsuarios.contains(usuario)) {
                    int index = adminUsuarios.indexOf(usuario);
                    if (adminContras.get(index).equals(contrasena)) {
                        System.out.println("\nInicio de sesion como administrador exitoso.");
                        menuAdministrador(scanner);
                        break;
                    }
                }
                if (clienteUsuarios.contains(usuario)) {
                    int index = clienteUsuarios.indexOf(usuario);
                    if (clienteContras.get(index).equals(contrasena)) {
                        System.out.println("\nInicio de sesion como cliente exitoso.");
                        menuCliente(scanner);
                        break;
                    }
                }
                intentosRestantes--;
                System.out.println("\nUsuario o contrasena incorrectos. \nIntentos restantes: " + intentosRestantes);
            }
            if(intentosRestantes == 0){
                System.out.println("\nHa superado el numero maximo de intentos, intentelo mas tarde");
                continuar = false;
            }
        }
        scanner.close();
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

    private void registrarClienteYMascota(Scanner scanner) {
        System.out.println("-------- Registro --------\n");
        
        //CLIENTE
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
        
        clienteUsuarios.add(usuario);
        clienteContras.add(telefono);
        
        //MASCOTA
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
            if(opcionSexo == 1){
                sexo = "Macho";
                break;
            } else if (opcionSexo == 2) {
                sexo = "Hembra";
                break;
            } else {
                System.out.println("Opcion no valida");
            }
        }   
        String castracion = "";
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
        
        //FECHA ACTUAL
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yy");
        String fechaRegistro = fechaActual.format(formatter);

        Mascota mascota = new Mascota(nombreMascota, especie, raza, edad, sexo, 
                color, peso, fechaNacimiento, fechaRegistro, new ArrayList<String>(),
                new ArrayList<String>(), new ArrayList<String>(), observaciones, esCastrada, esGestante);
        mascota.gestionarVacunas(scanner);
        mascota.gestionarAlergias(scanner);
        mascota.gestionarEnfermedades(scanner);
        
        mascotas.add(mascota);
        
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
                    HistorialMedico.buscarHistorial(mascotas, scanner); 
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
            System.out.println("4. Cerrar sesion");
            System.out.println("5. Salir del programa");
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
                    System.out.println("Cerrando sesion...");
                    return;
                case 5: 
                    System.out.println("Saliendo del programa...");
                    System.exit(0);
                default:
                    System.out.println("Opcion no valida");
            }
        } while (true);
    }
    
    
    public static void main(String[] args) {
        Administrador gestionVeterinaria = new Administrador();
        gestionVeterinaria.iniciarPrograma();
    }
}
