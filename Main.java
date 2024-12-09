package Projet_java;

public class Main {
    public static void main(String[] args) {
        Menu_principal menu = new Menu_principal();
        menu.lancement();
        
        Pioche pioche = new Pioche();
        Main main1 = new Main(pioche);
        Main main2 = new Main(pioche);
        Joueur joueur1 = new Joueur(pioche);
        Joueur joueur2 = new Joueur(pioche);
    }
}
