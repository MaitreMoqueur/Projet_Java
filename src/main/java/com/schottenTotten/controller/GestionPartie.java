package main.java.com.schottenTotten.controller;

import java.util.ArrayList;
import java.util.List;

import main.java.com.schottenTotten.model.*;
import main.java.com.schottenTotten.view.ConsoleView;
import main.java.com.schottenTotten.ai.*;
import main.java.com.schottenTotten.view.InputHandler;

public class GestionPartie {
    private final List<Joueur> liste_joueurs;
    private final List<Borne> liste_bornes;
    private final ConsoleView view;
    public boolean partieEnCours; //public pour tests
    private int numeroTour;
    private final Variante variante;

    public GestionPartie(List<Joueur> joueurs, List<Borne> bornes, ConsoleView view, Variante variante) {
        this.liste_joueurs = joueurs;
        this.liste_bornes = bornes;
        this.view = view;
        this.partieEnCours = true;
        this.variante = variante;
    }

    public void demarrerPartie(Pioche pioche, Pioche piocheTactique) {
        view.afficherLancementPartie();
        while (partieEnCours) {
            for (Joueur joueur : liste_joueurs) {
                if (joueur.getMain().getNombreCartes() == 0){
                    System.out.println("Plus de carte en main : égalité (Bravo i guess)");
                    partieEnCours = false;
                    break;
                }
                faireTourJoueur(joueur, pioche, piocheTactique);
                Joueur gagnant = verifierConditionVictoire();
                if (gagnant != null) {
                    partieEnCours = false;
                    break;
                }
            }
            numeroTour++;
        }
    }

    private void faireTourJoueur(Joueur joueur, Pioche pioche, Pioche piocheTactique) {
        if (joueur instanceof IA) {
            faireTourIA((IA) joueur, pioche, piocheTactique);
        } else {
            faireTourJoueurReel(joueur, pioche, piocheTactique);
        }
    }

    private void faireTourIA(IA joueurIA, Pioche pioche, Pioche piocheTactique) {
        //Message debut Tour IA
        view.afficherIATour(joueurIA);

        if (variante == Variante.EXPERT){
            List<Integer> bornesRevendicables = getlistbornesrevendicable(liste_bornes);
            List<Integer> bornesCapturees = joueurIA.revendiquerBorneIA(liste_bornes, bornesRevendicables);
            view.afficherBornesCaptureesParIA(bornesCapturees);
        }
        
        // IA joue une carte sur une borne
        boolean carteClanPlayed = false;
        boolean continuer = true;
        while (continuer) {
            List<Integer> bornesJouables = getlistbornesjouable(liste_bornes, joueurIA);
            List<Integer> resultat = joueurIA.jouerCarteIA(liste_bornes, bornesJouables);
            int indexCarte = resultat.get(0);
            Carte carte = joueurIA.getMain().getCarte(indexCarte);
            int indexBorne = resultat.get(1);
            joueurIA.jouerCarte(indexCarte, liste_bornes.get(indexBorne));
            if (!(carte instanceof CarteTactique)) {
                carteClanPlayed = true;
            }
            else {
                joueurIA.incrementerCarteTactiquePlayed();
            }
            view.afficherCarteJoueeSurBorne(carte, indexBorne);
            int conti = resultat.get(2);
                if (conti == 0){
                    continuer = false;
                }
        }
        

        if (variante != Variante.EXPERT){
            //IA essaye de revendiquer des bornes
            List<Integer> bornesRevendicables = getlistbornesrevendicable(liste_bornes);
            List<Integer> bornesCapturees = joueurIA.revendiquerBorneIA(liste_bornes, bornesRevendicables);
            view.afficherBornesCaptureesParIA(bornesCapturees);
        }

        // Joueur pioche une carte
        //joueurIA.piocheCarte(piochenormal, piochetactique)
        if (variante == Variante.CLASSIQUE){
            joueurIA.getMain().piocherCarte(pioche);
        }
        else {
            while (joueurIA.getMain().getNombreCartes() < 7) {
                boolean choix = joueurIA.choixpioche();
                joueurIA.getMain().piocherCarte(choix ? pioche : piocheTactique);       
            }
        }
        // Fin du tour IA
        view.afficherFinTour(joueurIA);
    }

