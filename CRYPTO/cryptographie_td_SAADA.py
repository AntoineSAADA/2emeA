import string

ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
DICT_FREQ= {'A':8.13,'B':0.93,'C':3.15,'D':3.55,'E':15.1,'F':0.96,
            'G':0.97,'H':1.08,'I':6.94,'J':0.71,'K':0.16,'L':5.68,
            'M':3.23,'N':6.42,'O':5.27,'P':3.03,'Q':0.89,'R':6.43,
            'S':7.91,'T':7.11,'U':6.05,'V':1.83,'W':0.04,'X':0.42,
            'Y':0.19,'Z':0.21}

def cesar(texte:string,cle:int):
    res = ""
    for c in texte:
        c = c.upper()
        if c in ALPHABET:
            res += ALPHABET[(ALPHABET.index(c)+cle)%26]
        else:
            res += c
    return res


def frequences(chaine):
    freq = [0] * 26
    for c in chaine:
        if c in string.ascii_uppercase:
            freq[ord(c) - ord('A')] += 1
    somme=sum(freq)
    freq=[v / somme * 1000.0 for v in freq]
    return freq

def cherche_cle_cesar(chaine):
    francais = [942, 102, 264, 339, 1587, 95, 104, 77, 841, 89, 0, 534, 324, 715, 514, 286, 106, 646, 790, 726, 624, 215, 0, 30, 24, 32]
    corr = [0] * 26 
    for dec in range(26):
        res = frequences(cesar(chaine, -dec))
        corr[dec] = sum(a * b for a, b in zip(res,francais))
    return corr.index(max(corr))

def autocesar(chaine):
    cle = cherche_cle_cesar(chaine)
    print("Clé : ",cle)
    return cesar(chaine, -cle)



def vigenere():
    """Cryptage de vigenere"""
    input_text = input("Entrez le texte à crypter : ")
    input_cle = input("Entrez la clé : ")
    texte = input_text.upper()
    cle = input_cle.upper()
    alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    resultat = ""
    i_cle = 0
    for i in range(len(texte)):
        if texte[i] in alphabet:
            if i_cle == len(cle):
                i_cle = 0
            resultat += alphabet[(alphabet.index(texte[i]) + alphabet.index(cle[i_cle % len(cle)])) % len(alphabet)]
            i_cle += 1
            
        else:
            resultat += texte[i]
    return resultat

# Décryptage de vigenere
def vigenere_inverse():
    """Décryptage de vigenere"""
    input_text = input("Entrez le texte à décrypter : ")
    input_cle = input("Entrez la clé : ")
    texte = input_text.upper()
    cle = input_cle.upper()
    alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    resultat = ""
    i_cle = 0
    for i in range(len(texte)):
        if texte[i] in alphabet:
            if i_cle == len(cle):
                i_cle = 0
            resultat += alphabet[(alphabet.index(texte[i]) - alphabet.index(cle[i_cle % len(cle)])) % len(alphabet)]
            i_cle += 1
        else:
            resultat += texte[i]
    return resultat


def main():
    """Fonction principale"""
    continu = True
    while continu :
        input_main = input("\n\n\nQue voulez-vous faire ? (crypter,décrypter ou quitter) : ")
        if input_main == "crypter":
            print(vigenere())
        elif input_main == "décrypter" or input_main == "decrypter":
            print(vigenere_inverse())
        else:
            input_main = input("Voulez-vous vraiment quitter ? (oui ou non) : ")
            if input_main == "oui":
                continu = False
                
def chercher_cle(chaine, n) :
    return "".join( chr(ord('A') + cherche_cle_cesar(chaine[i::n])) for i in range(n))


def IC(chaine):
    app = occurence(chaine)
    s = sum (n * (n - 1) for n in app)
    somme = sum(app)
    return s / (somme * (somme - 1))

def occurence(chaine):
    app = [0] * 26
    for c in chaine :
        if c in string.ascii_uppercase:
            app[ord(c) - ord('A')] += 1
    return app

def liste_indices(chaine, n):
    res=[0]
    for i in range(1, n + 1):
        res.append(sum(IC(chaine[k::i]) for k in range(i)))
        res[-1] /= i
    return res

def cherche_longueur_cle(chaine, seuil=0.068, nmax=12):
    p = [(lcle, i) for lcle, i in enumerate(liste_indices(chaine, nmax)) if i > seuil]
    return min(p)

def dechiffre_vigenere(s,cle):
    l = []
    for i, c in enumerate(s):
        if c in string.ascii_uppercase:
            c = ord(c) - ord(cle[i % len(cle)])
            c = chr(c % 26 + ord('A'))
        l.append(c)
    return "".join(l)

def vigenere_main(chaine):
    lgcle, indice = cherche_longueur_cle(chaine)
    lgcle+=1
    cle = chercher_cle(chaine, lgcle)
    print(cle)
    return dechiffre_vigenere(chaine, cle)

main()