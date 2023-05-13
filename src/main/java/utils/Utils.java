package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javafx.scene.control.DatePicker;

/**
 * Clase utilitaria con métodos estáticos para validaciones comunes como:
 * Validación de fecha seleccionada en DatePicker Validación de fecha LocalDate
 * Validación de número de identificación personal (DNI) Validación de número de
 * teléfono Validación de dirección de correo electrónico Validación de código
 * postal (CP) Validación de salario
 *
 * @author Nuria Vázquez 
 */
public class Utils {

    /**
     *
     * Valida que la fecha seleccionada en el DatePicker no sea posterior a la
     * fecha actual.
     *
     * @param fechaNacimiento Fecha seleccionada en el DatePicker.
     * @return true si la fecha seleccionada es válida (no posterior a la fecha
     * actual), false de lo contrario.
     */
    public static boolean validarFechaDatePicker(DatePicker fechaNacimiento) {
        LocalDate fechaSeleccionada = fechaNacimiento.getValue();
        LocalDate fechaActual = LocalDate.now();
        if (fechaSeleccionada == null) {
            // Si fechaSeleccionada es nula, lanzar una excepción o devolver false
            return false;
        }
        return !fechaSeleccionada.isAfter(fechaActual);
    }

    /**
     *
     * Valida que la fecha LocalDate no sea posterior a la fecha actual.
     *
     * @param fechaSeleccionada Fecha LocalDate a validar.
     * @return true si la fecha seleccionada es válida (no posterior a la fecha
     * actual), false de lo contrario.
     */
    public static boolean validarFechaLocalDate(LocalDate fechaSeleccionada) {
        LocalDate fechaActual = LocalDate.now();
        return !fechaSeleccionada.isAfter(fechaActual);
    }

    /**
     * Comprueba si una fecha seleccionada en un objeto DatePicker es posterior
     * o igual a la fecha actual.
     *
     * @param fechaCita El objeto DatePicker que contiene la fecha a validar.
     * @return true si la fecha es posterior o igual a la fecha actual, false en
     * caso contrario o si el objeto DatePicker es nulo.
     */
    
    public static boolean validarFechaCita(DatePicker fechaCita) {
        LocalDate fechaSeleccionada = fechaCita.getValue();
        LocalDate fechaActual = LocalDate.now();
        return fechaSeleccionada != null && !fechaSeleccionada.isBefore(fechaActual);
    }
    
    
    public static boolean validarHoraCita(String horaCita) {
    if (horaCita == null) {
        return false;
    }
    try {
        LocalTime hora = LocalTime.parse(horaCita, DateTimeFormatter.ofPattern("HH:mm"));
        return hora.compareTo(LocalTime.of(9, 0)) >= 0 && hora.compareTo(LocalTime.of(20, 0)) <= 0;
    } catch (DateTimeParseException e) {
        return false;
    }
}

    
     

    /**
     *
     * Valida que el número de identificación personal (DNI) tenga el formato
     * correcto.
     *
     * @param dni Número de identificación personal (DNI) a validar.
     * @return true si el formato del DNI es válido, false de lo contrario.
     */
    public static boolean validarDni(String dni) {
        String dniPattern = "\\d{8}[A-Z]";
        return dni.matches(dniPattern);
    }

    /**
     *
     * Valida que el número de teléfono tenga el formato correcto.
     *
     * @param telefono Número de teléfono a validar.
     * @return true si el formato del teléfono es válido, false de lo contrario.
     */
    public static boolean validarTelefono(String telefono) {
        String telefonoPattern = "\\d{9}";
        return telefono.matches(telefonoPattern);
    }

    /**
     *
     * Valida que la dirección de correo electrónico tenga el formato correcto.
     *
     * @param email Dirección de correo electrónico a validar.
     * @return true si el formato del correo electrónico es válido, false de lo
     * contrario.
     */
    public static boolean validarEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        return email.matches(emailPattern);
    }

    /**
     *
     * Valida que el código postal (CP) tenga el formato correcto.
     *
     * @param cp Código postal a validar.
     * @return true si el formato del código postal es válido, false de lo
     * contrario.
     */
    public static boolean validarCp(String cp) {
        String cpPattern = "\\d{5}";
        return cp.matches(cpPattern);
    }

    /**
     *
     * Valida que el salario tenga el formato correcto.
     *
     * @param salario Salario a validar.
     * @return true si el formato del salario es válido, false de lo contrario.
     */
    public static boolean validarSalario(String salario) {
        String salarioPattern = "\\d+(\\.\\d{2})?|^\\d+$";
        return salario.matches(salarioPattern);
    }

}
