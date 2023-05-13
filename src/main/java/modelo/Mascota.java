package modelo;

import java.sql.Date;

/**
 * Clase que respresenta una mascota y sus atributos
 * 
 * @author Nuria Vázquez 
 */

public class Mascota {

    private int idMascota;
    private String nombre;
    private Date fechaNacimiento;
    private String raza;
    private String especie;
    private String dniCliente;
    private Sexo sexo;
    
    public enum Sexo {
        M,F
    }
    

    public Mascota() {
    }

    public Mascota(int idMascota, String nombre, Sexo sexo, Date fechaNacimiento, String raza, String especie, String dniCliente) {

        this.nombre = nombre;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.raza = raza;
        this.especie = especie;
        this.dniCliente = dniCliente;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getRaza() {
        return raza;
    }

    public String getEspecie() {
        return especie;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    @Override
    public String toString() {
        return nombre + " " + dniCliente;
    }
}
