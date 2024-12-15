package com.schottenTotten.model;

public class Carte {
    private int numero;
    private String couleur;

    public Carte(int numero, String couleur) {
        this.numero = numero;
        this.couleur = couleur;
    }

    public int getNumero() {
        return numero;
    }

    public String getCouleur() {
        return couleur;
    }
}