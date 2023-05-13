package modelo;

import java.sql.Date;

/**
 * Clase que respresenta un cliente y sus atributos
 *
 * @author Nuria Vázquez 
 */
public class Cliente {

    private int idCliente;
    private String seguro;
    private String dni;
    private String nombre;
    private String apellidoUno;
    private String apellidoDos;
    private Date fechaNac;
    private int telefono;
    private String direccion;
    private int cp;
    private String poblacion;
    private String email;
    private Suscripcion suscripcion;

    public enum Suscripcion {
        S, N
    }

    public Cliente() {
    }

    public Cliente(int idCliente, String seguro, String dni, String nombre, String apellidoUno,
            String apellidoDos, Date fechaNac, int telefono, String direccion, int cp,
            String poblacion, String email, Suscripcion suscripcion) {
        this.idCliente = idCliente;
        this.seguro = seguro;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidoUno = apellidoUno;
        this.apellidoDos = apellidoDos;
        this.fechaNac = fechaNac;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cp = cp;
        this.poblacion = poblacion;
        this.email = email;
        this.suscripcion = suscripcion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getSeguro() {
        return seguro;
    }

    public void setSeguro(String seguro) {
        this.seguro = seguro;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
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

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Suscripcion getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(Suscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }

    @Override
    public String toString() {
        return dni;
    }
}
