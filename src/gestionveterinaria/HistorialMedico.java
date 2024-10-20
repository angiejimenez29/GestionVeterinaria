package gestionveterinaria;

import java.util.Scanner;
import java.util.ArrayList;

public class HistorialMedico {
    private Scanner scanner;
    private Mascota mascota;
    private Cliente cliente;

    public HistorialMedico(Mascota mascota, Cliente cliente) {
        this.mascota = mascota;
        this.cliente = cliente;
        this.scanner = scanner;
    }

    public void mostrarHistorial() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--Historial de " + mascota.getNombreMascota()+"--");
        System.out.println("Nombre del cliente: " + cliente.getNombre());
        System.out.println("Nombre de la mascota: " + mascota.getNombreMascota());
        System.out.println("Especie: " + mascota.getEspecie());
        System.out.println("Raza: " + mascota.getRaza());
        System.out.println("Edad: " + mascota.getEdad());
        System.out.println("Sexo: " + mascota.getSexo());
        System.out.println("Castrado: " + (mascota.isCastrada() ? "Si" : "No"));
        if (!mascota.isCastrada() && mascota.getSexo().equals("Hembra")) {
            System.out.println("Gestante: " + (mascota.isGestante() ? "Si" : "No"));
        }
        System.out.println("Color: " + mascota.getColor());
        System.out.println("Peso: " + mascota.getPeso() + " kg");
        System.out.println("Fecha de nacimiento: " + mascota.getFechaNacimiento());
        System.out.println("Fecha de registro: " + mascota.getFechaRegistro());
        
        System.out.println("\nVacunas:");
        if (mascota.getVacunas().isEmpty()) {
            System.out.println("");
        } else {
            for (String vacuna : mascota.getVacunas()) {
                System.out.println("- " + vacuna);
            }
        }

        System.out.println("\nAlergias:");
        if (mascota.getAlergias().isEmpty()) {
            System.out.println("");
        } else {
            for (String alergia : mascota.getAlergias()) {
                System.out.println("- " + alergia);
            }
        }

        System.out.println("\nEnfermedades:");
        if (mascota.getEnfermedades().isEmpty()) {
            System.out.println("");
        } else {
            for (String enfermedad : mascota.getEnfermedades()) {
                System.out.println("- " + enfermedad);
            }
        }

