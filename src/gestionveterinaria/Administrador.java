package gestionveterinaria;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
//OLA
public class Administrador {
    private ArrayList<Cliente> clientes;
    private ArrayList<Mascota> mascotas;
    private ArrayList<String> adminUsuarios;
    private ArrayList<String> adminContras;
    private Cita cita;
    private Personal personal;
    private List<String> horasDisponibles = new ArrayList<>();
    private static List<String> mascotasRegistradas = new ArrayList<>();
    private Map<String, Cliente> mascotaClienteMap;
    private Scanner scanner;

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
        scanner = new Scanner(System.in);
        this.clientes = new ArrayList<>();
        this.mascotas = new ArrayList<>();
        this.mascotaClienteMap = new HashMap<>();
        this.adminUsuarios = new ArrayList<>();
        this.adminContras = new ArrayList<>();

        this.personal = new Personal("nombre", "puesto", "telefono", horasDisponibles); 

        adminUsuarios.add("admin");
        adminContras.add("123");
        this.scanner = new Scanner(System.in);
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
            Cliente cliente = new Cliente();
            if (cliente.iniciarSesionCliente(usuario, contrasena, scanner)){
                break;
            }
            intentosRestantes--;
            System.out.println("\nUsuario o contrasena incorrectos. \nTiene" + + intentosRestantes + "restantes" );
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

    private void menuAdministrador(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n------ Administrador ------");

            System.out.println("1. Registrar administrador");
            System.out.println("2. Gestionar clientes y mascotas");
            System.out.println("3. Gestionar citas");
            System.out.println("4. Administrar personal");
            System.out.println("5. Cerrar sesion");
            System.out.println("6. Salir del programa");
            
            System.out.println("---------------------------");
            System.out.print("\n--> ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\n");

            switch (opcion) {
                case 1:
                    registrarAdministrador(scanner);                    
                    break;
                case 2:
                    gestionarClienteYMascota(scanner);
                    break;
                case 3:
                    Cita.iniciarMenu(true, "", "");                   
                    break;
                case 4:
                    menuPersonal(scanner);                   
                    break;
                case 5: 
                    System.out.println("Cerrando sesion");
                    return;
                case 6:
                    System.out.println("Saliendo del programa...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (true);
    }

    private void menuPersonal(Scanner scanner) {
        personal.iniciarMenu();
    }
    
    private void gestionarClienteYMascota(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n--- Gestionar cliente y mascota ---\n");
            Cliente.mostrarTablaClientesMascotas();
            System.out.println("-------------------------------------------------------------------------------------------------\n");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Registrar mascota");
            System.out.println("3. Historial medico");
            System.out.println("4. Volver");
            System.out.println("--------------------------------------");
            System.out.print("\n--> ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\n");

            switch (opcion) {
                case 1:
                    Cliente nuevoCliente = new Cliente();
                    nuevoCliente.registroCliente();
                    clientes.add(nuevoCliente);
                    break;
                case 2:
                    Mascota nuevaMascota = new Mascota();
                    nuevaMascota.registroMascota();
                    mascotas.add(nuevaMascota);                    
                    break;
                case 3:
                    HistorialMedico.buscarHistorial(mascotas, scanner);                    
                    break;
                case 4:
                    System.out.println("");
                    return;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 4);
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
            System.out.print("\n--> ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\n");

            switch (opcion) {
                case 1:
                    Cita.agendarCita(true, "", "");
                    break;
                case 2:
                    Cita.editarCita(true, "", "");
                    break;
                case 3:
                    Cita.cancelarCita(true, "", "");
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
        Administrador administrador = new Administrador();
        administrador.iniciarPrograma();
}

}