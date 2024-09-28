package gestionveterinaria;

import java.util.Scanner;

public class ControladorPrincipal {
    private GestorCitas gestorCitas;
    private GestorPersonal gestorPersonal;
    private Administrador administrador;
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
   |___|  |_______|  |___|  |_______|
        """;

    public ControladorPrincipal() {
        this.gestorCitas = new GestorCitas();
        this.gestorPersonal = new GestorPersonal();
        this.administrador = new Administrador();
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
        administrador.iniciarSesion(scanner);
        menuAdministrador(scanner);
    }
    //funciona 1, 2 y 4
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
                    administrador.registrarClienteYMascota(scanner);
                    break;
                case 2:
                    gestorCitas.verHistorialCitas();
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
                    gestorPersonal.agregarPersonal(scanner);
                    break;
                case 2:
                    gestorPersonal.verPersonal();
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
                    gestorCitas.agendarCita(scanner);
                    break;
                case 2:
                    gestorCitas.modificarCita(scanner);
                    break;
                case 3:
                    gestorCitas.cancelarCita(scanner);
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
            //esto se debe modificar para q unicamente deba acceder a su informacion como cliente
            switch (opcion) {
                case 1:
                    gestorCitas.agendarCita(scanner);
                    break;
                case 2:
                    gestorCitas.modificarCita(scanner);
                    break;
                case 3:
                    gestorCitas.cancelarCita(scanner);
                    break;
                case 4:
                    System.out.println("");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 4);
    }
}
