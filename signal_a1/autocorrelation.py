# -*- coding: utf-8 -*-
"""
Created on Tue May 19 21:25:05 2020

@author: 26fatiog7
"""
import sys
from math import cos,sin
#VALEURS A CHANGER A PARTIR D ICI
A = 1.25
f = 2500
v = 3.1415926/2
Fe = 100000
T = 1/Fe
#FIN DES VALEURS A CHANGER

a = []
for n in range (0, 1000):
    x =  A*cos(2*3.1415926*f*n*T + v)
    a.append(x)

print (a)
autoco = 0
for k in range(1, len(a)):
   b = []
   nbr = 0
   kk = k
   print ("autoco= ", autoco, "k= ", k-1)
   while nbr < len(a)//2-1 & k+kk < len(a):
       res = a[nbr]
       res2 = a[nbr + k]
       nbr = nbr +1
       if len(b) == 0:
           autoco = res*res2
           b.append(autoco)
       else:
           autoco = b[0] + res*res2
           b.clear()
           b.append(autoco)
           
   #print ("autoco= ", autoco, "k= ", k)