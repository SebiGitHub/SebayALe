package model;

public class Alumno {

    private int numero;
    private String usuario;
    private String contrasenia;
    private String f_nac;
    private int n_media;
    private int imagen;


    public Alumno(int numero, String usuario, String contrasenia, String f_nac, int imagen, int n_media) {
        this.numero = numero;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.f_nac = f_nac;
        this.imagen = imagen;
        this.n_media = n_media;
    }

    //Getters
    public int getNumero() {
        return numero;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getF_nac() {
        return f_nac;
    }

    public int getN_media() {
        return n_media;
    }

    public int getImagen() {
        return imagen;
    }

    //Setters
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setF_nac(String f_nac) {
        this.f_nac = f_nac;
    }

    public void setN_media(int n_media) {
        this.n_media = n_media;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }






}
