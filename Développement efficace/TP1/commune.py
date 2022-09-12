import json

class Commune:
    def __init__(self, nom, population, superficie, medecins_par_10_000_hab):
        self._population = population
        self._superficie = superficie
        self._medecins_par_10_000_hab = medecins_par_10_000_hab
        self._nom = nom

    def __repr__(self):
        return "[ Commune de {s._nom}: {s._population} hab. | superficie = {s._superficie}km² | {s._medecins_par_10_000_hab} med / 10000hab ]".format(s=self)
    def population(self):
        return self._population

    def superficie(self):
        return self._superficie

    def medecins_par_10_000_hab(self):
        return self._medecins_par_10_000_hab 

    def nom(self):
        return self._nom 

    def as_dict(self):
        return { "population": self.population(),
                 "superficie": self.superficie(),
                 "medecins_par_10_000_hab": self.medecins_par_10_000_hab,
                 "nom": self.nom }

def dict_vers_commune(d):
        return Commune(d["nom"], d["population"], d["superficie"], d["medecins_par_10_000_hab"])
