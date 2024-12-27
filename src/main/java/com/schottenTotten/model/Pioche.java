package main.java.com.schottenTotten.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Pioche {
    private List<Carte> cartes;
    private static final String[] tab_couleurs = {"Rouge", "Bleu", "Charbon", "Jaune", "Orange", "Violet"};

    // Constructeur pour la pioche classique
    public Pioche() {
        this.cartes = new ArrayList<>();
        for (String couleur : tab_couleurs) {
            for (int numero = 1; numero <= 9; numero++) {
                cartes.add(new Carte(numero, couleur));
            }
        }
        melangerCartes();
    }

    // Constructeur pour la pioche tactique
    public Pioche(boolean tactique) {
        this.cartes = new ArrayList<>();
        if (tactique) {
            // Ajouter des cartes tactiques
            for (int i = 0; i < 2; i++) {
                cartes.add(new CarteTactique(0, "Neutre", CarteTactique.TypeTactique.JOKER));
            }
            cartes.add(new CarteTactique(7, "Neutre", CarteTactique.TypeTactique.ESPION));
            cartes.add(new CarteTactique(1, "Neutre", CarteTactique.TypeTactique.PORTE_BOUCLIER));
            cartes.add(new CarteTactique(0, "Neutre", CarteTactique.TypeTactique.COLIN_MAILLARD));
            cartes.add(new CarteTactique(0, "Neutre", CarteTactique.TypeTactique.COMBAT_DE_BOUE));
            cartes.add(new CarteTactique(0, "Neutre", CarteTactique.TypeTactique.CHASSEUR_DE_TETE));
            cartes.add(new CarteTactique(0, "Neutre", CarteTactique.TypeTactique.STRATEGE));
            cartes.add(new CarteTactique(0, "Neutre", CarteTactique.TypeTactique.BANSHEE));
            cartes.add(new CarteTactique(0, "Neutre", CarteTactique.TypeTactique.TRAITRE));
        }
        melangerCartes();
    }

    // Mélanger les cartes
    private void melangerCartes() {
        Collections.shuffle(cartes, new Random());
    }

    // Tirer une carte
    public Carte tirerCarte() {
        if (!cartes.isEmpty()) {
            return cartes.remove(cartes.size() - 1);
        }
        return null;
    }

    // Obtenir le nombre de cartes restantes
    public int getNombreDeCartes() {
        return cartes.size();
    }

    // Obtenir les couleurs des cartes
    public static String[] getTabCouleurs() {
        return tab_couleurs;
    }
}
