import java.util.Scanner;

public class GestionPartie {
    private GestionJoueurs gestionJoueurs;
    private boolean partieEnCours;

    public GestionPartie(GestionJoueurs gestionJoueurs) {
        this.gestionJoueurs = gestionJoueurs;
        this.partieEnCours = true;
    }

    public void demarrerPartie() {
        while (partieEnCours) {
            faireTourJoueur(joueur_1);
            if (verifierConditionsVictoire(joueur_1)) {
                partieEnCours = false;
                break;
            }
            faireTourJoueur(joueur_2);
            if (verifierConditionsVictoire(joueur_2)) {
                partieEnCours = false;
                break;
            }
        }
        annoncerFinPartie();
    }

    private void faireTourJoueur(Joueur joueur) {
        System.out.println("\nC'est le tour de : " + joueur.getNom());
        if (joueur instanceof JoueurIA) {
            faireTourIA((JoueurIA) joueur);
        } else {
            faireTourJoueurReel((JoueurReel) joueur);
        }
    }
    private void faireTourIA(JoueurIA joueurIA) {
        System.out.println(joueurIA.getNom() + " réfléchit...");
        // Logique spécifique à l'IA
    }

    private void faireTourJoueurReel(JoueurReel joueurReel) {
        System.out.println(joueurReel.getNom() + ", c'est à vous de jouer !");

        Scanner scanner = new Scanner(System.in);

        // Étape 1 : Jouer une carte
        jouerCarte(joueurReel, scanner);

        // Étape 2 : Revendiquer des bornes
        boolean continuer = true;
        while (continuer) {
            System.out.print("Voulez-vous tenter de revendiquer une borne ? (oui/non) : ");
            String choix = scanner.nextLine().trim().toLowerCase();
            if (choix.equals("oui")) {
                revendiquerBorne(joueurReel, scanner);
            } else if (choix.equals("non")) {
                continuer = false;
            } else {
                System.out.println("Choix invalide. Veuillez répondre par 'oui' ou 'non'.");
            }
        }

        // Étape 3 : Piocher une carte
        piocherCarte(joueurReel);
    }
    
    private void revendiquerBorne(JoueurReel joueur, Scanner scanner) {
        System.out.print("Entrez le numéro de la borne à revendiquer (1-9) : ");
        int borne = demanderEntree(scanner, 1, 9);
    
        // Logique de revendication : exemple d'une vérification simplifiée
        boolean revendicationReussie = verifierRevendication(joueur, borne);
        if (revendicationReussie) {
            System.out.println("Vous avez revendiqué la borne " + borne + " !");
            // Appliquer les changements sur la borne : à définir selon les règles
        } else {
            System.out.println("Revendication échouée. Les conditions ne sont pas remplies pour la borne " + borne + ".");
        }
    }

    private boolean verifierConditionsVictoire() {
        for (Joueur joueur : gestionJoueurs.getJoueurs()) {
            if (joueur.getScore() >= 5) { // Exemple : victoire à 5 points
                System.out.println("Le joueur " + joueur.getNom() + " a gagné !");
                return true;
            }
        }
        return false;
    }

    private void annoncerFinPartie() {
        System.out.println("\n=== Fin de la partie ===");
        System.out.println(joueur_1.getNom() + " : " + joueur_1.getScore() + " points");
        System.out.println(joueur_2.getNom() + " : " + joueur_2.getScore() + " points");
        System.out.println("Merci d'avoir joué !");
    }
}

    private int demanderEntree(Scanner scanner, int min, int max) {
        int choix;
        do {
            System.out.print("Votre choix (" + min + "-" + max + ") : ");
            while (!scanner.hasNextInt()) {
                System.out.println("Entrée invalide. Veuillez entrer un chiffre.");
                scanner.next(); // Nettoie l'entrée non valide
            }
            choix = scanner.nextInt();
            scanner.nextLine(); // Consomme le retour à la ligne
            if (choix < min || choix > max) {
                System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix < min || choix > max);
        return choix;
    }
