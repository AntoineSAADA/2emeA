#Création d'une classe entrepot ayant comme argument un code(int) un nom(String) et un departement(String)
class Entrepot:
    def __init__(self,code,nom,departement):
        self.code = code
        self.nom = nom
        self.departement = departement
    def __str__(self):
        return "Entrepot : "+str(self.code)+", "+self.nom+", "+self.departement
    def __repr__(self):
        return "Entrepot : "+str(self.code)+", "+self.nom+", "+self.departement