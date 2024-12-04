import java.util.List;
import java.util.Arrays;

abstract class Joueur {
    protected String nom;
    protected int score;
    protected List<String> main; // Liste des cartes du joueur

    public Joueur(String nom, List<String> main) {
        this.nom = nom;
        this.main = main;
        this.score = 0; // Initialisé à 0 par défaut
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void ajouterPoint() {
        this.score++;
    }

    public List<String> getMain() {
        return main;
    }

    public void setMain(List<String> main) {
        this.main = main;
    }

    @Override
    public String toString() {
        return "Nom: " + nom + ", Score: " + score;
    }
}

class JoueurReel extends Joueur {
    public JoueurReel(String nom, List<String> main) {
        super(nom, main);
    }

    @Override
    public String toString() {
        return super.toString() + " (Joueur réel)";
    }
}

class IA {
    private int difficulte; // 0 = Facile, 1 = Moyen, 2 = Difficile

    public IA(int difficulte) {
        this.difficulte = difficulte;
    }

    public int getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(int difficulte) {
        if (difficulte >= 0 && difficulte <= 2) {
            this.difficulte = difficulte;
        } else {
            throw new IllegalArgumentException("Difficulté invalide : " + difficulte);
        }
    }

    @Override
    public String toString() {
        switch (difficulte) {
            case 0: return "Facile";
            case 1: return "Moyen";
            case 2: return "Difficile";
            default: return "Inconnu";
        }
    }
}

class JoueurIA extends Joueur {
    private IA ia;

    public JoueurIA(String nom, List<String> main, IA ia) {
        super(nom, main);
        this.ia = ia;
    }

    public IA getIA() {
        return ia;
    }

    public void setIA(IA ia) {
        this.ia = ia;
    }

    @Override
    public String toString() {
        return super.toString() + " (IA - Difficulté: " + ia + ")";
    }
}

public class Main {
    public static void main(String[] args) {
        // Joueur réel
        Joueur joueurReel = new JoueurReel("Alice", Arrays.asList("As de Coeur", "Roi de Pique"));
        System.out.println(joueurReel);

        // IA
        IA iaFacile = new IA(0); // Difficulté facile
        Joueur joueurIA = new JoueurIA("Bot1", Arrays.asList("10 de Carreau", "9 de Trèfle"), iaFacile);
        System.out.println(joueurIA);

        // Ajout de points
        joueurReel.ajouterPoint();
        joueurIA.ajouterPoint();

        System.out.println("Après mise à jour des scores :");
        System.out.println(joueurReel);
        System.out.println(joueurIA);
    }
}
