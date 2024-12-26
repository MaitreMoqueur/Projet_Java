package main.java.com.schottenTotten.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class JoueurTest {

    private Joueur joueur;
    private Pioche pioche;

    @BeforeEach
    public void setUp() {
        pioche = new Pioche();
        joueur = new Joueur("TestPlayer", pioche);
    }

    @Test
    public void testConstructor() {
        assertEquals("TestPlayer", joueur.getPseudo(), "Le pseudo du joueur doit être correct.");
        assertEquals(0, joueur.getScore(), "Le score initial du joueur doit être 0.");
        assertNotNull(joueur.getMain(), "La main du joueur ne doit pas être nulle.");
        assertTrue(joueur.getBornes().isEmpty(), "Le joueur ne doit pas avoir de bornes gagnées au départ.");
    }

    @Test
    public void testVictoire() {
        joueur.victoire();
        assertEquals(1, joueur.getScore(), "Le score du joueur devrait être 1 après une victoire.");
    }

    @Test
    public void testRevendiquerBorne() {
        Borne borne = new Borne(1);
        Joueur autreJoueur = new Joueur("AutrePlayer", pioche);

        boolean revendication = joueur.revendiquerBorne(borne, joueur);
        assertTrue(revendication, "Le joueur devrait pouvoir revendiquer une borne.");
        assertTrue(joueur.getBornes().contains(1), "La borne revendiquée devrait être ajoutée à la liste des bornes du joueur.");
    }

    @Test
    public void testJouerCarte() {
        int initialNombreCartes = joueur.getMain().getNombreCartes();
        
        Carte carteJouee = joueur.jouerCarte(0, new Borne(1));
        assertNotNull(carteJouee, "La carte jouée ne doit pas être nulle.");
        
        assertEquals(initialNombreCartes - 1, joueur.getMain().getNombreCartes(), "Le nombre de cartes du joueur devrait avoir diminué de 1.");
    }

    @Test
    public void testJouerCarteIndexInvalide() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            joueur.jouerCarte(-1, new Borne(1)); // Test d'un index négatif
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            joueur.jouerCarte(100, new Borne(1)); // Test d'un index trop grand
        });
    }
}