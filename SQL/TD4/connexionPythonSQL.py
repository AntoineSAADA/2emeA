from pickle import TRUE
from unittest import result
from Article import *
from Entrepot import *
from Stocker import *
from numpy import deprecate
import sqlalchemy  
# pour avoir sqlalchemy :
# sudo apt-get update 
# sudo apt-get install python3-sqlalchemy
# pip3 install mysql-connector-python

import getpass

from Article import Article  # pour faire la lecture cachée d'un mot de passe

def ouvrir_connexion(user,passwd,host,database):
    """
    ouverture d'une connexion MySQL
    paramètres:
       user     (str) le login MySQL de l'utilsateur
       passwd   (str) le mot de passe MySQL de l'utilisateur
       host     (str) le nom ou l'adresse IP de la machine hébergeant le serveur MySQL
       database (str) le nom de la base de données à utiliser
    résultat: l'objet qui gère le connection MySQL si tout s'est bien passé
    """
    try:
        #creation de l'objet gérant les interactions avec le serveur de BD
        engine=sqlalchemy.create_engine('mysql+mysqlconnector://'+user+':'+passwd+'@'+host+'/'+database)
        #creation de la connexion
        cnx = engine.connect()
    except Exception as err:
        print(err)
        raise err
    print("connexion réussie")
    return cnx

def max_ref(connexion):
    resultat = connexion.execute("select max(reference) from ARTICLE")
    return resultat.first().values()

def get_article(connexion,numero):
    resultat=connexion.execute("select libelle,prix from ARTICLE where reference =%s",numero)
    for (nom,prix) in resultat:
        return (numero,nom,prix)

def max_article_ref(connexion):
    resultat = connexion.execute("select libelle,max(reference) from ARTICLE")
    return resultat.first().values()

def liste_article(connexion):
    resultat=connexion.execute("select reference,libelle,prix from ARTICLE")
    return resultat.fetchall()

def liste_entrepot_trie_departement(connexion):
    resultat=connexion.execute("select * from ENTREPOT order by departement")
    departement_actuelle = ""
    nb_entrepot = 0
    for (code,nom,departement) in  resultat.fetchall():
        # Séparer les différents départements
        if departement_actuelle != departement:
            if nb_entrepot != 0 :
                print(str(nb_entrepot) + " au total dans " + departement_actuelle)
                nb_entrepot = 0
            print("\n----------------------")
            print("Département du ", departement)
            print("----------------------")
            departement_actuelle = departement
        elif departement_actuelle == "":
            departement_actuelle = departement
        print("L'entrepot de code "+ str(code)+ " à " + nom + " est situé dans le département du " + departement)
        nb_entrepot += 1
    print(str(nb_entrepot) + " au total dans " + departement_actuelle)


def liste_entrepot_ayant_article(connexion,numArticle):
    resultat = connexion.execute("select code,nom, quantite from ENTREPOT natural join STOCKER natural join ARTICLE where reference = %d"%numArticle)
    return resultat.fetchall()

def liste_article_dans_entrepot(connexion,numEntrepot):
    resultat = connexion.execute("SELECT code,libelle, quantite FROM ENTREPOT natural join STOCKER natural join ARTICLE where code = %d;"%numEntrepot)    
    return resultat.fetchall()
def valeur_entrepot(connexion,numEntrepot):
    resultat = connexion.execute("SELECT code, sum(prix*quantite) FROM ENTREPOT natural join STOCKER natural join ARTICLE where code = %d group by code;"%numEntrepot)    
    return resultat.fetchall()[0][1]

def ajouter_modifier_article(connexion,article:Article):
    resultat = connexion.execute("select reference,libelle from ARTICLE where reference = %d"%article.reference)
    if resultat.rowcount == 0:
        connexion.execute("insert into ARTICLE values (%d,'%s',%f)"%(article.reference,article.libelle,article.prix))
        print("Article ajouté")
    else:
        if article.libelle == resultat.fetchall()[0][1]:
            connexion.execute("update ARTICLE set prix = %f where reference = %d"%(article.prix,article.reference))
            print("Le prix de l'article a été modifié")
        else:
            print("L'article existe déjà avec un autre nom")

def ajouter_entrepot(connexion,entrepot:Entrepot):
    # Ajoute un nouvelle entrepot si aucun entrepot a le même nom et si il y a moins de 3 entrepots dans le même département
    resultat = connexion.execute("select nom,departement from ENTREPOT where nom = '%s' and departement = '%s'"%(entrepot.nom,entrepot.departement))
    if resultat.rowcount == 0:
        # Verifier que le même identifiant n'est pas utilisé 
        resultat = connexion.execute("select code from ENTREPOT where code = %d"%entrepot.code)
        if resultat.rowcount != 0:
            print("L'identifiant de l'entrepot est déjà utilisé")
        else:
            resultat = connexion.execute("select nom,departement from ENTREPOT where departement = '%s'"%entrepot.departement)
            if resultat.rowcount < 3:
                connexion.execute("insert into ENTREPOT values (%d,'%s','%s')"%(entrepot.code,entrepot.nom,entrepot.departement))
                print("Entrepot ajouté")
            else:
                print("Il y a déjà 3 entrepots dans ce département")

