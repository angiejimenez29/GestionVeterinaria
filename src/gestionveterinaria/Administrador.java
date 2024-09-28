package gestionveterinaria;

 //Author: gida.j
import java.util.ArrayList;
import java.util.Scanner;

public class Administrador {
    private ArrayList<Cliente> clientes;
    private ArrayList<Mascota> mascotas;
    private ArrayList<String> adminUsuarios;
    private ArrayList<String> adminContras;

    public Administrador() {
        this.clientes = new ArrayList<>();
        this.mascotas = new ArrayList<>();
        this.adminUsuarios = new ArrayList<>();
        this.adminContras = new ArrayList<>();
    }

public void iniciarSesion(Scanner scanner) {
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
    }
    public void crearCuentaAdministrador(Scanner scanner) {
        System.out.print("\n --- Crear cuenta ---");
        System.out.print("\nUsuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Contrasena: ");
        String contra = scanner.nextLine();
        adminUsuarios.add(usuario);
        adminContras.add(contra);
        System.out.println("Cuenta de administrador creada");
    }

    public void registrarClienteYMascota(Scanner scanner) {
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
}
