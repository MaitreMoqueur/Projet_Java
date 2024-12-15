package com.schottenTotten.controller;

import java.util.List;

import com.schottenTotten.model.*;
import com.schottenTotten.view.ConsoleView;
import com.schottenTotten.ai.*;

public class GestionPartie {
    private final List<Joueur> joueurs;
    private final ConsoleView view;
    private boolean partieEnCours;
    private int numeroTour;

    public GestionPartie(List<Joueur> joueurs, ConsoleView view) {
        this.joueurs = joueurs;
        this.view = view;
        this.partieEnCours = true;
    }

    public void demarrerPartie() {
        view.afficherLancementPartie();
        while (partieEnCours) {
            for (Joueur joueur : joueurs) {
                faireTourJoueur(joueur);
                if (verifierConditionVictoire()) {
                    partieEnCours = false;
                    break;
                }
            }
            numeroTour++;
        }
        annoncerFinPartie();
    }

    private void faireTourJoueur(Joueur joueur) {
        view.afficherTourJoueur(joueur);

        if (joueur instanceof IA) {
            faireTourIA(joueur);
        } else {
            faireTourJoueurReel(joueur);
        }
    }

    private void faireTourIA(IA joueurIA) {
        view.afficherIATour(joueurIA);
        //Jouer carte IA
        view.afficherCarteJoueeSurBorne();
        //faire tour IA
        view.afficherBornesCaptureesParIA();

        view.afficherFinTour(joueurIA);
    }

    private void faireTourJoueurReel(Joueur joueurReel) {
        view.AfficherJoueurDebutTour(joueurReel);
        view.afficherEtatTour();
        view.afficherquellecarteJouer();                                                                                                                                                                                                                                                                                                         
        joueurReel.jouerCarte();
        //afficher carte jouée dans jouer.carte
        boolean continuer = true;
        while (continuer) {
            view.afficherEtatTour();
            view.afficherdemanderevendiquerborne();
            if (view.demanderSiRevendication()) {
                int borne = view.demanderBorne();
                view.revendiquerBorne(joueurReel, borne);
                //afficher message adequate dans revendiquerBorne
            } else {
                continuer = false;                                      
            }
        }

        joueurReel.piocherCarte();
        //afficher la cartepiochée dans piocher carte;
        view.afficherFinTour();
    }


    private void annoncerFinPartie() {
        view.afficherfindepartie();
        // relancer ?
        // fermer jeu
        // Menu 
    }

    public Joueur verifierConditionVictoire(List<Borne> bornes, Joueur joueur1, Joueur joueur2) {
        int bornesCapturéesJ1 = 0;
        int bornesCapturéesJ2 = 0;
        int bornesConsecutivesJ1 = 0;
        int bornesConsecutivesJ2 = 0;
        int maxBornesConsecutivesJ1 = 0;
        int maxBornesConsecutivesJ2 = 0;

        for (Borne borne : bornes) {
            // Vérifie si la borne est capturée par le joueur 1
            if (borne.getEtat() == Borne.Etat.REVENDIQUE_J1) {
                bornesCapturéesJ1++;
                bornesConsecutivesJ1++;
                maxBornesConsecutivesJ1 = Math.max(maxBornesConsecutivesJ1, bornesConsecutivesJ1);
                bornesConsecutivesJ2 = 0; // Réinitialise pour joueur 2
            } 
            // Vérifie si la borne est capturée par le joueur 2
            else if (borne.getEtat() == Borne.Etat.REVENDIQUE_J2) {
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
            view.afficherEcranVictoire(joueur1);
            return joueur1;
        }
        if (bornesCapturéesJ2 >= 5 || maxBornesConsecutivesJ2 >= 3) {
            view.afficherEcranVictoire(joueur2);
            return joueur2;
        }

        return null; // Aucun joueur ne remplit les conditions de victoire
    }
}
