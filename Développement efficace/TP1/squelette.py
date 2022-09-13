#! /usr/bin/env python3

from glob import glob
import pygame
import sys

from balle import Balle

global FPSCLOCK
FPS = 30
WINDOWWIDTH = 800
WINDOWHEIGHT = 600
ARRIERE_PLAN = (42,17,51)
balle1 = Balle(2,2,0.2,0.2,(0,0,0),50,WINDOWWIDTH,WINDOWHEIGHT)
balle2 = Balle(2,2,0.5,0.9,(0,0,0),50,WINDOWWIDTH,WINDOWHEIGHT)
SCORE = 0
 
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
    global SCORE
    for event in pygame.event.get():
        # pour chaque évènement depuis le dernier appel de cette fonction
        if isQuitEvent(event):
            raise Quitte
        elif event.type == pygame.KEYDOWN:
            handleKey(event)
        elif event.type == pygame.MOUSEBUTTONDOWN:
            handleClick(event)
            if balle1.contient(event.pos):
                print("Baballe trouvée !")
                SCORE += 1
            elif balle2.contient(event.pos):
                print("Baballe trouvée !")
                SCORE += 10
            
                
def refresh(s):
    s.fill(ARRIERE_PLAN)

temps_total = 0
    
def affichage(s, t, font,balle1,balle2):
    """
    Redessine l'écran. 't' est le temps écoulé depuis l'image précédente.
    """
    global temps_total
    temps_total += t
    refresh(s)
    balle1.avance(temps_total,WINDOWWIDTH,WINDOWHEIGHT)
    balle1.dessine(s)
    balle2.avance(temps_total,WINDOWWIDTH,WINDOWHEIGHT)
    balle2.dessine(s)
    
    message = "Allons chercher la baballe Score : {}".format(SCORE)
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
            affichage(ecran, temps_ecoule, font,balle1,balle2)
        except Quitte:
            break

            
    pygame.quit()
    sys.exit(0)

main()
