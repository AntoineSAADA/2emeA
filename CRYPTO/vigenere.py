# Cryptage de vigenere
from base64 import decode


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

#decripter vigenere sans la cle
def vigenere_inverse2():
    """Décryptage de vigenere"""
    input_text = input("Entrez le texte à décrypter : ")
    texte = input_text.upper()
    alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    resultat = ""
    for i in range(len(alphabet)):
        for j in range(len(texte)):
            if texte[j] in alphabet:
                resultat += alphabet[(alphabet.index(texte[j]) - i) % len(alphabet)]
            else:
                resultat += texte[j]
        print(resultat)
        resultat = ""


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
    
main()