    private void faireTourJoueurReel(Joueur joueurReel, Pioche pioche, Pioche piocheTactique) {
        // Debut du Tour Joueur
        Joueur joueurAdverse;
        if (liste_joueurs.get(0).equals(joueurReel)) {
            joueurAdverse = liste_joueurs.get(1); // Si joueurReel est le premier, l'adversaire est le deuxième
        } else {
            joueurAdverse = liste_joueurs.get(0); // Sinon, l'adversaire est le premier
        }
        view.afficherJoueurDebutTour(joueurReel, numeroTour);
        if (variante == Variante.EXPERT){
            boolean continuer = true;
            while (continuer) {
                List<Integer> bornesRevendicables = getlistbornesrevendicable(liste_bornes);
                view.afficherEtatTour(numeroTour, joueurReel,liste_joueurs, liste_bornes);
                if (!bornesRevendicables.isEmpty()){
                    view.afficherdemanderevendiquerborne(bornesRevendicables);
                    int answer = InputHandler.inputinlist(bornesRevendicables);
                    if (answer == 0){
                        continuer = false;
                    }
                    else{
                        if (liste_bornes.get(answer).revendiquer(joueurReel)){
                            view.afficherCaptureBorne(joueurReel, answer);
                        }
                        else{
                            System.out.println("echec de la revendication");
                            try {
                                Thread.sleep(1000); // Pause finale pour que le message reste un instant
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }

                    }
                }
                else {
                    System.out.println("Pas de Borne revendicable");
                    continuer = false;
                }
            }
        }

        // Joueur Joue une carte
        boolean continuer = true;
        boolean carteClanPlayed = false;
        while (continuer){
            view.afficherEtatTour(numeroTour, joueurReel, liste_joueurs, liste_bornes);
            view.afficherquellecarteJouer();                                                                                                                                                                                                                                                                                                         
            int indexCarte = InputHandler.demanderEntree( 1, joueurReel.getMain().getNombreCartes())-1;
            System.out.print("Sur quelle Borne ? ");
            List<Integer> bornesjouable = getlistbornesjouable(liste_bornes, joueurReel);
            int indexBorne = InputHandler.inputinlist(bornesjouable);
            if (joueurReel.getMain().getCarte(indexCarte) instanceof CarteTactique) {
                if (joueurReel.getCarteTactiquePlayed() < joueurAdverse.getCarteTactiquePlayed() + 1){
                    joueurReel.incrementerCarteTactiquePlayed(); 
                   // joueurReel.getMain().getCarte(indexCarte).appliquerEffet(joueurReel, liste_bornes.get(indexBorne), false, pioche, piocheTactique);
                    joueurReel.jouerCarte(indexCarte, liste_bornes.get(indexBorne));
                }
                else {
                    //Message
                }
                
            } else {
                if (!carteClanPlayed){
                    carteClanPlayed = true;
                    joueurReel.jouerCarte(indexCarte, liste_bornes.get(indexBorne));
                }
                else{
                    //Message
                }
                
            }
            

            if (variante == Variante.CLASSIQUE){
                continuer = false;
            }
            else if (carteClanPlayed && joueurReel.getCarteTactiquePlayed() > joueurAdverse.getCarteTactiquePlayed() + 1){
                continuer = false;
            }
            else {
                //message continuer
                boolean choix = InputHandler.demanderEntree(0, 1) == 1;
                if (choix){
                    continuer = false;
                }
            }
        }





        
        if (variante != Variante.EXPERT){
            continuer = true;
            while (continuer) {
                List<Integer> bornesRevendicables = getlistbornesrevendicable(liste_bornes);
                view.afficherEtatTour(numeroTour, joueurReel,liste_joueurs, liste_bornes);
                if (!bornesRevendicables.isEmpty()){
                    view.afficherdemanderevendiquerborne(bornesRevendicables);
                    int answer = InputHandler.inputinlist(bornesRevendicables);
                    if (answer == 0){
                        continuer = false;
                    }
                    else{
                        if (liste_bornes.get(answer).revendiquer(joueurReel)){
                            view.afficherCaptureBorne(joueurReel, answer);
                        }
                        else{
                            System.out.println("echec de la revendication");
                            try {
                                Thread.sleep(1000); // Pause finale pour que le message reste un instant
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }

                    }
                }
                else {
                    System.out.println("Pas de Borne revendicable");
                    continuer = false;
                }
            }
        }
        // Joueur pioche une carte
        if (variante == Variante.CLASSIQUE){
            joueurReel.getMain().piocherCarte(pioche);
        }
        else {
            while (joueurReel.getMain().getNombreCartes() < 7) {
                //MESSAGE
                boolean choix = InputHandler.demanderEntree(0, 1) == 1;
                joueurReel.getMain().piocherCarte(choix ? pioche : piocheTactique);
            }
        }
        try {
            Thread.sleep(1000); // Pause finale pour que le message reste un instant
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Fin du tour
        view.afficherFinTour(joueurReel);
    }
    private List<Integer> getlistbornesrevendicable(List<Borne> listeBornes) {
        List<Integer> bornesRevendicables = new ArrayList<>();
        for (Borne borne : listeBornes) {
            if (borne.getEtat() == Borne.Etat.LIBRE && 
                borne.getList(liste_joueurs.get(0)).size() == 3 && 
                borne.getList(liste_joueurs.get(1)).size() == 3) {
                bornesRevendicables.add(borne.id_borne);
            }
        }
        return bornesRevendicables;
    }
    private List<Integer> getlistbornesjouable(List<Borne> listeBornes, Joueur joueur) {
        List<Integer> bornesjouable = new ArrayList<>();
        for (Borne borne : listeBornes) {
            if (borne.getEtat() == Borne.Etat.LIBRE && borne.getList(joueur).size() < 3){
                bornesjouable.add(borne.id_borne);
            }
        }
        return bornesjouable;
    }

    public Joueur verifierConditionVictoire() {
        int bornesCapturéesJ1 = 0;
        int bornesCapturéesJ2 = 0;
        int bornesConsecutivesJ1 = 0;
        int bornesConsecutivesJ2 = 0;
        int maxBornesConsecutivesJ1 = 0;
        int maxBornesConsecutivesJ2 = 0;

        for (Borne borne : liste_bornes) {
            // Vérifie si la borne est capturée par le joueur 1
            if (borne.getEtat() == Borne.Etat.CAPTUREE_J1) {
                bornesCapturéesJ1++;
                bornesConsecutivesJ1++;
                maxBornesConsecutivesJ1 = Math.max(maxBornesConsecutivesJ1, bornesConsecutivesJ1);
                bornesConsecutivesJ2 = 0; // Réinitialise pour joueur 2
            } 
            // Vérifie si la borne est capturée par le joueur 2
            else if (borne.getEtat() == Borne.Etat.CAPTUREE_J2) {
                bornesCapturéesJ2++;
                bornesConsecutivesJ2++;
                maxBornesConsecutivesJ2 = Math.max(maxBornesConsecutivesJ2, bornesConsecutivesJ2);
                bornesConsecutivesJ1 = 0; // Réinitialise pour joueur 1
            } 
            // Aucun joueur ne capture la borne
            else {
                bornesConsecutivesJ1 = 0;
                bornesConsecutivesJ2 = 0;
            }
        }

        // Vérifie les conditions de victoire
        if (bornesCapturéesJ1 >= 5 || maxBornesConsecutivesJ1 >= 3) {
            view.afficherEcranVictoire(liste_joueurs.get(0), liste_joueurs.get(1), 3);
            return liste_joueurs.get(0);
        }
        if (bornesCapturéesJ2 >= 5 || maxBornesConsecutivesJ2 >= 3) {
            view.afficherEcranVictoire(liste_joueurs.get(1), liste_joueurs.get(0), 3);
            return liste_joueurs.get(1);
        }

        return null; // Aucun joueur ne remplit les conditions de victoire
    }
}
