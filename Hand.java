package Projet_java;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static int nombre_cartes = 6;
    private List<Carte> cartes = new ArrayList<>();

    public Hand(Pioche pioche) {
        for (int i = 0; i < this.nombre_cartes; i++) {
            cartes.add(pioche.tirerCarte());
        }
    }

    public piocherCarte() {
        this.nombre_cartes++;
        cartes.add(pioche.tirerCarte());
    }

    public jouerCarte(int position_carte) {
        this.nombre_cartes--;
        return cartes.remove(cartes[i-1]);
    }
}   