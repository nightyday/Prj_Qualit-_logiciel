# Prj_Qualit-_logiciel

Projet mettant l'accent sur la qualité logicielle. 
Le rapport, qui décrit et exlique les choix fait : https://docs.google.com/document/d/1t8PTeN6bwYNIYLe2YB5lUcUbZpwZoBXpLl1Q4o1VN3A/edit

# Sujet
## Résumé
Vous devez développer une solution qui permet la gestion des emprunts du matériel informatique de la société locaMat. Cette solution doit nous permettre de gérer les emprunts de matériels informatiques (Téléphone, tablette, PC).

## Périmètre
Cette application web permettra de gérer les emprunts de notre matériel informatique. Ils pourront donc consulter, modifier, supprimer, ajouter ou réserver le matériel.

## Impact
L’application sera utilisée pour un usage interne et sera mise à disposition de tous nos collaborateurs avec des rôles différents (administrateurs, emprunteurs).

## Contraintes
Tous les utilisateurs n’auront pas les mêmes accès et les mêmes droits.

## Utilisateurs de l’application
Les principaux utilisateurs de l’application seront les collaborateurs de la société locaMat. Toutefois, certains collaborateurs auront plus de droits que d’autres. En effet l’administrateur pourra :
- Créer un nouveau matériel informatique
- Modifier un matériel informatique
- Supprimer un matériel informatique
- Consulter un matériel informatique
- Détail d’un utilisateur
- Créer un compte utilisateur (administrateur ou emprunteur)
- Modifier un utilisateur
- Réservation de matériel
Fonctionnalité optionnelle :
- Notification/Alerte sur l’emprunt du matériel

## Contexte technique
L’application devra fonctionner sur un ordinateur avec le navigateur chrome version minimum : 86.0.
Les données de l’application peuvent être stockées dans un fichier ou un SGBD.
La charte graphique est libre.

# Utilisation
Le projet est un projet maven/java est à été développé avec l'IDE VsCode, sous les versions suivantes : versions du JDK (21.0.2) et de Maven (3.9.6).

## Run  
Le fichier **App.java** contient la méthode main à lancer. Normalement, des données sont déjà initialisées dans les fichiers **.json**. Si ce n'est pas le cas, la méthode **configureData** peut être ajoutée au début de la méthode **main** (il faut que le fichier **passwords.json** soit initialisé avec *{}* pour que la sauvegarde de mot de passe se fasse correctement).  
Les mots de passe seront donc *password* + *a* pour les administrateurs ou *u* pour les utilisateurs + l'ID de la personne.  
*Exemple : Un utilisateur d'ID 7 aura le mot de passe **passwordu7***

##

_Zoé Casteret, Arthur Crochemore, Valentin Desmares, Adrien Amoroso, Louis Soulier et Douae Benabbou_
