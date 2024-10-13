package gestionveterinaria;

import java.util.ArrayList;
import java.util.Scanner;

public class Personal {
    private String nombre;
    private String puesto;
    private String correo;
    private String horario;

    private static ArrayList<Personal> personalList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    // Constructor sin parámetros
    public Personal() {
        this.nombre = "";
        this.puesto = "";
        this.correo = "";
        this.horario = "";
    }

    // Constructor con parámetros
    public Personal(String nombre, String puesto, String correo, String horario) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.correo = correo;
        this.horario = horario;
    }

    // Getters y setters para los atributos de Personal
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getPuesto() { return puesto; }
    public void setPuesto(String puesto) { this.puesto = puesto; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    // Sobrescribir toString para mostrar información del personal
    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Puesto: " + puesto + ", Correo: " + correo + ", Horario: " + horario;
    }

    // Método para iniciar el menú de personal
    public void iniciarMenu() {
        int opcion;
        do {
            System.out.println("\n------ Menu Personal ------");
            System.out.println("1. Registrar nuevo personal");
            System.out.println("2. Editar personal");
            System.out.println("3. Ver horarios del personal");
            System.out.println("4. Volver al menu principal");
            System.out.println("---------------------------");
            System.out.print("\nSeleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarPersonal();
                    break;
                case 2:
                    editarPersonal();
                    break;
                case 3:
                    verHorariosPersonal();
                    break;
                case 4:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    System.out.println("\nOpcion invalida.");
            }
        } while (opcion != 4);
    }

    private static void registrarPersonal() {
        System.out.println("\n");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        String puesto = seleccionarPuesto();
        
        System.out.print("\nCorreo: ");
        String correo = scanner.nextLine();

        String horario = seleccionarHorario(puesto);

        personalList.add(new Personal(nombre, puesto, correo, horario));
        System.out.println("\nPersonal registrado exitosamente");
        System.out.println("\n");
    }

    private static String seleccionarPuesto() {
        int opcion;
        while (true) {
            System.out.println("\nSeleccione el puesto: ");
            System.out.println("1. Esteticista");
            System.out.println("2. Medico/a");
            System.out.println("Escoja una opcion:");
            opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) return "Esteticista";
            if (opcion == 2) return "Medico/a";

            System.out.println("Opción no valida. Por favor, seleccione nuevamente.");
        }
    }

    private static String seleccionarHorario(String puesto) {
        System.out.println("\nSeleccione el turno: ");
        System.out.println("1. Manana");
        System.out.println("2. Tarde");
        System.out.println("Escoja una opcion:");
        int turno = scanner.nextInt();
        scanner.nextLine();
        
        if (turno == 1) { 
            if (puesto.equals("Medico/a")) {
                return "8AM, 9:30AM, 11AM"; 
            } else {
                return "8AM, 10AM";
            }
        } else { // Tarde
            if (puesto.equals("Medico/a")) {
                return "1:30PM, 3PM, 4:30PM";
            } else {
                return "1PM, 3PM";
            }
        }
    }

    private static void editarPersonal() {
        verHorariosPersonal();
        System.out.print("\nIngrese el ID del empleado que desea editar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (id > 0 && id <= personalList.size()) {
            Personal empleado = personalList.get(id - 1);
            System.out.println("Editando a: " + empleado + "\n");
            System.out.print("Nuevo nombre (presione Enter para no cambiar): ");
            String nuevoNombre = scanner.nextLine();
            if (!nuevoNombre.isEmpty()) {
                empleado.setNombre(nuevoNombre);
            }
            String nuevoPuesto = seleccionarPuesto();
            empleado.setPuesto(nuevoPuesto);
            String nuevoHorario = seleccionarHorario(nuevoPuesto);
            empleado.setHorario(nuevoHorario);
            System.out.println("\tEmpleado editado exitosamente");
            scanner.nextLine();
        } else {
            System.out.println("\tID no valido.");
        }
    }

    // Método para ver los horarios del personal
    private static void verHorariosPersonal() {
        System.out.println("\n\t\t\t-------- LISTA PERSONAL --------");
        System.out.println("\tID\tNombre\t\tPuesto\t\t\tHorario");
        System.out.println("\t------------------------------------------------------");
        for (int i = 0; i < personalList.size(); i++) {
            Personal empleado = personalList.get(i);
            System.out.println("\t" + (i + 1) + "\t" + empleado.getNombre() + "\t\t" + empleado.getPuesto() + "\t\t" + empleado.getHorario());
        }
    }
}