def entrer_stock(connexion,refA:int,refE:int,qte:int):
    # Augmente les stocks de l'article refA dans l'entrepot refE de qte quantité
    resultat = connexion.execute("SELECT * FROM ENTREPOT WHERE code = %d"%refE)
    if resultat.rowcount == 0:
        print("L'entrepot n'existe pas")
    else:
        resultat = connexion.execute("SELECT * FROM ARTICLE WHERE reference = %d"%refA)
        if resultat.rowcount == 0:
            print("L'article n'existe pas")
        else:
            resultat = connexion.execute("select quantite from STOCKER where reference = %d and code = %d"%(refA,refE))
            if resultat.rowcount == 0:
                connexion.execute("insert into STOCKER values (%d,%d,%d)"%(refA,refE,qte))
                print("Stock ajouté")
            else:
                connexion.execute("update STOCKER set quantite = quantite + %d where reference = %d and code = %d"%(qte,refA,refE))
                print("Stock mis à jour")
            
def sortir_stock(connexion,refA:int,refE:int,qte:int):
    # Diminue les stocks de l'article refA dans l'entrepot refE de qte quantité
    resultat = connexion.execute("SELECT * FROM ENTREPOT WHERE code = %d"%refE)
    if resultat.rowcount == 0:
        print("L'entrepot n'existe pas")
    else:
        resultat = connexion.execute("SELECT * FROM ARTICLE WHERE reference = %d"%refA)
        if resultat.rowcount == 0:
            print("L'article n'existe pas")
        else:
            # Si la quantité est supérieur à la quantité en stock sortir le maximum possible
            resultat = connexion.execute("select quantite from STOCKER where reference = %d and code = %d"%(refA,refE))
            if resultat.rowcount == 0:
                print("L'article n'est pas en stock dans l'entrepot")
            else:
                quantite = resultat.fetchall()[0][0]
                if quantite < qte:
                    qte = quantite
                connexion.execute("update STOCKER set quantite = quantite - %d where reference = %d and code = %d"%(qte,refA,refE))
                print("Stock mis à jour, il y en a maintenant " + str(quantite-qte) + " en stock")


if __name__ == "__main__":
    # login=input("login MySQL ")
    # passwd=getpass.getpass("mot de passe MySQL ")
    # serveur=input("serveur MySQL ")
    # bd=input("nom de la base de données ")
    cnx=ouvrir_connexion("saada","saada","localhost","DBsaada")
    # ici l'appel des procédures et fonctions
    # print(max_ref(cnx))
    # print(get_article(cnx,1))
    # print(max_article_ref(cnx))
    # print(liste_article(cnx))
    # liste_entrepot_trie_departement(cnx)
    # print(liste_entrepot_ayant_article(cnx,1))
    # print(liste_article_dans_entrepot(cnx,1))
    # print(valeur_entrepot(cnx,1))
    # article = Article(1,"Chaise",1.0)
    # ajouter_modifier_article(cnx,article)
    # entrepot = Entrepot(4,"test","Cher")
    # ajouter_entrepot(cnx,entrepot)
    # # entrer_stock(cnx,1,1,51)
    # sortir_stock(cnx,1,1,51)

    continuer = True
    while continuer:
        print("------------------------------------------")
        print("|            Menu d'utilsation           |")
        print("------------------------------------------")
        print("|1 - Afficher l'id max de Article        |")
        print("|2 - Afficher l'Article pour un ID donné |")
        print("|3 - Afficher l'article de l'ID Max      |")
        print("|4 - Afficher la liste des articles      |")
        print("|5 - Afficher la liste des entrepots trié|")
        print("|6 - Afficher la liste des entrepots qui |")
        print("|   contiennent un article donné         |")
        print("|7 - Afficher la liste des articles dans |")
        print("|   un entrepot donné                    |")
        print("|8 - Afficher la valeur d'un entrepot    |")
        print("|9 - Ajouter un article                  |")
        print("|10 - Ajouter un entrepot                |")
        print("|11 - Entrer du stock                    |")
        print("|12 - Sortir du stock                    |")
        print("|13 - Quitter                            |")
        print("------------------------------------------")
        choix = int(input("Choix : "))
        if choix == 1:
            print(max_ref())
        elif choix == 2:
            print(get_article(cnx,int(input("ID : "))))
        elif choix == 3:
            print(max_article_ref(cnx))
        elif choix == 4:
            print(liste_article(cnx))
        elif choix == 5:
            liste_entrepot_trie_departement(cnx)
        elif choix == 6:
            print(liste_entrepot_ayant_article(cnx,int(input("ID : "))))
        elif choix == 7:
            print(liste_article_dans_entrepot(cnx,int(input("ID : "))))
        elif choix == 8:
            print(valeur_entrepot(cnx,int(input("ID : "))))
        elif choix == 9:
            ref = int(input("Reference : "))
            nom = input("Nom : ")
            prix = float(input("Prix : "))
            article = Article(ref,nom,prix)
            ajouter_modifier_article(cnx,article)
        elif choix == 10:
            code = int(input("Code : "))
            nom = input("Nom : ")
            departement = input("Departement : ")
            entrepot = Entrepot(code,nom,departement)
            ajouter_entrepot(cnx,entrepot)
        elif choix == 11:
            refA = int(input("Reference de l'article : "))
            refE = int(input("Reference de l'entrepot : "))
            qte = int(input("Quantité : "))
            entrer_stock(cnx,refA,refE,qte)
        elif choix == 12:
            refA = int(input("Reference de l'article : "))
            refE = int(input("Reference de l'entrepot : "))
            qte = int(input("Quantité : "))
            sortir_stock(cnx,refA,refE,qte)
        elif choix == 13:
            continuer = False
        else:
            print("Choix invalide")
        print("Appuyer sur une touche pour continuer")
        input()


    cnx.close()


