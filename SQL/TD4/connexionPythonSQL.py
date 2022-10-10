from unittest import result
import sqlalchemy  
# pour avoir sqlalchemy :
# sudo apt-get update 
# sudo apt-get install python3-sqlalchemy
# pip3 install mysql-connector-python

import getpass  # pour faire la lecture cachée d'un mot de passe

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


if __name__ == "__main__":
    # login=input("login MySQL ")
    # passwd=getpass.getpass("mot de passe MySQL ")
    # serveur=input("serveur MySQL ")
    # bd=input("nom de la base de données ")
    cnx=ouvrir_connexion("saada","saada","servinfo-mariadb","DBsaada")
    # ici l'appel des procédures et fonctions
    print(max_ref(cnx))
    print(get_article(cnx,1))
    print(max_article_ref(cnx))
    print(liste_article(cnx))
    cnx.close()
