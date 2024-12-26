package main.java.com.schottenTotten.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarteTest {

    @Test
    public void testCarte() {
        Carte carte = new Carte(5, "Rouge");
        
        assertEquals(5, carte.getNumero(), "Le numéro de la carte doit être 5.");
        assertEquals("Rouge", carte.getCouleur(), "La couleur de la carte doit être Rouge.");
    }

    @Test
    public void testCarteWithEdgeValues() {
        Carte carteBasse = new Carte(0, "Bleu");
        assertEquals(0, carteBasse.getNumero(), "Le numéro de la carte doit être 0.");

        Carte carteHaute = new Carte(100, "Vert");
        assertEquals(100, carteHaute.getNumero(), "Le numéro de la carte doit être 100.");
    }

    @Test
    public void testCarteWithDifferentColors() {
        Carte carteRouge = new Carte(3, "Rouge");
        Carte carteBleu = new Carte(7, "Bleu");
        
        assertEquals("Rouge", carteRouge.getCouleur(), "La couleur de la carte doit être Rouge.");
        assertEquals("Bleu", carteBleu.getCouleur(), "La couleur de la carte doit être Bleu.");
    }
}
