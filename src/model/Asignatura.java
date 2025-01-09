package model;

public class Asignatura {

    private int codigo;
    private String nombre;
    private float nota;
    private int aluNumero;

    public Asignatura(int codigo, String nombre, float nota,int aluNumero) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nota = nota;
        this.aluNumero = aluNumero;
    }

    // Getters
    public int getCodigo(){
        return codigo;
    }

    public String getNombre(){
        return nombre;
    }

    public float getNota(){
        return nota;
    }

    public int getAluNumero(){
        return aluNumero;
    }


    // Setters
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public void setAluNumero(int aluNumero) {
        this.aluNumero = aluNumero;
    }
}
