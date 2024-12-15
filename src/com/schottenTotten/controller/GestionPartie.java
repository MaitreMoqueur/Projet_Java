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
        view.afficherMessage(joueurIA.getNom() + " réfléchit...");
        // Logique spécifique à l'IA
    }

    private void faireTourJoueurReel(JoueurReel joueurReel) {
        view.afficherMessage(joueurReel.getNom() + ", c'est à vous de jouer !");
        joueurReel.jouerCarte(view.demanderCarteAJouer());

        boolean continuer = true;
        while (continuer) {
            if (view.demanderSiRevendication()) {
                int borne = view.demanderBorne();
                if (!revendiquerBorne(joueurReel, borne)) {
                    view.afficherMessage("Revendication échouée !");
                }
            } else {
                continuer = false;
            }
        }

        joueurReel.piocherCarte();
        view.afficherMessage("Vous avez pioché une carte.");
    }

    private boolean revendiquerBorne(JoueurReel joueur, int borne) {
        if (borne < 1 || borne > 9) {
            view.afficherMessage("Numéro de borne invalide !");
            return false;
        }

        boolean revendicationReussie = joueur.revendiquerBorne(borne);
        if (revendicationReussie) {
            view.afficherMessage("Revendication réussie !");
        }
        return revendicationReussie;
    }

    private boolean verifierConditionsVictoire() {
        for (Joueur joueur : gestionJoueurs.getJoueurs()) {
            if (joueur.getScore() >= 5) {
                view.afficherVictoire(joueur.getNom());
                return true;
            }
        }
        return false;
    }

    private void annoncerFinPartie() {
        view.afficherFinPartie(gestionJoueurs.getJoueurs());
    }
}
