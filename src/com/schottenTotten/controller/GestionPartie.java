package com.schottenTotten.controller;

import java.util.List;

import com.schottenTotten.model.Joueur;
import com.schottenTotten.model.JoueurIA;
import com.schottenTotten.model.JoueurReel;
import com.schottenTotten.view.ConsoleView;

public class GestionPartie {
    private final GestionJoueurs gestionJoueurs;
    private final ConsoleView view;
    private boolean partieEnCours;

    public GestionPartie(GestionJoueurs gestionJoueurs, ConsoleView view) {
        this.gestionJoueurs = gestionJoueurs;
        this.view = view;
        this.partieEnCours = true;
    }

    public void demarrerPartie() {
        afficherLancementPartie();
        while (partieEnCours) {
            for (Joueur joueur : gestionJoueurs.getJoueurs()) {
                faireTourJoueur(joueur);
                if (verifierConditionsVictoire()) {
                    partieEnCours = false;
                    break;
                }
            }
        }
        annoncerFinPartie();
    }

    private void faireTourJoueur(Joueur joueur) {
        view.afficherTourJoueur(joueur);

        if (joueur instanceof JoueurIA) {
            faireTourIA((JoueurIA) joueur);
        } else {
            faireTourJoueurReel((JoueurReel) joueur);
        }
    }

    private void faireTourIA(JoueurIA joueurIA) {
        afficherIATour(joueurIA);
        //Jouer carte IA
        afficherCarteJoueeSurBorne();
        //faire tour IA
        afficherBornesCaptureesParIA();

        afficherFinTour(joueurIA);
    }

    private void faireTourJoueurReel(JoueurReel joueurReel) {
        AfficherJoueurDebutTour(joueurReel);
        afficherEtatTour();
        afficherquellecarteJouer();                                                                                                                                                                                                                                                                                                         
        joueurReel.jouerCarte(view.demanderCarteAJouer());
        //afficher carte jouée dans jouer.carte
        boolean continuer = true;
        while (continuer) {
            afficherEtatTour();
            afficherdemanderevendiquerborne();
            if (view.demanderSiRevendication()) {
                int borne = view.demanderBorne();
                revendiquerBorne(joueurReel, borne);
                //afficher message adequate dans revendiquerBorne
            } else {
                continuer = false;                                      
            }
        }

        joueurReel.piocherCarte();
        //afficher la cartepiochée dans piocher carte;
        afficherFinTour();
    }


    private void annoncerFinPartie() {
        afficherfindepartie();
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
            afficherEcranVictoire(joueur1);
            return joueur1;
        }
        if (bornesCapturéesJ2 >= 5 || maxBornesConsecutivesJ2 >= 3) {
            afficherEcranVictoire(joueur2);
            return joueur2;
        }

        return null; // Aucun joueur ne remplit les conditions de victoire
    }
}
