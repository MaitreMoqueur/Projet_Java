package com.schottenTotten.model;

public class CarteTactique extends Carte {

    // Enum pour les différents types de cartes tactiques
    public enum TypeTactique {
        JOKER,       // Carte Joker
        ESPION,      // Carte Clan de valeur 7
        PORTE_BOUCLIER, // Carte Clan de valeur 1, 2 ou 3
        COLIN_MAILLARD, // Règle spéciale pour la borne
        COMBAT_DE_BOUE, // Nécessite 4 cartes par côté
        CHASSEUR_DE_TETE, // Pioche trois cartes, en choisit deux
        STRATEGE,     // Déplace une carte entre bornes
        BANSHEE,      // Retire une carte adverse
        TRAITRE       // Déplace une carte adverse
    }

    private TypeTactique type;

    public CarteTactique(int numero, String couleur, TypeTactique type) {
        super(numero, couleur);
        this.type = type;
    }

    public TypeTactique getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Carte Tactique: " + type + " [" + super.toString() + "]";
    }

    // Méthodes spécifiques aux effets de chaque carte tactique
    public void appliquerEffet(Joueur joueur, Borne borne, boolean activation) {
        switch (type) {
            case JOKER:
                if (activation){
                    // Choisir la couleur et la valeur au moment de revendiquer
                    System.out.println(joueur.getPseudo() + " doit choisir la couleur et la valeur du Joker");
                }
                else {
                    // Se comporte comme une carte Clan classique
                    borne.ajouterCarte(joueur, this);
                }
                break;
            case ESPION:
                if (activation){
                    // Choisir la couleur et la valeur au moment de revendiquer
                    System.out.println(joueur.getPseudo() + " doit choisir la couleur de L'espion");
                }
                else {
                    // Se comporte comme une carte Clan classique
                    borne.ajouterCarte(joueur, this);
                }
                break;

            case PORTE_BOUCLIER:
                if (activation){
                    // Choisir la couleur et la valeur au moment de revendiquer
                    System.out.println(joueur.getPseudo() + " doit choisir la couleur et la valeur du porte-bouclier");
                }
                else {
                    // Se comporte comme une carte Clan classique
                    borne.ajouterCarte(joueur, this);
                }
            break;
            case COLIN_MAILLARD:
                // Change les règles de la borne
                //borne.appliquerRegleSpeciale("COLIN_MAILLARD");
                break;

            case COMBAT_DE_BOUE:
                // Change les règles de la borne pour 4 cartes par côté
                //borne.appliquerRegleSpeciale("COMBAT_DE_BOUE");
                break;

            case CHASSEUR_DE_TETE:
                // Pioche trois cartes et réorganise
                System.out.println(joueur.getPseudo() + " active Chasseur de Tête");
                break;

            case STRATEGE:
                // Déplace ou défausse une carte
                System.out.println(joueur.getPseudo() + " active Stratège");
                break;

            case BANSHEE:
                // Retire une carte adverse
                System.out.println(joueur.getPseudo() + " active Banshee");
                break;

            case TRAITRE:
                // Déplace une carte adverse
                System.out.println(joueur.getPseudo() + " active Traître");
                break;
        }
    }
}
