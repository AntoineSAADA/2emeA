# Programme permettant de chiffrer et de déchiffrer un texte avec la méthode de Hill

def chiffrement_hill(chaine:str, cle:list) -> str:
    res = ""
    for i in range(0, len(chaine)):
        for j in range(0, len(cle)):
            res += chr((ord(chaine[i]) * cle[j]) % 26 + 65)
    return res 

print(chiffrement_hill("TEST", [[1, 2], [3, 4]]))
