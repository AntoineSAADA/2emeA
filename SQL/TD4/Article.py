#Création de la classe article avec comme argument une reference(int) un libelle(String) et un prix(float)
class Article:
    def __init__(self,reference,libelle,prix):
        self.reference = reference
        self.libelle = libelle
        self.prix = prix

    def __str__(self):
        return "Article : "+str(self.reference)+", "+self.libelle+", "+str(self.prix)
    def __repr__(self):
        return "Article : "+str(self.reference)+", "+self.libelle+", "+str(self.prix)
    