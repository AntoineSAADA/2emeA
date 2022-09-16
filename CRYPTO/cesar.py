from base64 import decode
from cmath import sqrt
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

# print(cesar("J'aime le chiffrement",3))

def cle_cesar(texte:string):
    liste_possibilités = []
    for i in range(26):
        liste_possibilités.append(cesar(texte,i))
    return liste_possibilités

# print(cle_cesar("M'DLPH OH FKLIIUHPHQW"))

# def decode_cesar1(texte:string):
#     compteur_e = 0
#     nv_compteur = 0
#     res = ""
#     possibilités = cle_cesar(texte)
#     for essaie in possibilités:
#         for lettre in essaie:
#             if lettre == "e" or lettre == "E":
#                 nv_compteur +=1
#         if nv_compteur > compteur_e:
#             res = essaie
#             compteur_e = nv_compteur
#             nv_compteur = 0
#     return res

def frequence_lettre(texte:string):
    res = {}
    for lettre in ALPHABET:
        res[lettre] = 0
    for lettre in texte:
        if lettre in ALPHABET:
            res[lettre] += 1
    for cles in res.keys():
        res[cles] = res[cles]/len(texte)
    return res


def dist(dict1,dict2):
    # Calcul la distance euclidienne entre 2 vecteurs (dictionnaires)
    d=0
    for l in ALPHABET:
        if l in dict1.keys() and l in dict2.keys():
            d += (dict1[l]-dict2[l])**2
    return sqrt(d).real

def decode_cesar2(texte:string):
    res = ""
    possibilités = cle_cesar(texte)
    d = dist(frequence_lettre(texte),DICT_FREQ)
    for essaie in possibilités:
        if dist(frequence_lettre(essaie),DICT_FREQ) < d:
            res = essaie
            d = dist(frequence_lettre(essaie),DICT_FREQ)
    return res

print(cesar("MIAOU",10))
print(decode_cesar2("voc ckxqvydc vyxqc noc fsyvyxc no v kedywxo lvoccoxd wyx myoeb n exo vkxqeoeb wyxydyxo"))



