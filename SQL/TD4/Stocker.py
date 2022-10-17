#Creation d'une classe Stocker ayant comme argument un codeArticle(int) un codeEntrepot(int) et une quantite(int)
class Stocker:
    def __init__(self,codeArticle,codeEntrepot,quantite):
        self.codeArticle = codeArticle
        self.codeEntrepot = codeEntrepot
        self.quantite = quantite
    def __str__(self):
        return "Stocker : "+str(self.codeArticle)+", "+str(self.codeEntrepot)+", "+str(self.quantite)
    def __repr__(self):
        return "Stocker : "+str(self.codeArticle)+", "+str(self.codeEntrepot)+", "+str(self.quantite)