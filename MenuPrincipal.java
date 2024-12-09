  import java.util.Scanner;

public class MenuPrincipal {
    private boolean settingsChoisis;
    private int modeDeJeu;       // 0 = Spectateur, 1 = Joueur vs IA, 2 = Joueur vs Joueur
    private int difficulteIA;    // 0 = Facile, 1 = Moyen, 2 = Difficile
    private int difficulteIA2;   // Difficulté de la deuxième IA, utilisée uniquement pour le mode Spectateur
    private int variante;        // 1 = Classique, 2 = Tactique

    public MenuPrincipal() {
        this.settingsChoisis = false; 
        this.modeDeJeu = -1;          
        this.difficulteIA = -1;       
        this.variante = -1;
    }

    public boolean gererMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean continuerProgramme = true;

        while (continuerProgramme) {
            afficherMenu();
            System.out.print("Votre choix : ");
            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    if (settingsChoisis) {
                        System.out.println("Lancement de la partie...");
                        return true;
                    } else {
                        System.out.println("Vous devez d'abord configurer les paramètres du jeu !");
                    }
                    break;
                case 2:
                    afficherRegles();
                    break;
                case 3:
                    System.out.println("Merci d'avoir joué ! À bientôt !");
                    continuerProgramme = false;
                    return false;
                case 4:
                    afficherCredits();
                    break;
                case 5:
                    configurerSettings(scanner);
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
        scanner.close();
        return false;
    }

    private void afficherMenu() {
        System.out.println("\n=== Menu Principal ===");
        System.out.println("1. Jouer une partie");
        System.out.println("2. Voir les règles");
        System.out.println("3. Quitter");
        System.out.println("4. Crédits");
        System.out.println("5. Configurer les paramètres");
        System.out.println("======================");
    }

    private void afficherRegles() {
        System.out.println("\n=== Règles du Jeu ===");
        System.out.println("1. Le but du jeu est de remporter un maximum de bornes.");
        System.out.println("2. Jouez vos cartes en formant des combinaisons gagnantes.");
        System.out.println("3. Le premier joueur à remporter 5 bornes gagne !");
        System.out.println("=====================");
    }

    private void afficherCredits() {
        System.out.println("\n=== Crédits ===");
        System.out.println("Développé par : [Ton Nom / Ton Équipe]");
        System.out.println("=================");
    }

    private void configurerSettings(Scanner scanner) {
        System.out.println("\n=== Configuration des paramètres ===");

        // Choix du mode de jeu
        System.out.println("Choisissez le mode de jeu :");
        System.out.println("0. Spectateur (IA vs IA)");
        System.out.println("1. Joueur vs IA");
        System.out.println("2. Joueur vs Joueur");
        modeDeJeu = demanderEntree(scanner, 0, 2);

        // Choix de la difficulté si IA est impliquée
        if (modeDeJeu == 0) {
            System.out.println("Choisissez la difficulté de la première IA :");
            System.out.println("0. Facile (aléatoire)");
            System.out.println("1. Moyen (début de réflexion)");
            System.out.println("2. Difficile (réflexion avancée)");
            difficulteIA = demanderEntree(scanner, 0, 2);

            System.out.println("Choisissez la difficulté de la deuxième IA :");
            System.out.println("0. Facile (aléatoire)");
            System.out.println("1. Moyen (début de réflexion)");
            System.out.println("2. Difficile (réflexion avancée)");
            difficulteIA2 = demanderEntree(scanner, 0, 2);
        } else if (modeDeJeu == 1) {
            System.out.println("Choisissez la difficulté de l'IA :");
            System.out.println("0. Facile (aléatoire)");
            System.out.println("1. Moyen (début de réflexion)");
            System.out.println("2. Difficile (réflexion avancée)");
            difficulteIA = demanderEntree(scanner, 0, 2);
            difficulteIA2 = -1; // Pas de deuxième IA
        } else {
            difficulteIA = -1; // Pas d'IA
            difficulteIA2 = -1; // Pas de deuxième IA
        }


        // Choix de la variante
        System.out.println("Choisissez la variante :");
        System.out.println("1. Classique");
        System.out.println("2. Tactique");
        variante = demanderEntree(scanner, 1, 2);

        settingsChoisis = true; // Les paramètres sont configurés
        System.out.println("Les paramètres du jeu ont été configurés avec succès !");
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
            if (choix < min || choix > max) {
                System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix < min || choix > max);
        return choix;
    }

    public int getModeDeJeu() {
        return modeDeJeu;
    }

    public int getDifficulteIA() {
        return difficulteIA;
    }

    public int getVariante() {
        return variante;
    }

    public int getDifficulteIA2() {
        return difficulteIA2;
    }
    

}
