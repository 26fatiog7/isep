# -*- coding: utf-8 -*-
"""
Created on Tue May 19 21:25:05 2020

Soient deux signaux sinusoïdaux échantillonnés 
à la fréquence Fe=1/T=16KHz :
x(nT)=Axcos(2πf0nT) 
y(nT)=Aycos(2πf0nT+θ) 
avec f0=250Hz, Ax=2.5V, Ay=5.3V et θ=π4.
On calcule l'intercorrélation normalisée :
Cxy(kT)=1N∑N−1n=0x(nT)y((n+k)T) . On prendNT≫1f0.
Quelle valeur prend le maximum de correlation ?

@author: 26fatiog7
"""
import sys
from math import cos,sin

# Valeur à changer à patrtir d'ici
# x(n)
A=2.5
f=250
Fe=16000
T = 1/Fe

#y(n)
A1 = 5.3
v=3.1415926/4
#Fin des valeurs à changer


# on veut NT >> 1/f0 => N >> 1/(f0*T) 
N = 20*1/(f * T) 
print(N)

a2 = []
a = []
for n in range (0, 1000):
    x =  A*cos(2*3.1415926*f*n*T)
    a.append(x)
    a2.append(x)

aa = []
aa2 = []
  
for n in range (0, 1000):
    x =  A*cos(2*3.1415926*f*n*T + v)
    aa.append(x)
    aa2.append(x)

b = []
for n in range (0, int(N)):
    if len(b) == 0:
        res = A * A1 * cos((2 * 3.1415926 * f * n *T))**2
        b.append(res)
    else:
        res = b[0]+A*A1*cos((2*3.1415926*f * n *T))**2
        b.clear()
        b.append(res)
    print ("autoco : ", b[0]/N)
print ("somme finale =", b[0]/N)









           