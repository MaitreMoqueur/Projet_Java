package main.java.com.schottenTotten.model;


import main.java.com.schottenTotten.model.Carte;
import main.java.com.schottenTotten.model.Borne;
import main.java.com.schottenTotten.model.Joueur;
import main.java.com.schottenTotten.model.Pioche;
import main.java.com.schottenTotten.view.InputHandler;




public class CarteTactique extends Carte {

    public enum TypeTactique {
        JOKER, ESPION, PORTE_BOUCLIER, COLIN_MAILLARD, COMBAT_DE_BOUE,
        CHASSEUR_DE_TETE, STRATEGE, BANSHEE, TRAITRE
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

    public Carte appliquerEffet(Joueur joueur, Borne borne, boolean activation, Pioche pioche) {
        switch (type) {
            case JOKER:
                if (activation) {
                    int valeur = InputHandler.demanderEntree(1, 8);
                    int indexCouleur = InputHandler.demanderEntree(1,8);
                    return new Carte(valeur, pioche.getTabCouleurs()[indexCouleur]);
                } else {
                    joueur.setJokerPlayed(true);
                    borne.ajouterCarte(joueur, this);
                }
                break;

            case ESPION:
                if (activation) {
                    int indexCouleur = InputHandler.demanderEntree(1,8);
                    return new Carte(7, pioche.getTabCouleurs()[indexCouleur]);
                } else {
                    borne.ajouterCarte(joueur, this);
                }
                break;

            case PORTE_BOUCLIER:
                if (activation) {
                    int valeur = InputHandler.demanderEntree(1, 3);
                    int indexCouleur = InputHandler.demanderEntree(1,8);
                    return new Carte(valeur, pioche.getTabCouleurs()[indexCouleur]);
                } else {
                    borne.ajouterCarte(joueur, this);
                }
                break;

            case COLIN_MAILLARD:
            case COMBAT_DE_BOUE:
            case CHASSEUR_DE_TETE:
            case STRATEGE:
            case BANSHEE:
            case TRAITRE:
                // Implémentations spécifiques ici
                break;

            default:
                throw new UnsupportedOperationException("Effet non implémenté pour le type : " + type);
        }
        return null; // Par défaut, pas de carte créée
    }
}
