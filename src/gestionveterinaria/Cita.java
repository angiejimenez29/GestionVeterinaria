package gestionveterinaria;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cita {
    private String nombreCliente;
    private String apellidoCliente;
    private String nombreMascota;
    private String tipoCita;
    private String turno;
    private String especialista;
    private String hora;

    private static ArrayList<Cita> citasList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public Cita(String nombreCliente, String apellidoCliente, String nombreMascota, String tipoCita, String turno, String especialista, String hora) {
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.nombreMascota = nombreMascota;
        this.tipoCita = tipoCita;
        this.turno = turno;
        this.especialista = especialista;
        this.hora = hora;
    }

    public static void iniciarMenu(boolean isAdmin, String clienteNombre, String clienteApellido) {
        int opcion;
        do {
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
       
            String asciiArt = """
__  _ __        _  o _|_ _  _
|||(/_| ||_|   (_  |  |_(_|_>
""";

System.out.println(asciiArt);
            System.out.println("1. Registrar cita");
            System.out.println("2. Editar cita");
            System.out.println("3. Eliminar cita");
            System.out.println("4. Ver citas");
            System.out.println("5. Volver");
            System.out.println("---------------------------");
            System.out.print("\n-> ");
            
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agendarCita(isAdmin, clienteNombre, clienteApellido);
                    break;
                case 2:
                    editarCita(isAdmin, clienteNombre, clienteApellido);
                    break;
                case 3:
                    cancelarCita(isAdmin, clienteNombre, clienteApellido);
                    break;
                case 4:
                    verHistorialCitas(isAdmin, clienteNombre, clienteApellido);
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opcion inválida.");
            }
        } while (opcion != 5);
    }

    public static void agendarCita(boolean isAdmin, String clienteNombre, String clienteApellido) {
    String nombreCliente = clienteNombre;
    String apellidoCliente = clienteApellido;
    String nombreMascota = "";
    if (Cliente.getClientes().isEmpty()) {
        System.out.println("No hay ningún cliente registrado.");
        return; 
    }

    if (isAdmin) {

        System.out.print("\n");
        System.out.println("------ Clientes ------");
        List<Cliente> clientes = Cliente.getClientes();
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i).getNombre() + " " + clientes.get(i).getApellido());
        }
        System.out.print("-> ");
        int seleccionCliente = scanner.nextInt();
        scanner.nextLine();
        Cliente clienteSeleccionado = clientes.get(seleccionCliente - 1);
        nombreCliente = clienteSeleccionado.getNombre();
        apellidoCliente = clienteSeleccionado.getApellido();

        System.out.print("\n");
        System.out.print("\n");
        System.out.println("---- Mascotas ----");
        List<Mascota> mascotas = clienteSeleccionado.getMascotas();
        for (int i = 0; i < mascotas.size(); i++) {
            System.out.println((i + 1) + ". " + mascotas.get(i).getNombreMascota());
        System.out.print("-> ");
        }
        
        int seleccionMascota = scanner.nextInt();
        scanner.nextLine();
        nombreMascota = mascotas.get(seleccionMascota - 1).getNombreMascota();
    } else {
        List<Mascota> mascotas = new ArrayList<>();
        for (Cliente cliente : Cliente.getClientes()) {
            if (cliente.getNombre().equals(nombreCliente) && cliente.getApellido().equals(apellidoCliente)) {
                mascotas = cliente.getMascotas();
                break;
            }
        }

        if (mascotas.isEmpty()) {
            System.out.println("No tienes mascotas registradas.");
            return;
        }

        System.out.println("\nMascotas registradas: ");
        for (int i = 0; i < mascotas.size(); i++) {
            System.out.println((i + 1) + ". " + mascotas.get(i).getNombreMascota());
        }

        System.out.print("\n--> ");
        int seleccionMascota = scanner.nextInt();
        scanner.nextLine();
        nombreMascota = mascotas.get(seleccionMascota - 1).getNombreMascota();
    }

    System.out.print("\n");
    System.out.println("Tipo de cita:");
    System.out.println("1. Estetica");
    System.out.println("2. Consulta Medica");
    System.out.print("\n-> ");
    int seleccionTipoCita = scanner.nextInt();
    scanner.nextLine();
    
    String tipoCita;
    switch (seleccionTipoCita) {
        case 1:
            tipoCita = "Estetica";
            break;
        case 2:
            tipoCita = "Consulta Medica";
            break;
        default:
            System.out.println("Opcion invalida.");
            return;
    }

    String nombreEspecialista = seleccionarEspecialista(tipoCita);
    if (nombreEspecialista.isEmpty()) {
        System.out.println("No se pudo agendar la cita. No hay especialistas disponibles.");
        return;
    }

    List<String> horasDisponibles = Personal.obtenerHorasDisponibles(nombreEspecialista);
    if (horasDisponibles.isEmpty()) {
        System.out.println("No hay horas disponibles para el especialista seleccionado.");
        return;
    }

    System.out.println("\nHorarios disponibles para " + nombreEspecialista + ":");
    for (int i = 0; i < horasDisponibles.size(); i++) {
        System.out.println((i + 1) + ". " + horasDisponibles.get(i));
    }

    System.out.print("\n-> ");
    int seleccionHora = scanner.nextInt();
    scanner.nextLine();
    
    if (seleccionHora < 1 || seleccionHora > horasDisponibles.size()) {
        System.out.println("Seleccion no valida. Intente de nuevo.");
        return;
    }

    String hora = horasDisponibles.get(seleccionHora - 1);
    for (Personal p : Personal.getPersonalList()) {
        if (p.getNombre().equals(nombreEspecialista)) {
            p.eliminarHoraDisponible(hora);
            break;
        }
    }

    Cita nuevaCita = new Cita(nombreCliente, apellidoCliente, nombreMascota, tipoCita, "", nombreEspecialista, hora);
    citasList.add(nuevaCita);
    System.out.println("Cita registrada exitosamente.");
}

    public static void editarCita(boolean isAdmin, String clienteNombre, String clienteApellido) {

    List<Cita> citasFiltradas = new ArrayList<>();
    for (Cita cita : citasList) {
        if (isAdmin || (cita.nombreCliente.equals(clienteNombre) && cita.apellidoCliente.equals(clienteApellido))) {
            citasFiltradas.add(cita);
        }
    }

    verHistorialCitas(isAdmin, clienteNombre, clienteApellido);

    System.out.print("-> ");
    int citaIndex = scanner.nextInt() - 1;
    scanner.nextLine();

    if (citaIndex < 0 || citaIndex >= citasFiltradas.size()) {
        System.out.println("Cita no valida.");
        return;
    }

    Cita cita = citasFiltradas.get(citaIndex);
    String horaAnterior = cita.hora;

    if (!isAdmin) {
        List<Mascota> mascotas = new ArrayList<>();
        for (Cliente cliente : Cliente.getClientes()) {
            if (cliente.getNombre().equals(clienteNombre) && cliente.getApellido().equals(clienteApellido)) {
                mascotas = cliente.getMascotas();
                break;
            }
        }

        System.out.println("Mascotas de " + clienteNombre + ":");
        for (int i = 0; i < mascotas.size(); i++) {
            System.out.println((i + 1) + ". " + mascotas.get(i).getNombreMascota());
        }

        System.out.print("-> ");
        String nuevoMascotaInput = scanner.nextLine();
        if (!nuevoMascotaInput.isEmpty()) {
            int seleccionMascota = Integer.parseInt(nuevoMascotaInput);
            if (seleccionMascota < 1 || seleccionMascota > mascotas.size()) {
                System.out.println("Seleccion no valida.");
                return;
            }
            cita.nombreMascota = mascotas.get(seleccionMascota - 1).getNombreMascota();
            System.out.println("Mascota modificada exitosamente.");
        }
    } else {

        System.out.println("\nLista de Clientes:");
        List<Cliente> clientes = Cliente.getClientes();
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i).getNombre() + " " + clientes.get(i).getApellido());
        }

        System.out.print("-> ");
        System.out.print("\n");
        String nuevoClienteInput = scanner.nextLine();

        if (!nuevoClienteInput.isEmpty()) {
            int seleccionCliente = Integer.parseInt(nuevoClienteInput);
            if (seleccionCliente < 1 || seleccionCliente > clientes.size()) {
                System.out.println("Seleccion no valida.");
                return;
            }

            Cliente clienteSeleccionado = clientes.get(seleccionCliente - 1);
            cita.nombreCliente = clienteSeleccionado.getNombre();
            cita.apellidoCliente = clienteSeleccionado.getApellido();
            System.out.println("Cliente modificado exitosamente.");
            System.out.print("/n");

            List<Mascota> mascotas = clienteSeleccionado.getMascotas();
            System.out.println("Mascotas de " + clienteSeleccionado.getNombre() + ":");
            for (int i = 0; i < mascotas.size(); i++) {
                System.out.println((i + 1) + ". " + mascotas.get(i).getNombreMascota());
            }

            System.out.print("-> ");
            String nuevoMascotaInput = scanner.nextLine();
            if (!nuevoMascotaInput.isEmpty()) {
                int seleccionMascota = Integer.parseInt(nuevoMascotaInput);
                if (seleccionMascota < 1 || seleccionMascota > mascotas.size()) {
                    System.out.println("Seleccion no valida.");
                    return;
                }
                cita.nombreMascota = mascotas.get(seleccionMascota - 1).getNombreMascota();
                System.out.println("Mascota modificada exitosamente.");
            }
        }
    }

    System.out.println("\nNuevo tipo de cita");
    System.out.println("1. Estetica");
    System.out.println("2. Consulta Medica");
    String nuevoTipoCita = scanner.nextLine();
    System.out.print("\n-> ");
    if (!nuevoTipoCita.isEmpty()) {
        switch (nuevoTipoCita) {
            case "1":
                cita.tipoCita = "Estetica";
                break;
            case "2":
                cita.tipoCita = "Consulta Medica";
                break;
            default:
                System.out.println("Opcion no valida. Manteniendo tipo de cita anterior.");
                break;
        }
    }

    if (cita.especialista == null || cita.especialista.isEmpty()) {
        String nombreEspecialista = seleccionarEspecialista(cita.tipoCita);
        if (!nombreEspecialista.isEmpty()) {
            cita.especialista = nombreEspecialista;
        }
    } else {
        System.out.print("Desea mantener al especialista actual " + cita.especialista + "? (Si/No): ");
        String mantenerEspecialista = scanner.nextLine();
        
        if (mantenerEspecialista.equalsIgnoreCase("no")) {
            String nombreEspecialista = seleccionarEspecialista(cita.tipoCita);
            if (!nombreEspecialista.isEmpty()) {
                cita.especialista = nombreEspecialista;
            }
        }
    }

    if (cita.hora == null || cita.hora.isEmpty()) {
        List<String> horasDisponibles = Personal.obtenerHorasDisponibles(cita.especialista);
        if (!horasDisponibles.isEmpty()) {
            System.out.println("\nHorarios disponibles para " + cita.especialista + ":");
            for (int i = 0; i < horasDisponibles.size(); i++) {
                System.out.println((i + 1) + ". " + horasDisponibles.get(i));
            }

            System.out.print("\n-> ");
            String nuevoHoraInput = scanner.nextLine();
            if (!nuevoHoraInput.isEmpty()) {
                int seleccionHora = Integer.parseInt(nuevoHoraInput);
                if (seleccionHora < 1 || seleccionHora > horasDisponibles.size()) {
                    System.out.println("Selección no válida.");
                    return;
                }

                String nuevaHora = horasDisponibles.get(seleccionHora - 1);
                for (Personal p : Personal.getPersonalList()) {
                    if (p.getNombre().equals(cita.especialista)) {
                        p.agregarHoraDisponible(horaAnterior); // Liberar la hora anterior
                        break;
                    }
                }
                cita.hora = nuevaHora;
                for (Personal p : Personal.getPersonalList()) {
                    if (p.getNombre().equals(cita.especialista)) {
                        p.eliminarHoraDisponible(nuevaHora); // Eliminar la nueva hora seleccionada del especialista
                        break;
                    }
                }
            }
        }
    } else {
        System.out.print("Desea mantener la hora actual " + cita.hora + "? (Si/No): ");
        String mantenerHora = scanner.nextLine();
        if (mantenerHora.equalsIgnoreCase("no")) {
            List<String> horasDisponibles = Personal.obtenerHorasDisponibles(cita.especialista);
            if (!horasDisponibles.isEmpty()) {
                System.out.println("\nHorarios disponibles para " + cita.especialista + ":");
                for (int i = 0; i < horasDisponibles.size(); i++) {
                    System.out.println((i + 1) + ". " + horasDisponibles.get(i));
                }

                System.out.print("\n-> ");
                int seleccionHora = scanner.nextInt();
                scanner.nextLine();

                if (seleccionHora < 1 || seleccionHora > horasDisponibles.size()) {
                    System.out.println("Selección no válida.");
                    return;
                }

                String nuevaHora = horasDisponibles.get(seleccionHora - 1);
                cita.hora = nuevaHora;

                for (Personal p : Personal.getPersonalList()) {
                    if (p.getNombre().equals(cita.especialista)) {
                        p.agregarHoraDisponible(horaAnterior);
                        p.eliminarHoraDisponible(nuevaHora); 
                        break;
                    }
                }
            }
        }
    }

    System.out.println("Cita editada exitosamente.");
    System.out.print("\n");
    System.out.print("\n");
}

    public static void cancelarCita(boolean isAdmin, String clienteNombre, String clienteApellido) {
    List<Cita> citasFiltradas = new ArrayList<>();
    for (Cita cita : citasList) {
        if (isAdmin || (cita.nombreCliente.equals(clienteNombre) && cita.apellidoCliente.equals(clienteApellido))) {
            citasFiltradas.add(cita);
        }
    }

    if (citasFiltradas.isEmpty()) {
        System.out.println("No hay citas disponibles para cancelar.");
        return;
    }

    verHistorialCitas(isAdmin, clienteNombre, clienteApellido);

    System.out.print("\n--> ");
    int citaIndex = scanner.nextInt() - 1;
    scanner.nextLine();

    if (citaIndex < 0 || citaIndex >= citasFiltradas.size()) {
        System.out.println("Cita no valida.");
        return;
    }

    Cita cita = citasFiltradas.get(citaIndex);

    System.out.print("Cancelar la cita de " + cita.nombreMascota + " (Sí/No) ");
    String confirmacion = scanner.nextLine();

    if (confirmacion.equalsIgnoreCase("sí")) {
        for (Personal p : Personal.getPersonalList()) {
            if (p.getNombre().equals(cita.especialista)) {
                p.agregarHoraDisponible(cita.hora); 
                break;
            }
        }

        citasList.remove(cita);
        System.out.println("Cita cancelada exitosamente.");
    } else {
        System.out.println("Cancelacion de cita abortada.");
    }
}

    public static void verHistorialCitas(boolean isAdmin, String clienteNombre, String clienteApellido) {
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        String asciiArt = """
     __           
    /   o _|_ _  _
    \\__ |  |_(_|_>
""";

System.out.println(asciiArt);
System.out.printf("%-5s %-20s %-20s %-20s %-20s %-10s\n", 
                          "ID", "Cliente", "Mascota", "Tipo de Cita", "Especialista", "Hora");
        System.out.println("--------------------------------------------------------------------------------------");
        for (int i = 0; i < citasList.size(); i++) {
    Cita cita = citasList.get(i);  
    if (isAdmin || (cita.nombreCliente.equals(clienteNombre) && cita.apellidoCliente.equals(clienteApellido))) {
        System.out.printf("%-5d %-20s %-20s %-20s %-20s %-10s\n", 
                          (i + 1), 
                          cita.nombreCliente + " " + cita.apellidoCliente, 
                          cita.nombreMascota, 
                          cita.tipoCita, 
                          cita.especialista, 
                          cita.hora);
        System.out.print("\n");
    }
}
    }

    private static String seleccionarEspecialista(String tipoCita) {
        ArrayList<Personal> especialistasDisponibles = new ArrayList<>();

        for (Personal p : Personal.getPersonalList()) {
            if ((tipoCita.equalsIgnoreCase("Estetica") && p.getPuesto().equals("Esteticista")) ||
                (tipoCita.equalsIgnoreCase("Consulta Medica") && p.getPuesto().equals("Veterinario"))) {
                especialistasDisponibles.add(p);
            }

        }

        if (especialistasDisponibles.isEmpty()) {
             System.out.print("\n");
            System.out.println("No hay especialistas disponibles para este tipo de cita.");
            return "";
        }

        System.out.println("\nEspecialistas disponibles para " + tipoCita + ":");
        for (int i = 0; i < especialistasDisponibles.size(); i++) {
            Personal especialista = especialistasDisponibles.get(i);
            System.out.println((i + 1) + ". " + especialista.getNombre() + " - Horarios disponibles: " + especialista.getHorasDisponibles());
        }

        System.out.print("\n-> ");
        int seleccion = scanner.nextInt();
        scanner.nextLine();

        if (seleccion > 0 && seleccion <= especialistasDisponibles.size()) {
            return especialistasDisponibles.get(seleccion - 1).getNombre();
        } else {
            System.out.println("Selección no valida. Por favor, intente de nuevo.");
            return seleccionarEspecialista(tipoCita);
        }
    }

    private static String solicitarDato(String mensaje) {
        System.out.print(mensaje + ": ");
        return scanner.nextLine();
    }
    
    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public String getTipoCita() {
        return tipoCita;
    }

    public String getEspecialista() {
        return especialista;
    }

    public String getHora() {
        return hora;
    }

    public static ArrayList<Cita> getCitasList() {
        return citasList; 
    }
}
