 Projet Schotten-Totten en Java
Réalisé en Java par Mocquery Philippe & Flo ?
PG220
Encadré Par 

Date de rendue : 

Registre des fonctionnalitées : 

1 - Parlons de l'interface :
    Interface console possible Mais semble bofbofbof
    A voir si on a le temps mais possibilité de faire un truc plus clean


Deroulement du Programme :



Lancement du jeu 

    Menu Principale

        Jouer
        Reglages
        Credits
        Historique
        Quitter

    Reglages : Les reglages de bases sont parfait : pas de modification a faire
    Credits : Random Bullshit
    Quitter : Ferme l'application 
    Historique : Bah non ?
    
        Lancer  
        Variantes
        Mode
       
    Mode : 
        Jouer en ligne : Coming soon (#Crossover reseau)
        Solo : de base si 1 seul joueur humain
            Nom Joueur. 
            Role : Aleatoire / Defensif/ Attaquant
            Difficulté IA
        Spectateur : 2 IA
            Difficulté IA 1
            Difficulté IA 2
        Pass & Play : 2 Joueurs
            Nom Joueurs 1
            role 
            Nom Joueurs 2 
            role (predefinis)
    Variantes :
        variante de base
        variante tactique



Debut du jeu

Joueur Attaquant :
    -Affiche changement
    -Pioche
    Choisir Carte -> Ou la mettre -> valider -> pass tour


Joueur defenseur :
    -Affiche changement
    -Pioche
    Choisir Carte > Ou la mettre -> valider -> pass tour
    Jouer Jeton Huile -> ou le mettre - effet 

Realisation du tour 
    Check if attaques -> oui -> check who win
    check fin de jeu - si oui - ecran fin
    Tour suivant


Structure 

    Entité
        -Role
        -Nom
        -Cartes en Main
        -nbr jeton huile
        * Joueur    
        * IA
            -difficulté
        < Jouer Carte
        < Jouer jeton Huile
        
    
    Muraille
        - Carte defensive
        - Carte attaquante
        - identifiant
        - Detruite ?
        * Type 
            (nombre de vie)
            (regles active)

    Pioche 

    Cartes 
    


Liste des fonctions necessaire ?

Jouer Carte
Jouer Jeton Huile
Piocher Carte 
Check_Combat
Check_victoire

Setup Jeu






    

