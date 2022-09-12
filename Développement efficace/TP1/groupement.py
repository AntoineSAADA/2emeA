import sys
import json
import commune


class Groupement:
    def __init__(self, nom, divisions):
        self._nom = nom
        self._divisions = divisions

    def divisions(self):
        return self._divisions

    def population(self):
        total = 0
        for d in self.divisions():
            total += d.population()
        return total

    def superficie(self):
        return 12345 # question 15
        
    def nom(self):
        return "Sodrovno-Voldachie" # question 15

    def nombre_communes(self):
        return 234 # question 15

    def medecins_par_10_000_hab(self):
        total_m = 0
        for d in self.divisions():
            m = d.medecins_par_10_000_hab() * (d.population() / 10000)
            total_m += m
        pop = self.population()
        if pop == 0:
            return 0
        else:
            return 10000 * total_m / self.population()

        
def dict_vers_territoire(d):
    if "communes" in d:
        dictionnaires_divisions = d["communes"]
    elif "departements" in d:
        dictionnaires_divisions = d["departements"]
    elif "regions" in d:
        dictionnaires_divisions = d["regions"]
    else: # pas de subdivisions, c'est une commune
        return commune.dict_vers_commune(d)

    divisions = []
    for dico_division in dictionnaires_divisions:
        t = dict_vers_territoire(dico_division)
        divisions.append(t)
    return Groupement(d["nom"], divisions)

                
def main(nom_fichier_json):
    with open(nom_fichier_json) as j:
        dictionnaires_pays = json.load(j)
        pays = []
        for d in dictionnaires_pays:
            p = dict_vers_territoire(d)
            pays.append(p)

        for p in pays:
            print("Bienvenue en", p.nom())
            print("Il y a", p.population(), "habitants.")
            print("La superficie est de", p.superficie(), ".")
            print("Il y a", p.medecins_par_10_000_hab(), "médecins pour 10_000 hab.")
            # print("Ce pays est composé de", p.nombre_communes(), "communes")
            print("")

            
if __name__ == "__main__":
    nom_fichier_json = sys.argv[1]
    main(nom_fichier_json)
