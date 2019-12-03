package com.example.seguridadmovil;

public class Usuario {

    private String id;
    private String correo;
    private String nombre;
    private String telefono;

    public Usuario(String id, String correo, String nombre, String telefono) {
        this.id = id;
        this.correo = correo;
        this.nombre = nombre;
        this.telefono = telefono;
    }
    public Usuario() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() { return correo; }
}
