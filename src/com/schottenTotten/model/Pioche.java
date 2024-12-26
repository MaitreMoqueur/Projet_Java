package com.schottenTotten.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Pioche {
    private int nombre_cartes = 54;
    private List<Carte> cartes = new ArrayList<>();
    private static final String[] tab_couleurs = {"Rouge", "Bleu", "Charbon", "Jaune", "Orange", "Violet"};

    public Pioche() {
        for (String couleur : tab_couleurs) {
            for (int numero = 1; numero <= 9; numero++) {
                cartes.add(new Carte(numero, couleur));
            }
        }
        Collections.shuffle(cartes, new Random());
    }

    public Carte tirerCarte() {
        if (getNombreDeCartes() == 0) {
            throw new IllegalStateException("La pioche est vide !");
        }
        this.nombre_cartes--;
        return cartes.remove(this.nombre_cartes);
    }

    public int getNombreDeCartes() {
        return this.nombre_cartes;
    }
}