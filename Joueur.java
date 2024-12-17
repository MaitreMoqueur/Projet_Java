package Projet_java;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
    private String pseudo;
    

    public Joueur(Pioche pioche) {
        Hand main = new Hand(pioche);


        this.nom = nom;
        this.estHumain = estHumain;
        this.main = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public boolean isHumain() {
        return estHumain;
    }

    public void ajouterCarte(Carte carte) {
        main.add(carte);
    }

    public Carte jouerCarte(int index) {
        if (index < 0 || index >= main.size()) {
            throw new IllegalArgumentException("Index de carte invalide");
        }
        return main.remove(index);
    }

    public List<Carte> getMain() {
        return new ArrayList<>(main);
    }
}
