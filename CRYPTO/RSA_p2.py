import numpy as np


ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

def enlever_caractere_non_dico(texte):
    """Fonction qui enlève tous les caractères non présents dans l'alphabet

    Args:
        texte (String): message non crypté 

    Returns:
        String: message non crypté en maj sans espace ni caractères spéciaux
    """
    texte = texte.upper()
    for mot in texte:
        if mot not in ALPHABET:
            texte = texte.replace(mot, "")
    return texte


def est_premier(nombre):
    """Fonction qui renvoie True si nombre est premier est false sinon

    Args:
        nombre (int): Un nombre

    Returns:
        bool: True si nombre est premier false sinon
    """
    if nombre == 1 or nombre == 2:
        return True
    if nombre%2 == 0:
        return False
    moitier_nombre = nombre**0.5
    if moitier_nombre == int(moitier_nombre):
        return False
    for x in range(3, int(moitier_nombre), 2):
        if nombre % x == 0:
            return False	
    return True


def genere_premier(maxi):
    """Fonction qui génère un nombre premier aléatoirement plus petit que maxi

    Args:
        maxi (int): Nombre maximum
    """
    res = 0 
    while res == 0:
        i = np.random.choice(maxi,1)
        if est_premier(i):
            res = i
    return res

def test_genere_premier():
    """Fonction qui teste la fonction genere_premier
    """
    essaie_genere = genere_premier(100)
    assert essaie_genere < 100
    assert essaie_genere > 1
    assert est_premier(essaie_genere)
    
    
def pgcd_de(a, b):
    """Fonction qui return le tuple du pgcd de deux nombre

    Args:
        a (int): Un nombre
        b (int): Un nombre

    Returns:
        tuple: le pgdc en indice 0, et en indice 1 et 2 u,v tels que a*u+b*v = r
    """
    r, u, v = a, 1, 0  # r = a, u = 1, v = 0
    rp, up, vp = b, 0, 1 #rprime, uprime, vprime 
    while rp != 0: #tant que rp est différent de 0
        q = r//rp #on calcule le quotient de la division euclidienne de r par rp
        rs, us, vs = r, u, v #on sauvegarde r, u et v
        r, u, v = rp, up, vp #on affecte rp, up et vp à r, u et v
        rp, up, vp = (rs - q*rp), (us - q*up), (vs - q*vp) #on affecte rs - q*rp, us - q*up et vs - q*vp à rp, up et vp
    return (r, u, v) #u et v étants les coefficients de bezout 


def genere_cle():
    """Retourne un dictionnaire contenant la clé privée et la clé publique sous forme de tuples: {priv:(clé privée),pub:(clé publique)}

    Returns:
        dict: Le dictionnaire avec la clé public et privée
    """
    	
    p = genere_premier(1000) #choix d'un nombre premier entre 0 et 1000
    q = genere_premier(1000) #choix d'un nombre premier entre 0 et 1000


    n = p*q  #calcul de n
    m = (p-1)*(q-1) #calcul de m

    #choix d'un nombre premier entre 0 et m
    r = 10 #initialisation de r
    d = 0 #initialisation de d
    while r != 1 or d <= 2 or d >= m: #tant que r est différent de 1 ou que d est inférieur ou égal à 2 ou que d est supérieur ou égal à m
        c = np.random.choice(1000,1) #on choisit un nombre entre 0 et 1000
        r, d, v = pgcd_de(c,m) #on calcule le pgcd de c et m
    n, c, d = int(n), int(c), int(d) #on convertit n, c et d en entier


    return {"priv":(n,c), "pub":(n,d)} #on retourne le dictionnaire contenant la clé privée et la clé publique sous forme de tuples: {priv:(clé privée),pub:(clé publique)}
	



def chiffre(n, c, msg):
    """Fonction permettant de chiffrer en list d'int un message

    Args:
        n (int): n représentant le n de la clé public
        c (int): c représentant le d de la clé public  
        msg (String): Le message non chiffré

    Returns:
        List : La liste des éléments chiffré en int
    """
    asc = [str(ord(j)) for j in enlever_caractere_non_dico(msg)] #on convertit chaque caractère du message en ascii
    for i, k in enumerate(asc): #pour chaque élément de la liste asc
        if len(k) < 3:		#si la longueur de l'élément est inférieur à 3
            while len(k) < 3: #tant que la longueur de l'élément est inférieur à 3
                k = '0' + k #on ajoute un 0 devant l'élément
            asc[i] = k #on affecte l'élément à la liste asc à l'indice i
    ascg = ''.join(asc) #on convertit la liste asc en chaine de caractère
    d , f = 0 , 4 #définition des indices de découpe
    while len(ascg)%f != 0: #tant que la longueur de ascg modulo 4 est différent de 0
        ascg = ascg + '0' #on ajoute un 0 à la fin de ascg
    l = [] #liste des blocs
    while f <= len(ascg): #tant que f est inférieur à la longueur de ascg
        l.append(ascg[d:f]) #on ajoute le bloc de ascg[d:f] à la liste l
        d , f = f , f + 4   #on incrémente d et f de 4
    #chiffrement des groupes
    crypt = [str(((int(i))**c)%n) for i in l] #on chiffre chaque bloc de la liste l
    return crypt #on retourne la liste des blocs chiffrés


def dechiffre(n, d, crypt):
    """Fonction qui dechiffre la liste chiffré

    Args:
        n (int): Représente n de la clé privé
        d (int): Représente c de la clé privé
        crypt (List): Message chiffré

    Returns:
        _type_: _description_
    """
    resultat = [str((int(i)**d)%n) for i in crypt] #on déchiffre chaque bloc de la liste crypt
    for i, s in enumerate(resultat): #pour chaque élément de la liste resultat
        if len(s) < 4: #si la longueur de l'élément est inférieur à 4
            while len(s) < 4: #tant que la longueur de l'élément est inférieur à 4
                s = '0' + s #on ajoute un 0 devant l'élément
            resultat[i] = s #on affecte l'élément à la liste resultat à l'indice i
    g = ''.join(resultat) #on convertit la liste resultat en chaine de caractère
    asci = '' #chaine de caractère vide
    d , f = 0 , 3 #définition des indices de découpe 
    while f < len(g): #tant que f est inférieur à la longueur de g 
        asci = asci + chr(int(g[d:f])) #conversion ascii
        d , f = f , f + 3 #on incrémente d et f de 3 
    return asci #on retourne la chaine de caractère asci


def test_genere_cle():
    cle = genere_cle() #on génère la clé
    assert dechiffre(cle["priv"][0], cle["priv"][1], chiffre(cle["pub"][0], cle["pub"][1], "Bonjour comment ca va ?")) == "BONJOURCOMMENTCAVA"
    assert dechiffre(cle["priv"][0], cle["priv"][1], chiffre(cle["pub"][0], cle["pub"][1], "Moi ca va pas mal")) == "MOICAVAPASMAL"
    assert dechiffre(cle["priv"][0], cle["priv"][1], chiffre(cle["pub"][0], cle["pub"][1], "Je suis en train de faire un exercice de cryptographie")) == "JESUISENTRAINDEFAIREUNEXERCICEDECRYPTOGRAPHIE"

