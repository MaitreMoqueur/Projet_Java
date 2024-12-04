package Projet_java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Pioche {
    private int nombre_carte = 54;
    private List<Carte> cartes = new ArrayList<>();
    private static final String[] tab_couleurs = {"A", "B", "C", "D", "E", "F"};

    public Pioche(int version) {
        for (String couleur : tab_couleurs) {
            for (int numero = 1; numero <= 9; numero++) {
                cartes.add(new Carte(numero, couleur));
            }
        }

        Collections.shuffle(cartes, new Random());
    }

    public Carte tirerCarte() {
        if (cartes.isEmpty()) {
            throw new IllegalStateException("La pioche est vide !");
        }
        return cartes.remove(cartes.size() - 1);
    }

    public int getNombreDeCartes() {
        return cartes.size();
    }
}