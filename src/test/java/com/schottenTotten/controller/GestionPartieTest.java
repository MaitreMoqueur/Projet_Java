package main.java.com.schottenTotten.controller;

import main.java.com.schottenTotten.model.*;
import main.java.com.schottenTotten.view.ConsoleView;
import main.java.com.schottenTotten.ai.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GestionPartieTest {

    private GestionPartie gestionPartie;
    private ConsoleView view;
    private List<Joueur> joueurs;
    private List<Borne> bornes;
    private Pioche pioche;

    @BeforeEach
    public void setUp() {
        view = new ConsoleView();

        pioche = new Pioche();

        joueurs = new ArrayList<>();
        joueurs.add(new Joueur("Joueur1", pioche));
        joueurs.add(new Joueur("Joueur2", pioche));
        
        bornes = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            bornes.add(new Borne("Joueur1", "Joueur2", i));
        }

        Variante variante = Variante.CLASSIQUE;
        
        gestionPartie = new GestionPartie(joueurs, bornes, view, variante);
    }

    @Test
    public void testVérifierConditionVictoire_aucungagnant() {
        bornes.get(0).setEtat(Borne.Etat.CAPTUREE_J1);
        bornes.get(1).setEtat(Borne.Etat.CAPTUREE_J2);

        Joueur gagnant = gestionPartie.verifierConditionVictoire();

        assertNull(gagnant, "Il ne devrait pas y avoir de gagnant.");
    }

    @Test
    public void testVérifierConditionVictoire_J1_consecutif() {
        bornes.get(0).setEtat(Borne.Etat.CAPTUREE_J1);
        bornes.get(1).setEtat(Borne.Etat.CAPTUREE_J1);
        bornes.get(2).setEtat(Borne.Etat.CAPTUREE_J1);

        Joueur gagnant = gestionPartie.verifierConditionVictoire();

        assertNotNull(gagnant, "Il devrait y avoir gagnant.");
        assertEquals("Joueur1", gagnant.getPseudo());
    }

    @Test
    public void testVérifierConditionVictoire_J1_5bornes() {
        bornes.get(0).setEtat(Borne.Etat.CAPTUREE_J1);
        bornes.get(1).setEtat(Borne.Etat.CAPTUREE_J1);
        bornes.get(2).setEtat(Borne.Etat.CAPTUREE_J1);
        bornes.get(4).setEtat(Borne.Etat.CAPTUREE_J1);
        bornes.get(5).setEtat(Borne.Etat.CAPTUREE_J1);

        Joueur gagnant = gestionPartie.verifierConditionVictoire();

        assertNotNull(gagnant, "Il devrait y avoir gagnant.");
        assertEquals("Joueur1", gagnant.getPseudo());
    }
}
