import math


def algo_basique(nombre):
    for i in range(2, nombre):
        if nombre % i == 0:
            return False
    return True

def algo_ameliore(nombre):
    for i in range(2, int(math.sqrt(nombre))+1):
        if nombre % i == 0:
            return False
    return True

def eratothenes(nombre):
    liste = [True] * nombre
    for i in range(2, int(math.sqrt(nombre))+1):
        if liste[i]:
            for j in range(i**2, nombre, i):
                liste[j] = False
    return liste

def nombre_pseudo_premier(nombre):
    for i in range(2, nombre):
        if algo_ameliore(i) and algo_ameliore(nombre-i):
            return True
    return False

def pgcd(a, b):
    while b != 0:
        a, b = b, a % b
    return a

def coef_bezout(a, b):
    if b == 0:
        return (1, 0)
    else:
        q, r = divmod(a, b)
        s, t = coef_bezout(b, r)
        return (t, s - q * t)
    
def inverse_modulaire(a, n):
    u, v, s, t = 1, 0, 0, 1
    while n != 0:
        q, r = divmod(a, n)
        a, n = n, r
        u, s = s, u - q * s
        v, t = t, v - q * t
    return u


def cryptage_rsa(m, n, e):
    return (m ** e) % n

