package com.schottenTotten.model;

import java.util.ArrayList;
import java.util.List;

public class Muraille {
    private int id;
    private List<Carte> attaque;
    private List<Carte> defense;
    private boolean detruite;

    public Muraille(int id) {
        this.id = id;
        this.attaque = new ArrayList<>();
        this.defense = new ArrayList<>();
        this.detruite = false;
    }

    public void ajouterCarteAttaque(Carte carte) {
        attaque.add(carte);
    }

    public void ajouterCarteDefense(Carte carte) {
        defense.add(carte);
    }

    public boolean estDetruite() {
        return detruite;
    }

    @Override
    public String toString() {
        return "Muraille " + id + " | Attaque: " + attaque + " | Défense: " + defense;
    }
}