        System.out.println("\nObservaciones: " + mascota.getObservaciones());   
    }
    
    public void mostrarHistorialCliente() {
        mostrarHistorial();
    }
    
    public void mostrarHistorialAdmin() {
            mostrarHistorial();
            System.out.print("\nEditar historial | (1) Si | (2) No  | -> ");
            Scanner scanner = new Scanner(System.in);
            int opcion = scanner.nextInt();

            if (opcion == 1) {
                editarHistorial();
            }
        }
    
    public static void buscarHistorialCliente(ArrayList<Mascota> mascotas, Cliente cliente, Scanner scanner) {
        System.out.println("\n----- Historial Medico -----");
        ArrayList<Mascota> mascotasCliente = cliente.getMascotas();
        if (mascotasCliente.isEmpty()) {
            System.out.println("El cliente no tiene mascotas registradas.");
            return;
        }
        System.out.println("Mascotas registradas de " + cliente.getNombre() + " " + cliente.getApellido() + ":");
        for (int i = 0; i < mascotasCliente.size(); i++) {
            Mascota mascota = mascotasCliente.get(i);
            System.out.println((i + 1) + ". " + mascota.getNombreMascota());
        }
        System.out.print("\nSeleccione el número de la mascota: ");
        int opcionMascota = scanner.nextInt();
        scanner.nextLine(); 
        if (opcionMascota < 1 || opcionMascota > mascotasCliente.size()) {
            System.out.println("Opción inválida. Intente nuevamente.");
            return;
        }
        Mascota mascotaSeleccionada = mascotasCliente.get(opcionMascota - 1);
        HistorialMedico historial = new HistorialMedico(mascotaSeleccionada, cliente);
        historial.mostrarHistorialCliente();
    }
    
    public static void buscarHistorial(ArrayList<Mascota> mascotas, Scanner scanner) {
        System.out.println("\n----- Historial Medico -----\n");
        ArrayList<Cliente> clientes = Cliente.getClientes();
        if (clientes.isEmpty()) {
            System.out.println("\nNo hay clientes registrados.");
            return;
        }
        System.out.println("\n--- Lista de Clientes ---");
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            System.out.println((i + 1) + ". " + cliente.getNombre() + " " + cliente.getApellido());
        }
        System.out.print("\nSeleccione el cliente: ");
        int opcionCliente = scanner.nextInt();
        scanner.nextLine();
        if (opcionCliente < 1 || opcionCliente > clientes.size()) {
            System.out.println("Intente nuevamente.");
            return;
        }
        Cliente clienteSeleccionado = clientes.get(opcionCliente - 1);
        System.out.println( "\n>>> " + clienteSeleccionado.getNombre() + " " + clienteSeleccionado.getApellido() + ":");
        
        ArrayList<Mascota> mascotasCliente = clienteSeleccionado.getMascotas();
        if (mascotasCliente.isEmpty()) {
            System.out.println("\nNo tiene mascotas registradas.");
            return;
        }
        System.out.println("\n--- Mascotas ---\n");
        for (int i = 0; i < mascotasCliente.size(); i++) {
            Mascota mascota = mascotasCliente.get(i);
            System.out.println((i + 1) + ". " + mascota.getNombreMascota());
        }
        System.out.print("\nSeleccione la mascota: ");
        int opcionMascota = scanner.nextInt();
        scanner.nextLine();
        if (opcionMascota < 1 || opcionMascota > mascotasCliente.size()) {
            System.out.println("Intente nuevamente.");
            return;
        }
        Mascota mascotaEncontrada = mascotasCliente.get(opcionMascota - 1);
        if (mascotaEncontrada != null) {
            HistorialMedico historial = new HistorialMedico(mascotaEncontrada, clienteSeleccionado);
            historial.mostrarHistorialAdmin();
        } else {
            System.out.println("Mascota no encontrada.");
        }
    }

    public void editarHistorial() {
    Scanner scanner = new Scanner(System.in);
    boolean editing = true;

    while (editing) {
        System.out.println("\n--- Editar Historial Medico ---");
        System.out.println("Seleccione el campo que desea editar:");
        System.out.println("1. Nombre de la mascota");
        System.out.println("2. Especie");
        System.out.println("3. Raza");
        System.out.println("4. Edad");
        System.out.println("5. Sexo");
        System.out.println("6. Castrado");
        System.out.println("7. Color");
        System.out.println("8. Peso");
        System.out.println("9. Observaciones");
        System.out.println("10. Vacunas");
        System.out.println("11. Alergias");
        System.out.println("12. Enfermedades");
        System.out.print("-> ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        System.out.print("\n");
        switch (opcion) {
            case 1:
                System.out.print("Nombre de la mascota (actual: " + mascota.getNombreMascota() + "): ");
                String nuevoNombre = scanner.nextLine();
                if (!nuevoNombre.isEmpty()) {
                    mascota.setNombreMascota(nuevoNombre);
                }
                break;

            case 2:
                System.out.print("Especie (actual: " + mascota.getEspecie() + "): ");
                String nuevaEspecie = scanner.nextLine();
                if (!nuevaEspecie.isEmpty()) {
                    mascota.setEspecie(nuevaEspecie);
                }
                break;

            case 3:
                System.out.print("Raza (actual: " + mascota.getRaza() + "): ");
                String nuevaRaza = scanner.nextLine();
                if (!nuevaRaza.isEmpty()) {
                    mascota.setRaza(nuevaRaza);
                }
                break;

            case 4:
                System.out.print("Edad (actual: " + mascota.getEdad() + "): ");
                String nuevaEdad = scanner.nextLine();
                if (!nuevaEdad.isEmpty()) {
                    mascota.setEdad(Integer.parseInt(nuevaEdad));
                }
                break;

            case 5:
                System.out.print("Sexo (actual: " + mascota.getSexo() + ") | (1) Macho | (2) Hembra: ");
                int opcionSexo = scanner.nextInt();
                scanner.nextLine();
                if (opcionSexo == 1) {
                    mascota.setSexo("Macho");
                } else if (opcionSexo == 2) {
                    mascota.setSexo("Hembra");
                }
                break;

            case 6:
                System.out.print("Castrado: | (1) Si | (2) No | -> ");
                int opcionCastracion = scanner.nextInt();
                scanner.nextLine(); 
                if (opcionCastracion == 1) {
                    mascota.setCastrada(true);
                } else if (opcionCastracion == 2) {
                    mascota.setCastrada(false);
                    if (mascota.getSexo().equals("Hembra")) {
                        System.out.print("Gestante: | (1) Si | (2) No | -> ");
                        int opcionGestante = scanner.nextInt();
                        scanner.nextLine();
                        mascota.setGestante(opcionGestante == 1);
                    }
                }
                break;

            case 7:
                System.out.print("Color (actual: " + mascota.getColor() + "): ");
                String nuevoColor = scanner.nextLine();
                if (!nuevoColor.isEmpty()) {
                    mascota.setColor(nuevoColor);
                }
                break;

            case 8:
                System.out.print("Peso (actual: " + mascota.getPeso() + " kg): ");
                String nuevoPeso = scanner.nextLine();
                if (!nuevoPeso.isEmpty()) {
                    mascota.setPeso(Double.parseDouble(nuevoPeso));
                }
                break;

            case 9:
                System.out.print("Observaciones (actual: " + mascota.getObservaciones() + "): ");
                String nuevasObservaciones = scanner.nextLine();
                if (!nuevasObservaciones.isEmpty()) {
                    mascota.setObservaciones(nuevasObservaciones);
                }
                break;

            case 10:
                editarVacunas(scanner);
                break;

            case 11:
                editarAlergias(scanner);
                break;

            case 12:
                editarEnfermedades(scanner);
                break;

            case 0:
                editing = false;
                break;

            default:
                System.out.println("Opcion no valida");
                break;
        }
    }
    System.out.println("Historial medico actualizado.");
    mostrarHistorial();
}

    public void editarVacunas(Scanner scanner) {
        while (true) {
            System.out.println("\n----- Vacunas -----");
            mostrarVacunas();

            System.out.println("\n1. Agregar vacuna");
            System.out.println("2. Editar vacuna existente");
            System.out.println("3. Eliminar vacuna");
            System.out.print("-> ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarVacuna(scanner); 
                    break;
                case 2:
                    System.out.println("\n----- Editar Vacunas -----");
                    System.out.print("\nVacuna a editar: ");
                    int indiceEditar = scanner.nextInt() - 1;
                    scanner.nextLine();
                    
                    if (indiceEditar >= 0 && indiceEditar < mascota.getVacunas().size()) {
                        String[] vacunasDisponibles = mascota.vacunasEspecie();
                        for (int i = 0; i < vacunasDisponibles.length; i++) {
                        System.out.println((i + 1) + ". " + vacunasDisponibles[i]);
                    }
                        System.out.print("\nSeleccione la nueva vacuna: ");
                        int seleccion = scanner.nextInt() - 1;
                        scanner.nextLine();

                        if (seleccion >= 0 && seleccion < vacunasDisponibles.length) {
                            System.out.print("Fecha de la vacuna: ");
                            String nuevaFecha = scanner.nextLine().trim();
                            mascota.getVacunas().set(indiceEditar, vacunasDisponibles[seleccion] + " - " + nuevaFecha);
                        } else {
                            System.out.println("Opcion no valida");
                        }
                    } else {
                        System.out.println("Indice invalido.");
                    }
                    break;
                case 3:
                    System.out.println("\n----- Eliminar Vacunas -----");
                    System.out.print("\nVacuna a eliminar: ");
                    int indiceEliminar = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (indiceEliminar >= 0 && indiceEliminar < mascota.getVacunas().size()) {
                        mascota.getVacunas().remove(indiceEliminar);
                    } else {
                        System.out.println("Indice invalido.");
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opcion no valida");
            }
        }
    }

    // Función para agregar vacunas
    public void agregarVacuna(Scanner scanner) {
        System.out.println("\n----- Agregar Vacunas -----\n");
        String[] vacunasDisponibles = mascota.vacunasEspecie();
        for (int i = 0; i < vacunasDisponibles.length; i++) {
            System.out.println((i + 1) + ". " + vacunasDisponibles[i]);
        }
        System.out.println((vacunasDisponibles.length + 1) + ". Otros");

        System.out.print("\nSeleccione la vacuna a agregar: ");
        int seleccion = scanner.nextInt() - 1;
        scanner.nextLine();

        if (seleccion >= 0 && seleccion < vacunasDisponibles.length) {
            System.out.print("Fecha de la vacuna: ");
            String fechaVacuna = scanner.nextLine().trim();
            mascota.getVacunas().add(vacunasDisponibles[seleccion] + " - " + fechaVacuna);
        } else {
            System.out.println("Opcion no valida");
        }
    }

    public void editarAlergias(Scanner scanner) {
        while (true) {
            System.out.println("\n----- Alergias -----");
            mostrarAlergias(); 
            System.out.println("\n1. Agregar alergia");
            System.out.println("2. Editar alergia existente");
            System.out.println("3. Eliminar alergia");
            System.out.print("-> ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarAlergia(scanner);
                    break;
                case 2:
                    System.out.println("\n----- Editar Alergias -----");
                    System.out.print("\nAlergia a editar: ");
                    int indiceEditar = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (indiceEditar >= 0 && indiceEditar < mascota.getAlergias().size()) {
                        System.out.print("Ingrese la nueva alergia: ");
                        String nuevaAlergia = scanner.nextLine();
                        mascota.getAlergias().set(indiceEditar, nuevaAlergia);
                    } else {
                        System.out.println("Indice invalido.");
                    }
                    break;
                case 3:
                    System.out.println("\n----- Eliminar Alergias -----");
                    System.out.print("'\nAlergia a eliminar: ");
                    int indiceEliminar = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (indiceEliminar >= 0 && indiceEliminar < mascota.getAlergias().size()) {
                        mascota.getAlergias().remove(indiceEliminar);
                    } else {
                        System.out.println("Indice invalido.");
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opcion no valida");
            }
        }
    }

    // Función para agregar alergias
    public void agregarAlergia(Scanner scanner) {
        System.out.println("\n----- Agregar Alergias -----");
        String[] alergiasDisponibles = mascota.alergiasEspecie();
        for (int i = 0; i < alergiasDisponibles.length; i++) {
            System.out.println((i + 1) + ". " + alergiasDisponibles[i]);
        }
        System.out.println((alergiasDisponibles.length + 1) + ". Otros");

        System.out.print("\nSeleccione la alergia a agregar: ");
        int seleccion = scanner.nextInt() - 1;
        scanner.nextLine();

        if (seleccion >= 0 && seleccion < alergiasDisponibles.length) {
            mascota.getAlergias().add(alergiasDisponibles[seleccion]);
        } else if (seleccion == alergiasDisponibles.length) {
            System.out.print("Nombre de la nueva alergia: ");
            String nuevaAlergia = scanner.nextLine();
            mascota.getAlergias().add(nuevaAlergia);
        } else {
            System.out.println("Opcion no valida");
        }
    }

    public void editarEnfermedades(Scanner scanner) {
        while (true) {
            System.out.println("\n----- Enfermedades -----");
            mostrarEnfermedades();
            System.out.println("\n1. Agregar enfermedad");
            System.out.println("2. Editar enfermedad existente");
            System.out.println("3. Eliminar enfermedad");
            System.out.print("-> ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarEnfermedad(scanner); 
                    break;
                case 2:
                    System.out.println("\n----- Editar Enfermedades -----");
                    System.out.print("\nEnfermedad a editar: ");
                    int indiceEditar = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (indiceEditar >= 0 && indiceEditar < mascota.getEnfermedades().size()) {
                        System.out.print("Ingrese la nueva enfermedad: ");
                        String nuevaEnfermedad = scanner.nextLine();
                        mascota.getEnfermedades().set(indiceEditar, nuevaEnfermedad);
                    } else {
                        System.out.println("Indice invalido.");
                    }
                    break;
                case 3:
                    System.out.println("\n----- Eliminar Enfermedades -----");
                    System.out.print("Enfermedad a eliminar: ");
                    int indiceEliminar = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (indiceEliminar >= 0 && indiceEliminar < mascota.getEnfermedades().size()) {
                        mascota.getEnfermedades().remove(indiceEliminar);
                    } else {
                        System.out.println("Indice invalido.");
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opcion no valida");
            }
        }
    }

    public void agregarEnfermedad(Scanner scanner) {
        System.out.println("\n----- Agregar Enfermedades -----");
        String[] enfermedadesDisponibles = mascota.enfermedadesEspecie();
        for (int i = 0; i < enfermedadesDisponibles.length; i++) {
            System.out.println((i + 1) + ". " + enfermedadesDisponibles[i]);
        }
        System.out.println((enfermedadesDisponibles.length + 1) + ". Otros");

        System.out.print("\nEnfermedad a agregar: ");
        int seleccion = scanner.nextInt() - 1;
        scanner.nextLine();

        if (seleccion >= 0 && seleccion < enfermedadesDisponibles.length) {
            mascota.getEnfermedades().add(enfermedadesDisponibles[seleccion]);
        } else if (seleccion == enfermedadesDisponibles.length) { 
            System.out.print("Nombre de la nueva enfermedad: ");
            String nuevaEnfermedad = scanner.nextLine();
            mascota.getEnfermedades().add(nuevaEnfermedad);
        } else {
            System.out.println("Opcion no valida");
        }
    }

    private void mostrarVacunas() {
        System.out.println("\nVacunas actuales:");
        for (int i = 0; i < mascota.getVacunas().size(); i++) {
            System.out.println((i + 1) + ". " + mascota.getVacunas().get(i));
        }
    }

    private void mostrarAlergias() {
        System.out.println("\nAlergias actuales:");
        for (int i = 0; i < mascota.getAlergias().size(); i++) {
            System.out.println((i + 1) + ". " + mascota.getAlergias().get(i));
        }
    }

    private void mostrarEnfermedades() {
        System.out.println("\nEnfermedades actuales:");
        for (int i = 0; i < mascota.getEnfermedades().size(); i++) {
            System.out.println((i + 1) + ". " + mascota.getEnfermedades().get(i));
        }
    }
}
