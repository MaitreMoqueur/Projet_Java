package main.java.com.schottenTotten.ai;

import main.java.com.schottenTotten.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IATest {
    private IA iaFacile;
    private IA iaMoyen;
    private IA iaDifficile;
    private Pioche pioche;
    private List<Borne> bornes;
    private List<Integer> bornesJouables;
    private List<Integer> bornesRevendicables;

    @BeforeEach
    public void setUp() {
        pioche = new Pioche();

        iaFacile = new IA(1, "IA Facile", pioche);
        iaMoyen = new IA(2, "IA Moyen", pioche);
        iaDifficile = new IA(3, "IA Difficile", pioche);

        bornes = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            bornes.add(new Borne("IA Facile", "IA Moyen", i));
        }

        bornesJouables = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            bornesJouables.add(i);
        }

        bornesRevendicables = new ArrayList<>();
    }

    @Test
    public void testJouerCarteIA_Facile() {
        List<Integer> choix = iaFacile.jouerCarteIA(bornes, bornesJouables);

        assertNotNull(choix);
        assertEquals(2, choix.size());
        assertTrue(choix.get(0) >= 0 && choix.get(0) < iaFacile.getMain().getNombreCartes());
        assertTrue(bornesJouables.contains(choix.get(1)));
    }

    @Test
    public void testJouerCarteIA_Moyen() {
        List<Integer> choix = iaMoyen.jouerCarteIA(bornes, bornesJouables);

        assertNotNull(choix);
        assertEquals(2, choix.size());
        assertTrue(choix.get(0) >= 0 && choix.get(0) < iaMoyen.getMain().getNombreCartes());
        assertTrue(bornesJouables.contains(choix.get(1)));
    }

    @Test
    public void testJouerCarteIA_Difficile() {
        assertThrows(UnsupportedOperationException.class, () -> iaDifficile.jouerCarteIA(bornes, bornesJouables));
    }

    @Test
    public void testRevendiquerBorneIA_AucuneBorneRevendicable() {
        List<Integer> bornesCapturees = iaFacile.revendiquerBorneIA(bornes, bornesRevendicables);
        assertTrue(bornesCapturees.isEmpty());
    }

    @Test
    public void testRevendiquerBorneIA_UneBorneRevendicable() {
        Borne borneRevendicable = bornes.get(0);
        borneRevendicable.ajouterCarte(iaFacile, new Carte(5, "Rouge"));
        borneRevendicable.ajouterCarte(iaFacile, new Carte(6, "Rouge"));
        borneRevendicable.ajouterCarte(iaFacile, new Carte(7, "Rouge"));

        //On prend par exemple iaMoyen comme adversaire pour pouvoir revendiquer une borne
        borneRevendicable.ajouterCarte(iaMoyen, new Carte(1, "Rouge"));
        borneRevendicable.ajouterCarte(iaMoyen, new Carte(2, "Vert"));
        borneRevendicable.ajouterCarte(iaMoyen, new Carte(3, "Bleu"));

        bornesRevendicables.add(0);

        List<Integer> bornesCapturees = iaFacile.revendiquerBorneIA(bornes, bornesRevendicables);

        assertEquals(1, bornesCapturees.size(), "Une seule borne devrait être capturée.");
        assertTrue(bornesCapturees.contains(0), "L'indice de la borne capturée devrait être 0.");
        assertEquals(Borne.Etat.CAPTUREE_J1, borneRevendicable.getEtat(), "La borne revendiquée devrait être capturée par le joueur 1.");
    }


    @Test
    public void testJouerCarteIA_ExceptionQuandMainVide() {
        iaFacile.getMain().getCartes().clear();

        assertThrows(IllegalStateException.class, () -> iaFacile.jouerCarteIA(bornes, bornesJouables));
    }

    @Test
    public void testBorneCible_MiseAJour() {
        iaMoyen.jouerCarteIA(bornes, bornesJouables);

        int borneCible = bornesJouables.stream().findFirst().orElse(-1);
        assertTrue(bornesJouables.contains(iaMoyen.jouerCarteIA(bornes, bornesJouables).get(1)));
    }
}
