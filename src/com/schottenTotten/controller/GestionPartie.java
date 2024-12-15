package com.schottenTotten.controller;

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
        afficherLancementPartie(;)
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
        // Logique spécifique à l'IA
        //faire tour IA

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
            afficherEtatTour()
            afficherdemanderevendiquerborne()
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

    private boolean verifierConditionsVictoire() {
        //a faire
        // affichage
    }

    private void annoncerFinPartie() {
        afficherfindepartie();
        // relancer ?
        // fermer jeu
        // Menu 
    }
}
