#! /usr/bin/env python3

import pygame
import sys

from balle import Balle

global FPSCLOCK
FPS = 30
WINDOWWIDTH = 800
WINDOWHEIGHT = 600
ARRIERE_PLAN = (42,17,51)


class Quitte(Exception ):
    pass

def isQuitEvent(event):
    return (event.type == pygame.QUIT or 
            (event.type == pygame.KEYDOWN and event.key == pygame.K_ESCAPE))

def handleKey(event):
    print("appui sur la touche", event.key)

def handleClick(event):
    print("Clic à la position", event.pos)

def handleEvents():
    for event in pygame.event.get():
        # pour chaque évènement depuis le dernier appel de cette fonction
        if isQuitEvent(event):
            raise Quitte
        elif event.type == pygame.KEYDOWN:
            handleKey(event)
        elif event.type == pygame.MOUSEBUTTONDOWN:
            handleClick(event)
        

def refresh(s):
    s.fill(ARRIERE_PLAN)

temps_total = 0
    
def affichage(s, t, font,balle):
    """
    Redessine l'écran. 't' est le temps écoulé depuis l'image précédente.
    """
    global temps_total
    balle.avance(temps_total,WINDOWWIDTH,WINDOWHEIGHT)
    temps_total += t
    refresh(s)
    balle.dessine(s)
    message = "Allons chercher la baballe"
    message = font.render(message, 1, (255,255,255))
    s.blit(message, (0,0))

def main():
    pygame.init()
    FPSCLOCK = pygame.time.Clock()
    pygame.display.set_caption('Baballe 4.0')
    ecran = pygame.display.set_mode((WINDOWWIDTH, WINDOWHEIGHT))
    font = pygame.font.Font(pygame.font.match_font('comicsans'),30)
    refresh(ecran)

    while True:  #boucle principale
        try:
            handleEvents()
            pygame.display.update()
            temps_ecoule = FPSCLOCK.tick(FPS)
            affichage(ecran, temps_ecoule, font,Balle(2,2,0.2,0.2,(0,0,0),50,WINDOWWIDTH,WINDOWHEIGHT))
        except Quitte:
            break

            
    pygame.quit()
    sys.exit(0)

main()
