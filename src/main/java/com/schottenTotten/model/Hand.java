package main.java.com.schottenTotten.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    protected static int nombre_cartes = 6;
    protected List<Carte> cartes = new ArrayList<>();

    public Hand(Pioche pioche) {
        for (int i = 0; i < this.nombre_cartes; i++) {
            cartes.add(pioche.tirerCarte());
        }
    }

    public void piocherCarte(Pioche pioche) {
        this.nombre_cartes++;
        cartes.add(pioche.tirerCarte());
    }

    public int getNombreCartes() {
        return cartes.size();
    }

    public List<Carte> getCartes() {
        return cartes;
    }

    public Carte retirerCarte(int index) {
        if (index < 0 || index >= cartes.size()) {
            throw new IndexOutOfBoundsException("Index de carte invalide !");
        }
        return cartes.remove(index);
    }

    public Carte getCarte(int indexCarte) {
        if (indexCarte < 0 || indexCarte >= cartes.size()) {
            throw new IndexOutOfBoundsException("Index de carte invalide !");
        }
        return cartes.get(indexCarte);
    }


}   