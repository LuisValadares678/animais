package com.produtor.animais;


public class Animal {
    private String codigo;
    private double peso;
    private String sexo;

    public Animal(String codigo, double peso, String sexo) {
        this.codigo = codigo;
        this.peso = peso;
        this.sexo = sexo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "codigo='" + codigo + '\'' +
                ", peso=" + peso +
                ", sexo='" + sexo + '\'' +
                '}';
    }
}
