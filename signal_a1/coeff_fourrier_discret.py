# -*- coding: utf-8 -*-
"""
Created on Wed May 20 23:23:58 2020
    enonce :
        Soit le signal discret de N=4 échantillons :
        x0=1x1=5x2=−1x3=2
        Calculer le coefficient de Fourier discret X2
@author: 26fatiog7
"""
from math import cos,sin,sqrt

k = 2      # demande le k de Xk
x = [1, 5, -1, 2]   # valeur x0   x1   x2   x3   x4 ....
x1 = 1
x2 = 5
x3 = -1
x4 = 2
N = 4       # nombre d'echatillons
b = []
c = []
"""
for  n in range (0, N):
    b.append(x[n] * cos(-2 * 3.1415926 * k * n/N))
    c.append(x[n] * sin(-2 * 3.1415926 * k * n/N))

print(c, b)
R = 0
I = 0
for  n in range (0, N):
    R = R + b[n]
    I = I + c[n]

print (sqrt(R**2 + I**2))
"""
a1 = x1 * cos(-2 * 3.1415926 * k * n/N))