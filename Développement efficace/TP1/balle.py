#! /usr/bin/env python3
import pygame

class Balle:
    
    def __init__(self,position_x:int,position_y:int,vitesse_x:int,vitesse_y:int,couleur:tuple,taille:int,max_x:int,max_y:int):
        self.position_x = position_x
        self.position_y = position_y
        self.vitesse_x = vitesse_x
        self.vitesse_y = vitesse_y
        self.couleur = couleur
        self.taille = taille
        self.max_x = max_x
        self.max_y = max_y
    
    def avance(self,t,max_x,max_y):
        self.position_x = int(self.vitesse_x*t)% max_x
        self.position_y = int(self.vitesse_y*t)% max_y
    
    def dessine(self,s):
        pygame.draw.circle(s, self.couleur, (self.position_x,self.position_y), self.taille)
        
    def contient(self,position):
        x = self.position_x
        y = self.position_y
        xx,yy = position
        dx2 = (x-xx)**2
        dy2 = (y-yy)**2
        return dx2 + dy2 < self.taille**2
    
    
        
    
    