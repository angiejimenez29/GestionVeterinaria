package modelo;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

/**
 * Clase que respresenta una cita y sus atributos
 *
 * @author Nuria Vázquez 
 */
public class Cita {

    private int idCita;
    private int idMascota;
    private int idVeterinario;
    private int idCliente;
    private int idRecepcionista;

    private Date fechaCita;
    private Time horaCita;
    private String tipoConsulta;
    private String diagnostico;
    private String tratamiento;
    private String medicacion;
    private BigDecimal precio;

    private String nombreRecepcion;
    private String nombreVeterinario;
    private String apellidoUnoVeterinario;
    private String apellidoDosVeterinario;
    private String nombreMascota;
    private String nombreCliente;
    private String apellidoUnoCliente;
    private String apellidoDosCliente;
    private String dniCliente;

    public Cita() {
    }

    public Cita(int idCita, int idMascota, int idVeterinario, int idCliente, int idRecepcionista,
            Date fechaCita, Time horaCita, String tipoConsulta, String diagnostico, String tratamiento,
            String medicacion, BigDecimal precio, String nombreVeterinario, String apellidoUnoVeterinario,
            String apellidoDosVeterinario, String nombreMascota, String nombreRecepcion, String nombreCliente,
            String apellidoUnoCliente, String apellidoDosCliente, String dniCliente) {

        this.idCita = idCita;
        this.idMascota = idMascota;
        this.idVeterinario = idVeterinario;
        this.idCliente = idCliente;
        this.idRecepcionista = idRecepcionista;
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
        this.tipoConsulta = tipoConsulta;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.medicacion = medicacion;
        this.precio = precio;
        this.nombreVeterinario = nombreVeterinario;
        this.apellidoUnoVeterinario = apellidoUnoVeterinario;
        this.apellidoDosVeterinario = apellidoDosVeterinario;
        this.nombreMascota = nombreMascota;
        this.nombreRecepcion = nombreRecepcion;
        this.nombreCliente = nombreCliente;
        this.apellidoUnoCliente = apellidoUnoCliente;
        this.apellidoDosCliente = apellidoDosCliente;
        this.dniCliente = dniCliente;
    }

    public String getDniCliente() {

        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getNombreRecepcion() {

        return nombreRecepcion;
    }

    public void setNombreRecepcion(String nombreRecepcion) {
        this.nombreRecepcion = nombreRecepcion;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public int getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdRecepcionista() {
        return idRecepcionista;
    }

    public void setIdRecepcionista(int idRecepcionista) {
        this.idRecepcionista = idRecepcionista;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Time getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(Time horaCita) {
        this.horaCita = horaCita;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getMedicacion() {
        return medicacion;
    }

    public void setMedicacion(String medicacion) {
        this.medicacion = medicacion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getNombreVeterinario() {
        return nombreVeterinario;
    }

    public void setNombreVeterinario(String nombreVeterinario) {
        this.nombreVeterinario = nombreVeterinario;
    }

    public String getApellidoUnoVeterinario() {
        return apellidoUnoVeterinario;
    }

    public void setApellidoUnoVeterinario(String apellidoUnoVeterinario) {
        this.apellidoUnoVeterinario = apellidoUnoVeterinario;
    }

    public String getApellidoDosVeterinario() {
        return apellidoDosVeterinario;
    }

    public void setApellidoDosVeterinario(String apellidoDosVeterinario) {
        this.apellidoDosVeterinario = apellidoDosVeterinario;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoUnoCliente() {
        return apellidoUnoCliente;
    }

    public void setApellidoUnoCliente(String apellidoUnoCliente) {
        this.apellidoUnoCliente = apellidoUnoCliente;
    }

    public String getApellidoDosCliente() {
        return apellidoDosCliente;
    }

    public void setApellidoDosCliente(String apellidoDosCliente) {
        this.apellidoDosCliente = apellidoDosCliente;
    }

    @Override
    public String toString() {
        return  dniCliente;
    }

}
