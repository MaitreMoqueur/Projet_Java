import java.util.ArrayList;
import java.util.List;

public class Borne {
    public enum Etat {
        LIBRE,
        REVENDIQUE_J1,
        REVENDIQUE_J2,
        BLOQUE
    }

    private List<String> cartesJoueur1; // Cartes jouées par le joueur 1
    private List<String> cartesJoueur2; // Cartes jouées par le joueur 2
    private Etat etat;                 // État de la borne

    public Borne() {
        this.cartesJoueur1 = new ArrayList<>();
        this.cartesJoueur2 = new ArrayList<>();
        this.etat = Etat.LIBRE; // Par défaut, la borne est libre
    }

    // Ajoute une carte pour un joueur
    public void ajouterCarte(Joueur joueur, String carte) {
        if (etat != Etat.LIBRE) {
            System.out.println("Cette borne a déjà été revendiquée.");
            return;
        }

        if (joueur instanceof JoueurReel) {
            cartesJoueur1.add(carte);
            System.out.println("Carte " + carte + " ajoutée pour le joueur réel.");
        } else if (joueur instanceof JoueurIA) {
            cartesJoueur2.add(carte);
            System.out.println("Carte " + carte + " ajoutée pour l'IA.");
        }
    }

    // Vérifie si les conditions pour revendiquer la borne sont remplies
    public boolean verifierConditions(Joueur joueur) {
        // Logique simplifiée pour illustrer
        // Ici, on peut ajouter des règles spécifiques pour déterminer la revendication
        if (cartesJoueur1.size() >= 3) {
            etat = Etat.REVENDIQUE_J1;
            return true;
        } else if (cartesJoueur2.size() >= 3) {
            etat = Etat.REVENDIQUE_J2;
            return true;
        }
        return false;
    }

    // Retourne l'état actuel de la borne
    public Etat getEtat() {
        return etat;
    }

    // Méthode pour afficher l'état de la borne
    public void afficherEtat() {
        System.out.println("Borne : ");
        System.out.println("Cartes Joueur 1 : " + cartesJoueur1);
        System.out.println("Cartes Joueur 2 : " + cartesJoueur2);
        System.out.println("État : " + etat);
    }
}

public class GestionBornes {
    private List<Borne> bornes;

    public GestionBornes() {
        this.bornes = new ArrayList<>();
        for (int i = 0; i < 9; i++) { // Créer 9 bornes
            bornes.add(new Borne());
        }
    }

    // Ajouter une carte à une borne
    public void ajouterCarte(int indexBorne, Joueur joueur, String carte) {
        if (indexBorne < 0 || indexBorne >= bornes.size()) {
            System.out.println("Index de borne invalide !");
            return;
        }
        bornes.get(indexBorne).ajouterCarte(joueur, carte);
    }

    // Vérifie les conditions de victoire pour toutes les bornes
    public boolean verifierConditionsVictoire(Joueur joueur) {
        int bornesRevendiquees = 0;

        for (Borne borne : bornes) {
            if (borne.verifierConditions(joueur)) {
                bornesRevendiquees++;
            }
        }

        return bornesRevendiquees >= 5; // Exemple : gagner avec 5 bornes revendiquées
    }

    // Afficher l'état des bornes
    public void afficherBornes() {
        for (int i = 0; i < bornes.size(); i++) {
            System.out.println("Borne " + (i + 1) + " :");
            bornes.get(i).afficherEtat();
            System.out.println();
        }
    }
}