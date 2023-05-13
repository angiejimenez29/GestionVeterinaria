package modelo;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Clase que respresenta un recepcionista y sus atributos
 *
 * @author Nuria Vázquez 
 */
public class Recepcionista {

    private int idRecepcionista;
    private BigDecimal salario;
    private String nombre;
    private String apellidoUno;
    private String apellidoDos;
    private String dni;
    private Date fechaNac;
    private String direccion;
    private int cp;
    private int telefono;
    private String email;
    private Turno turno;

    public enum Turno {
        M, T, N
    }

    public Recepcionista() {
    }

    // Constructor
    public Recepcionista(int idRecepcionista, BigDecimal salario, String nombre, String apellidoUno,
            String apellidoDos, String dni, Date fechaNac, String direccion, int cp,
            int telefono, String email, Turno turno) {
        this.idRecepcionista = idRecepcionista;
        this.salario = salario;
        this.nombre = nombre;
        this.apellidoUno = apellidoUno;
        this.apellidoDos = apellidoDos;
        this.dni = dni;
        this.fechaNac = fechaNac;
        this.direccion = direccion;
        this.cp = cp;
        this.telefono = telefono;
        this.email = email;
        this.turno = turno;
    }

    // Getters y setters
    public int getIdRecepcionista() {
        return idRecepcionista;
    }

    public void setIdRecepcionista(int idRecepcionista) {
        this.idRecepcionista = idRecepcionista;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoUno() {
        return apellidoUno;
    }

    public void setApellidoUno(String apellidoUno) {
        this.apellidoUno = apellidoUno;
    }

    public String getApellidoDos() {
        return apellidoDos;
    }

    public void setApellidoDos(String apellidoDos) {
        this.apellidoDos = apellidoDos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return nombre + " " + dni;
    }

}
